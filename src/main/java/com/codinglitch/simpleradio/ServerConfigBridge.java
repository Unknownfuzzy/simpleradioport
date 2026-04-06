package com.codinglitch.simpleradio;

import net.neoforged.neoforge.common.ModConfigSpec;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class ServerConfigBridge {
    public final FrequencyConfig frequency = new FrequencyConfig();
    public final RouterConfig router = new RouterConfig();
    public final DeviceConfig microphone = new DeviceConfig();
    public final DeviceConfig speaker = new DeviceConfig();
    public final DeviceConfig radio = new DeviceConfig();
    public final DeviceConfig walkie_talkie = new DeviceConfig();
    public final DeviceConfig transceiver = new DeviceConfig();
    public final DeviceConfig transmitter = new DeviceConfig();
    public final DeviceConfig receiver = new DeviceConfig();
    public final DeviceConfig wire = new DeviceConfig();
    public final AntennaConfig antenna = new AntennaConfig();
    public final CompatibilityConfig compatibilities = new CompatibilityConfig();

    public ServerConfigBridge() {
        frequency.defaultFrequency = "auto-generate";
        frequency.crossDimensional = false;
        frequency.dimensionalInterference = 4.0;
        frequency.baseFMInterference = 2.0;
        frequency.baseAMInterference = 15.0;
        frequency.packetBuffer = 2;

        router.compileAmount = 10;
        router.activityForgiveness = 2;
        router.activityTime = 20;
        router.activityRedstoneFactor = 1500.0;
        router.feedbackListening = true;
        router.soundListening = false;

        microphone.listeningRange = 8;
        microphone.redstonePolling = 5;

        speaker.speakingRange = 32;
        speaker.redstonePolling = 5;

        radio.receptionPower = 100;
        radio.receptionFloor = 50;
        radio.speakingRange = 24;

        walkie_talkie.receptionPower = 100;
        walkie_talkie.receptionFloor = 20;
        walkie_talkie.transmissionPowerFM = 500;
        walkie_talkie.diminishThresholdFM = 100;
        walkie_talkie.transmissionPowerAM = 900;
        walkie_talkie.diminishThresholdAM = 200;
        walkie_talkie.transmissionDiminishment = 1.0;
        walkie_talkie.listeningRange = 4;
        walkie_talkie.speakingRange = 4;

        transceiver.receptionPower = 200;
        transceiver.receptionFloor = 10;
        transceiver.transmissionPowerFM = 1000;
        transceiver.diminishThresholdFM = 200;
        transceiver.transmissionPowerAM = 1800;
        transceiver.diminishThresholdAM = 300;
        transceiver.transmissionDiminishment = 1.0;
        transceiver.listeningRange = 4;
        transceiver.speakingRange = 4;

        transmitter.antennaAptitude = 10;
        transmitter.transmissionPowerFM = 3300;
        transmitter.diminishThresholdFM = 300;
        transmitter.transmissionPowerAM = 4400;
        transmitter.diminishThresholdAM = 500;
        transmitter.transmissionDiminishment = 1.0;

        receiver.antennaAptitude = 10;
        receiver.receptionPower = 300;
        receiver.receptionFloor = 20;

        wire.diminishmentMethod = "MULTIPLICATIVE";
        wire.transmissionDiminishment = 0.01;

        antenna.maxDistance = 8;
    }

    public static class FrequencyConfig {
        public int wholePlaces = 3;
        // Fix for FrequenciesImpl.java
        public ModConfigSpec.IntValue decimalPlaces = SimpleRadioServerConfig.FREQUENCY_DECIMAL_PLACES;
        public String defaultFrequency = "000.00";
        public boolean crossDimensional = true;
        public double dimensionalInterference = 0.5;
        public double baseFMInterference = 0.1;
        public double baseAMInterference = 0.2;
        public int packetBuffer = 5;
    }

    public static class RouterConfig {
        // Fix for RadioRouter.java:492 and 499
        public int compileAmount = 10;
        public int activityForgiveness = 2;
        public int activityTime = 20;
        public double activityRedstoneFactor = 1500.0;
        public boolean feedbackListening = true;
        public boolean soundListening = false;
    }

    public static class DeviceConfig {
        // Fix for MicrophoneBlock.java:72
        public int receptionPower = 0;
        public int receptionFloor = 0;
        public int antennaAptitude = 0;
        public int transmissionPowerFM = 0;
        public int diminishThresholdFM = 0;
        public int transmissionPowerAM = 0;
        public int diminishThresholdAM = 0;
        public String diminishmentMethod = "ADDITIVE";
        public double transmissionDiminishment = 0.0;
        public int listeningRange = 8;
        public int speakingRange = 8;
        public int redstonePolling = 5;
        public boolean enabled = true;
    }

    public static class AntennaConfig {
        public int maxDistance = 64;
    }

    public static class CompatibilityConfig {
        public boolean voice_chat_interaction = true;
        public boolean vibrative_voice = true;
        public boolean valkyrien_skies = true;
        public boolean cc_tweaked = true;
        public boolean etched = true;
        public boolean audioplayer = true;
    }
}
