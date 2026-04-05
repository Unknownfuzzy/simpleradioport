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
        public int activityForgiveness = 5;
        public boolean feedbackListening = true;
        public boolean soundListening = true;
    }

    public static class DeviceConfig {
        // Fix for MicrophoneBlock.java:72
        public int listeningRange = 16;
        public int speakingRange = 16;
        public int redstonePolling = 2;
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