package com.codinglitch.simpleradio.radio;

import com.codinglitch.simpleradio.SimpleRadioLibrary;
import com.codinglitch.simpleradio.CompatCore;
import com.codinglitch.simpleradio.SimpleRadioApi;
import com.codinglitch.simpleradio.central.WorldlyPosition;
import com.codinglitch.simpleradio.core.networking.packets.ClientboundSpeakSoundPacket;
import com.codinglitch.simpleradio.platform.Services;
import com.codinglitch.simpleradio.radio.effects.AudioEffect;
import com.codinglitch.simpleradio.radio.effects.BaseAudioEffect;
import com.codinglitch.simpleradio.core.routers.Speaker;
import de.maxhenkel.voicechat.api.audiochannel.AudioChannel;
import de.maxhenkel.voicechat.api.audiochannel.AudioPlayer;
import de.maxhenkel.voicechat.api.audiochannel.EntityAudioChannel;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import de.maxhenkel.voicechat.api.opus.OpusDecoder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

/**
 * A type of {@link RadioRouter} that accepts {@link RadioSource}s and emits them in-world.
 * <br>
 * Often serves as the end of the audio pipeline.
 * <br>
 * <b>Does not route further.</b>
 */
public class RadioSpeaker extends RadioRouter implements Supplier<short[]>, Speaker {
    public AudioChannel audioChannel;
    public EntityAudioChannel entityAudioChannel;
    public LocationalAudioChannel locationalAudioChannel;
    public AudioPlayer audioPlayer;
    private final Map<UUID, Map<UUID, Queue<short[]>>> packetBuffer;
    private final Map<UUID, OpusDecoder> decoders;
    private final AudioEffect effect;

    public String category;
    public float range = 8;

    public int speakingTime = 0;
    private int emptyFrames = 0;

    protected RadioSpeaker(UUID id) {
        super(id);

        packetBuffer = new ConcurrentHashMap<>();
        decoders = new ConcurrentHashMap<>();
        effect = new BaseAudioEffect();
    }
    protected RadioSpeaker() {
        this(UUID.randomUUID());
    }

