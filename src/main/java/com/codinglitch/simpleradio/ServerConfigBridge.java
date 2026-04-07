package com.codinglitch.simpleradio;

import net.neoforged.neoforge.common.ModConfigSpec;

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
        loadDefaults();
    }

    private void loadDefaults() {
        frequency.defaultFrequency = "auto-generate";
        frequency.crossDimensional = true;
        frequency.dimensionalInterference = 2.0;
        frequency.baseFMInterference = 1.0;
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

        transceiver.receptionPower = 350;
        transceiver.receptionFloor = 25;
        transceiver.transmissionPowerFM = 1500;
        transceiver.diminishThresholdFM = 350;
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

    public void reloadFromSpec() {
        frequency.decimalPlaces = SimpleRadioServerConfig.FREQUENCY_DECIMAL_PLACES;
        frequency.defaultFrequency = getValue(SimpleRadioServerConfig.FREQUENCY_DEFAULT_FREQUENCY, frequency.defaultFrequency);
        frequency.crossDimensional = getValue(SimpleRadioServerConfig.FREQUENCY_CROSS_DIMENSIONAL, frequency.crossDimensional);
        frequency.dimensionalInterference = getValue(SimpleRadioServerConfig.FREQUENCY_DIMENSIONAL_INTERFERENCE, frequency.dimensionalInterference);
        frequency.baseFMInterference = getValue(SimpleRadioServerConfig.FREQUENCY_BASE_FM_INTERFERENCE, frequency.baseFMInterference);
        frequency.baseAMInterference = getValue(SimpleRadioServerConfig.FREQUENCY_BASE_AM_INTERFERENCE, frequency.baseAMInterference);
        frequency.packetBuffer = getValue(SimpleRadioServerConfig.FREQUENCY_PACKET_BUFFER, frequency.packetBuffer);

        router.compileAmount = getValue(SimpleRadioServerConfig.ROUTER_COMPILE_AMOUNT, router.compileAmount);
        router.activityForgiveness = getValue(SimpleRadioServerConfig.ROUTER_ACTIVITY_FORGIVENESS, router.activityForgiveness);
        router.activityTime = getValue(SimpleRadioServerConfig.ROUTER_ACTIVITY_TIME, router.activityTime);
        router.activityRedstoneFactor = getValue(SimpleRadioServerConfig.ROUTER_ACTIVITY_REDSTONE_FACTOR, router.activityRedstoneFactor);
        router.feedbackListening = getValue(SimpleRadioServerConfig.ROUTER_FEEDBACK_LISTENING, router.feedbackListening);
        router.soundListening = getValue(SimpleRadioServerConfig.ROUTER_SOUND_LISTENING, router.soundListening);

        microphone.listeningRange = getValue(SimpleRadioServerConfig.MICROPHONE_LISTENING_RANGE, microphone.listeningRange);

        speaker.speakingRange = getValue(SimpleRadioServerConfig.SPEAKER_SPEAKING_RANGE, speaker.speakingRange);

        radio.receptionPower = getValue(SimpleRadioServerConfig.RADIO_RECEPTION_POWER, radio.receptionPower);
        radio.receptionFloor = getValue(SimpleRadioServerConfig.RADIO_RECEPTION_FLOOR, radio.receptionFloor);
        radio.speakingRange = getValue(SimpleRadioServerConfig.RADIO_SPEAKING_RANGE, radio.speakingRange);

        walkie_talkie.receptionPower = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_RECEPTION_POWER, walkie_talkie.receptionPower);
        walkie_talkie.receptionFloor = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_RECEPTION_FLOOR, walkie_talkie.receptionFloor);
        walkie_talkie.transmissionPowerFM = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_TRANSMISSION_POWER_FM, walkie_talkie.transmissionPowerFM);
        walkie_talkie.diminishThresholdFM = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_DIMINISH_THRESHOLD_FM, walkie_talkie.diminishThresholdFM);
        walkie_talkie.transmissionPowerAM = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_TRANSMISSION_POWER_AM, walkie_talkie.transmissionPowerAM);
        walkie_talkie.diminishThresholdAM = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_DIMINISH_THRESHOLD_AM, walkie_talkie.diminishThresholdAM);
        walkie_talkie.transmissionDiminishment = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_TRANSMISSION_DIMINISHMENT, walkie_talkie.transmissionDiminishment);
        walkie_talkie.listeningRange = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_LISTENING_RANGE, walkie_talkie.listeningRange);
        walkie_talkie.speakingRange = getValue(SimpleRadioServerConfig.WALKIE_TALKIE_SPEAKING_RANGE, walkie_talkie.speakingRange);

        transceiver.receptionPower = getValue(SimpleRadioServerConfig.TRANSCEIVER_RECEPTION_POWER, transceiver.receptionPower);
        transceiver.receptionFloor = getValue(SimpleRadioServerConfig.TRANSCEIVER_RECEPTION_FLOOR, transceiver.receptionFloor);
        transceiver.transmissionPowerFM = getValue(SimpleRadioServerConfig.TRANSCEIVER_TRANSMISSION_POWER_FM, transceiver.transmissionPowerFM);
        transceiver.diminishThresholdFM = getValue(SimpleRadioServerConfig.TRANSCEIVER_DIMINISH_THRESHOLD_FM, transceiver.diminishThresholdFM);
        transceiver.transmissionPowerAM = getValue(SimpleRadioServerConfig.TRANSCEIVER_TRANSMISSION_POWER_AM, transceiver.transmissionPowerAM);
        transceiver.diminishThresholdAM = getValue(SimpleRadioServerConfig.TRANSCEIVER_DIMINISH_THRESHOLD_AM, transceiver.diminishThresholdAM);
        transceiver.transmissionDiminishment = getValue(SimpleRadioServerConfig.TRANSCEIVER_TRANSMISSION_DIMINISHMENT, transceiver.transmissionDiminishment);
        transceiver.listeningRange = getValue(SimpleRadioServerConfig.TRANSCEIVER_LISTENING_RANGE, transceiver.listeningRange);
        transceiver.speakingRange = getValue(SimpleRadioServerConfig.TRANSCEIVER_SPEAKING_RANGE, transceiver.speakingRange);

        transmitter.antennaAptitude = getValue(SimpleRadioServerConfig.TRANSMITTER_ANTENNA_APTITUDE, transmitter.antennaAptitude);
        transmitter.transmissionPowerFM = getValue(SimpleRadioServerConfig.TRANSMITTER_TRANSMISSION_POWER_FM, transmitter.transmissionPowerFM);
        transmitter.diminishThresholdFM = getValue(SimpleRadioServerConfig.TRANSMITTER_DIMINISH_THRESHOLD_FM, transmitter.diminishThresholdFM);
        transmitter.transmissionPowerAM = getValue(SimpleRadioServerConfig.TRANSMITTER_TRANSMISSION_POWER_AM, transmitter.transmissionPowerAM);
        transmitter.diminishThresholdAM = getValue(SimpleRadioServerConfig.TRANSMITTER_DIMINISH_THRESHOLD_AM, transmitter.diminishThresholdAM);
        transmitter.transmissionDiminishment = getValue(SimpleRadioServerConfig.TRANSMITTER_TRANSMISSION_DIMINISHMENT, transmitter.transmissionDiminishment);

        receiver.antennaAptitude = getValue(SimpleRadioServerConfig.RECEIVER_ANTENNA_APTITUDE, receiver.antennaAptitude);
        receiver.receptionPower = getValue(SimpleRadioServerConfig.RECEIVER_RECEPTION_POWER, receiver.receptionPower);
        receiver.receptionFloor = getValue(SimpleRadioServerConfig.RECEIVER_RECEPTION_FLOOR, receiver.receptionFloor);

        wire.diminishmentMethod = getValue(SimpleRadioServerConfig.WIRE_DIMINISHMENT_METHOD, wire.diminishmentMethod);
        wire.transmissionDiminishment = getValue(SimpleRadioServerConfig.WIRE_TRANSMISSION_DIMINISHMENT, wire.transmissionDiminishment);

        antenna.maxDistance = getValue(SimpleRadioServerConfig.ANTENNA_MAX_DISTANCE, antenna.maxDistance);

        compatibilities.voice_chat_interaction = true;
        compatibilities.vibrative_voice = true;
        compatibilities.valkyrien_skies = true;
        compatibilities.cc_tweaked = true;
        compatibilities.etched = true;
        compatibilities.audioplayer = true;
    }

    private static <T> T getValue(ModConfigSpec.ConfigValue<T> value, T fallback) {
        try {
            return value.get();
        } catch (IllegalStateException ignored) {
            return fallback;
        }
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