    public RadioSpeaker(Entity owner) {
        this(owner, UUID.randomUUID());
    }
    public RadioSpeaker(Entity owner, UUID uuid) {
        this(uuid);
        this.owner = owner;

        SimpleRadioApi.registerRouterSided(this, owner.level().isClientSide(), null);
    }
    public RadioSpeaker(WorldlyPosition location) {
        this(location, UUID.randomUUID());
    }
    public RadioSpeaker(WorldlyPosition location, UUID uuid) {
        this(uuid);
        this.position = location;

        SimpleRadioApi.registerRouterSided(this, location.isClientSide(), null);
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getSpeakingTime() {
        return speakingTime;
    }

    @Override
    public short[] get() {
        short[] audio = generatePacket();
        if (audio == null) {
            if (audioPlayer != null && (this.speakingTime > 0 || !packetBuffer.isEmpty()) && emptyFrames < 2) {
                emptyFrames++;
                return new short[960];
            }

            if (audioPlayer != null) {
                audioPlayer.stopPlaying();
            }

            audioPlayer = null;
            audioChannel = null;
            entityAudioChannel = null;
            locationalAudioChannel = null;
            emptyFrames = 0;
            return null;
        }

        emptyFrames = 0;
        return audio;
    }

    public short[] generatePacket() {
        List<short[]> totalPacketsToCombine = new ArrayList<>();

        for (Map.Entry<UUID, Map<UUID, Queue<short[]>>> listenerPacket : packetBuffer.entrySet()) {
            Map<UUID, Queue<short[]>> playerPackets = listenerPacket.getValue();
            if (playerPackets.isEmpty()) continue;

            List<short[]> playerPacketsToCombine = new ArrayList<>();
            for (Map.Entry<UUID, Queue<short[]>> playerPacket : playerPackets.entrySet()) {
                short[] audio = playerPacket.getValue().poll();
                if (audio != null) playerPacketsToCombine.add(audio);
            }
            playerPackets.values().removeIf(Queue::isEmpty);

            if (!playerPacketsToCombine.isEmpty()) {
                totalPacketsToCombine.add(CommonRadioPlugin.combineAudio(playerPacketsToCombine));
            }
        }
        packetBuffer.values().removeIf(Map::isEmpty);

        if (totalPacketsToCombine.isEmpty()) return null;

        return CommonRadioPlugin.combineAudio(totalPacketsToCombine);
    }

    @Override
    public void updateLocation(WorldlyPosition location) {
        super.updateLocation(location);

        if (locationalAudioChannel != null) {
            Vector3f audioPosition = getAudioChannelPosition(location);
            locationalAudioChannel.updateLocation(CommonRadioPlugin.serverApi.createPosition(audioPosition.x, audioPosition.y, audioPosition.z));
        }

        if (entityAudioChannel != null && owner != null) {
            entityAudioChannel.updateEntity(CommonRadioPlugin.serverApi.fromEntity(owner));
        }
    }

    @Override
    public void tick(int tickCount) {
        super.tick(tickCount);

        if (speakingTime > 0) speakingTime--;
    }

    @Override
    public void take(Source source) {
        if (!this.active) return;
        if (acceptCriteria != null && !acceptCriteria.test(source)) return;
        super.take(source);
        speak(source);
    }

    public void speak(Source source) {
        RadioSource radioSource = (RadioSource) source;
        boolean cleanVoicePath = radioSource.data != null;

        if (locationalAudioChannel != null) {
            WorldlyPosition currentLocation = getLocation();
            if (currentLocation != null) {
                Vector3f audioPosition = getAudioChannelPosition(currentLocation);
                locationalAudioChannel.updateLocation(CommonRadioPlugin.serverApi.createPosition(audioPosition.x, audioPosition.y, audioPosition.z));
            }
        }

        // Severity calculation
        ServerLevel level = null;
        Vector3f position = null;
        if (this.position != null) {
            level = (ServerLevel) this.position.level;
            position = this.position.position();
        } else {
            level = (ServerLevel) owner.level();
            position = owner.position().toVector3f();
        }
        if (level == null || position == null) return;

        if (!SimpleRadioLibrary.SERVER_CONFIG.frequency.crossDimensional && level != radioSource.origin.level) return;

        float computedSeverity = (float) radioSource.computeSeverity();
        if (!cleanVoicePath && (CommonRadioPlugin.RADIOS_CATEGORY.equals(category) || CommonRadioPlugin.SPEAKERS_CATEGORY.equals(category))) {
            computedSeverity *= 0.35f;
        }
        this.effect.severity = cleanVoicePath ? 0f : Math.max(0f, Math.min(computedSeverity, 100f));
        this.effect.volume = radioSource.volume;
        if (!cleanVoicePath && this.effect.severity >= 100) return;

        // Parsing sound event
        if (radioSource.data == null) {
            if (radioSource.sound == null) return;
            this.compileActivity(source);
            this.speakingTime = 4;

            for (ServerPlayer player : level.players()) {
                if (player.position().distanceTo(new Vec3(position)) < 50) {
                    Services.NETWORKING.sendToPlayer(player, new ClientboundSpeakSoundPacket(
                            this.getReference(), radioSource.sound,
                            radioSource.volume, radioSource.pitch, this.effect.severity, radioSource.offset, radioSource.seed
                    ));
                }
            }

            return;
        }

        // Packet buffer
        Map<UUID, Queue<short[]>> listenerPackets = packetBuffer.computeIfAbsent(radioSource.owner, k -> new ConcurrentHashMap<>());
        Queue<short[]> playerPackets = listenerPackets.computeIfAbsent(radioSource.getRealOwner(), k -> new ConcurrentLinkedQueue<>());

        // Decoding
        byte[] data = radioSource.data;

        OpusDecoder decoder = getDecoder(radioSource.owner);
        if (data == null || data.length == 0) {
            decoder.resetState();
            return;
        }
        short[] decoded = decoder.decode(data);
        if (decoded == null || decoded.length == 0) {
            return;
        }
        if (!CommonRadioPlugin.isAudioValid(decoded)) {
            return;
        }

        if (radioSource.getActivity() > CommonRadioPlugin.AUDIO_ACTIVITY_THRESHOLD) {
            this.compileActivity(source);
            this.speakingTime = 4;
        }

        short[] filtered = cleanVoicePath
                ? applyVolumeOnly(Arrays.copyOf(decoded, decoded.length), radioSource.volume)
                : effect.apply(Arrays.copyOf(decoded, decoded.length));

        playerPackets.offer(filtered);
        trimPacketBuffer(playerPackets);

        // Loader-specific compat
        Services.COMPAT.onData(this, radioSource, decoded);

        // Common compat
        CompatCore.onData(this, radioSource, decoded);

        if (this.audioPlayer == null)
            getAudioPlayer().startPlaying();
    }

    private void trimPacketBuffer(Queue<short[]> packets) {
        int maxPackets = Math.max(1, SimpleRadioLibrary.SERVER_CONFIG.frequency.packetBuffer);
        while (packets.size() > maxPackets) {
            packets.poll();
        }
    }

    private short[] applyVolumeOnly(short[] data, float volume) {
        for (int i = 0; i < data.length; i++) {
            data[i] = clampSample(data[i] * volume);
        }
        return data;
    }

    private short clampSample(float sample) {
        return (short) Math.max(Short.MIN_VALUE, Math.min(Math.round(sample), Short.MAX_VALUE));
    }

    public OpusDecoder getDecoder(UUID sender) {
        return decoders.computeIfAbsent(sender, uuid -> CommonRadioPlugin.serverApi.createDecoder());
    }

    private AudioPlayer getAudioPlayer() {
        if (this.audioPlayer == null) {
            boolean handheldChannel = usesHandheldAudioChannel();

            if (owner != null && !handheldChannel) {
                this.entityAudioChannel = CommonRadioPlugin.serverApi.createEntityAudioChannel(this.reference, CommonRadioPlugin.serverApi.fromEntity(owner));
                this.audioChannel = this.entityAudioChannel;
                if (this.entityAudioChannel != null) {
                    this.entityAudioChannel.setDistance(range);
                    this.entityAudioChannel.setCategory(category);
                }
            }

            if (this.audioChannel == null) {
                WorldlyPosition location = this.getLocation();
                Vector3f audioPosition = getAudioChannelPosition(location);
                this.locationalAudioChannel = CommonRadioPlugin.serverApi.createLocationalAudioChannel(this.reference,
                        CommonRadioPlugin.serverApi.fromServerLevel(location.level),
                        CommonRadioPlugin.serverApi.createPosition(audioPosition.x, audioPosition.y, audioPosition.z)
                );
                this.audioChannel = this.locationalAudioChannel;
                if (this.locationalAudioChannel != null) {
                    this.locationalAudioChannel.setDistance(range);
                    this.locationalAudioChannel.setCategory(category);
                }
            }

            this.audioPlayer = CommonRadioPlugin.serverApi.createAudioPlayer(audioChannel, CommonRadioPlugin.serverApi.createEncoder(), this);
        }
        return this.audioPlayer;
    }

    private boolean usesHandheldAudioChannel() {
        return owner != null && (
                CommonRadioPlugin.TRANSCEIVERS_CATEGORY.equals(category)
                        || CommonRadioPlugin.WALKIES_CATEGORY.equals(category)
        );
    }

    private Vector3f getAudioChannelPosition(WorldlyPosition location) {
        if (owner != null && usesHandheldAudioChannel()) {
            Vec3 look = owner.getLookAngle().scale(0.35d);
            return new Vector3f(
                    (float) (owner.getX() + look.x),
                    (float) (owner.getEyeY() - 0.15d + look.y),
                    (float) (owner.getZ() + look.z)
            );
        }

        if (owner == null) {
            return new Vector3f(location.x + 0.5f, location.y + 0.5f, location.z + 0.5f);
        }

        return new Vector3f(location.x, location.y, location.z);
    }

    @Override
    public void invalidate() {
        if (this.audioPlayer != null) {
            this.audioPlayer.stopPlaying();
        }

        this.packetBuffer.clear();
        this.decoders.clear();
        this.speakingTime = 0;
        this.emptyFrames = 0;
        this.audioPlayer = null;
        this.audioChannel = null;
        this.entityAudioChannel = null;
        this.locationalAudioChannel = null;
        this.active = false;

        super.invalidate();
    }
}
