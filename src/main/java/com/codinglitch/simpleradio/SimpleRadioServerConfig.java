package com.codinglitch.simpleradio;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SimpleRadioServerConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue ROUTER_COMPILE_AMOUNT;
    public static final ModConfigSpec.IntValue ROUTER_ACTIVITY_FORGIVENESS;
    public static final ModConfigSpec.IntValue ROUTER_ACTIVITY_TIME;
    public static final ModConfigSpec.DoubleValue ROUTER_ACTIVITY_REDSTONE_FACTOR;
    public static final ModConfigSpec.BooleanValue ROUTER_FEEDBACK_LISTENING;
    public static final ModConfigSpec.BooleanValue ROUTER_SOUND_LISTENING;

    public static final ModConfigSpec.IntValue FREQUENCY_DECIMAL_PLACES;
    public static final ModConfigSpec.ConfigValue<String> FREQUENCY_DEFAULT_FREQUENCY;
    public static final ModConfigSpec.BooleanValue FREQUENCY_CROSS_DIMENSIONAL;
    public static final ModConfigSpec.DoubleValue FREQUENCY_DIMENSIONAL_INTERFERENCE;
    public static final ModConfigSpec.DoubleValue FREQUENCY_BASE_FM_INTERFERENCE;
    public static final ModConfigSpec.DoubleValue FREQUENCY_BASE_AM_INTERFERENCE;
    public static final ModConfigSpec.IntValue FREQUENCY_PACKET_BUFFER;

    public static final ModConfigSpec.IntValue MICROPHONE_LISTENING_RANGE;

    public static final ModConfigSpec.IntValue SPEAKER_SPEAKING_RANGE;

    public static final ModConfigSpec.IntValue RADIO_RECEPTION_POWER;
    public static final ModConfigSpec.IntValue RADIO_RECEPTION_FLOOR;
    public static final ModConfigSpec.IntValue RADIO_SPEAKING_RANGE;

    public static final ModConfigSpec.IntValue WALKIE_TALKIE_RECEPTION_POWER;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_RECEPTION_FLOOR;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_TRANSMISSION_POWER_FM;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_DIMINISH_THRESHOLD_FM;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_TRANSMISSION_POWER_AM;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_DIMINISH_THRESHOLD_AM;
    public static final ModConfigSpec.DoubleValue WALKIE_TALKIE_TRANSMISSION_DIMINISHMENT;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_LISTENING_RANGE;
    public static final ModConfigSpec.IntValue WALKIE_TALKIE_SPEAKING_RANGE;

    public static final ModConfigSpec.IntValue TRANSCEIVER_RECEPTION_POWER;
    public static final ModConfigSpec.IntValue TRANSCEIVER_RECEPTION_FLOOR;
    public static final ModConfigSpec.IntValue TRANSCEIVER_TRANSMISSION_POWER_FM;
    public static final ModConfigSpec.IntValue TRANSCEIVER_DIMINISH_THRESHOLD_FM;
    public static final ModConfigSpec.IntValue TRANSCEIVER_TRANSMISSION_POWER_AM;
    public static final ModConfigSpec.IntValue TRANSCEIVER_DIMINISH_THRESHOLD_AM;
    public static final ModConfigSpec.DoubleValue TRANSCEIVER_TRANSMISSION_DIMINISHMENT;
    public static final ModConfigSpec.IntValue TRANSCEIVER_LISTENING_RANGE;
    public static final ModConfigSpec.IntValue TRANSCEIVER_SPEAKING_RANGE;

    public static final ModConfigSpec.IntValue TRANSMITTER_ANTENNA_APTITUDE;
    public static final ModConfigSpec.IntValue TRANSMITTER_TRANSMISSION_POWER_FM;
    public static final ModConfigSpec.IntValue TRANSMITTER_DIMINISH_THRESHOLD_FM;
    public static final ModConfigSpec.IntValue TRANSMITTER_TRANSMISSION_POWER_AM;
    public static final ModConfigSpec.IntValue TRANSMITTER_DIMINISH_THRESHOLD_AM;
    public static final ModConfigSpec.DoubleValue TRANSMITTER_TRANSMISSION_DIMINISHMENT;

    public static final ModConfigSpec.IntValue RECEIVER_ANTENNA_APTITUDE;
    public static final ModConfigSpec.IntValue RECEIVER_RECEPTION_POWER;
    public static final ModConfigSpec.IntValue RECEIVER_RECEPTION_FLOOR;

    public static final ModConfigSpec.ConfigValue<String> WIRE_DIMINISHMENT_METHOD;
    public static final ModConfigSpec.DoubleValue WIRE_TRANSMISSION_DIMINISHMENT;

    public static final ModConfigSpec.IntValue ANTENNA_MAX_DISTANCE;

    public static final ModConfigSpec.BooleanValue COMPAT_CREATE;

    static {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        BUILDER.push("Server");

        BUILDER.push("router");

        ROUTER_COMPILE_AMOUNT = BUILDER
                .comment("How many audio samples are compiled per server tick before the router yields")
                .defineInRange("compileAmount", 10, 1, 2000);

        ROUTER_ACTIVITY_FORGIVENESS = BUILDER
                .comment("How many low-activity samples can pass before the active state is allowed to decay")
                .defineInRange("activityForgiveness", 2, 0, 1000);

        ROUTER_ACTIVITY_TIME = BUILDER
                .comment("How long router activity stays active after receiving valid input")
                .defineInRange("activityTime", 20, 0, 1000);

        ROUTER_ACTIVITY_REDSTONE_FACTOR = BUILDER
                .comment("How strongly audio activity maps to redstone output")
                .defineInRange("activityRedstoneFactor", 1500.0, 0.0, 1000000.0);

        ROUTER_FEEDBACK_LISTENING = BUILDER
                .comment("Whether handheld and block radio outputs can be picked up again by nearby listeners")
                .define("feedbackListening", true);

        ROUTER_SOUND_LISTENING = BUILDER
                .comment("Whether world sounds such as jukeboxes are routed into the radio system")
                .define("soundListening", false);

        BUILDER.pop();

        BUILDER.push("frequency");

        FREQUENCY_DECIMAL_PLACES = BUILDER
                .comment("Decimal precision for displayed radio frequencies")
                .defineInRange("decimalPlaces", 2, 0, 5);

        FREQUENCY_DEFAULT_FREQUENCY = BUILDER
                .comment("Default frequency for newly created devices. Use 'auto-generate' to build one from the configured digit layout")
                .define("defaultFrequency", "auto-generate");

        FREQUENCY_CROSS_DIMENSIONAL = BUILDER
                .comment("Whether wireless radio traffic can travel between dimensions")
                .define("crossDimensional", true);

        FREQUENCY_DIMENSIONAL_INTERFERENCE = BUILDER
                .comment("Extra quality loss applied when a signal crosses dimensions")
                .defineInRange("dimensionalInterference", 2.0, 0.0, 100.0);

        FREQUENCY_BASE_FM_INTERFERENCE = BUILDER
                .comment("Minimum FM interference severity before distance-based quality loss is applied")
                .defineInRange("baseFMInterference", 1.0, 0.0, 100.0);

        FREQUENCY_BASE_AM_INTERFERENCE = BUILDER
                .comment("Minimum AM interference severity before distance-based quality loss is applied")
                .defineInRange("baseAMInterference", 15.0, 0.0, 100.0);

        FREQUENCY_PACKET_BUFFER = BUILDER
                .comment("How many decoded voice packets may be buffered per speaker/listener path")
                .defineInRange("packetBuffer", 2, 1, 64);

        BUILDER.pop();

        BUILDER.push("microphone");

        MICROPHONE_LISTENING_RANGE = BUILDER
                .comment("How far a microphone can hear nearby sound sources")
                .defineInRange("listeningRange", 8, 0, 256);

        BUILDER.pop();

        BUILDER.push("speaker");

        SPEAKER_SPEAKING_RANGE = BUILDER
                .comment("How far a speaker block can be heard")
                .defineInRange("speakingRange", 32, 0, 256);

        BUILDER.pop();

        BUILDER.push("radio");

        RADIO_RECEPTION_POWER = BUILDER
                .comment("How strongly placed radios pull in wireless broadcasts")
                .defineInRange("receptionPower", 100, 0, 100000);

        RADIO_RECEPTION_FLOOR = BUILDER
                .comment("How much of the distance penalty a placed radio ignores before quality begins dropping")
                .defineInRange("receptionFloor", 50, 0, 100000);

        RADIO_SPEAKING_RANGE = BUILDER
                .comment("How far audio from a placed radio can be heard")
                .defineInRange("speakingRange", 24, 0, 256);

        BUILDER.pop();

        BUILDER.push("walkieTalkie");

        WALKIE_TALKIE_RECEPTION_POWER = BUILDER
                .comment("How strongly walkie-talkies receive wireless broadcasts")
                .defineInRange("receptionPower", 100, 0, 100000);

        WALKIE_TALKIE_RECEPTION_FLOOR = BUILDER
                .comment("Distance that a walkie-talkie can ignore before reception quality starts dropping")
                .defineInRange("receptionFloor", 20, 0, 100000);

        WALKIE_TALKIE_TRANSMISSION_POWER_FM = BUILDER
                .comment("Base FM transmission strength for walkie-talkies")
                .defineInRange("transmissionPowerFM", 500, 0, 100000);

        WALKIE_TALKIE_DIMINISH_THRESHOLD_FM = BUILDER
                .comment("FM power threshold where walkie-talkie audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdFM", 100, 0, 100000);

        WALKIE_TALKIE_TRANSMISSION_POWER_AM = BUILDER
                .comment("Base AM transmission strength for walkie-talkies")
                .defineInRange("transmissionPowerAM", 900, 0, 100000);

        WALKIE_TALKIE_DIMINISH_THRESHOLD_AM = BUILDER
                .comment("AM power threshold where walkie-talkie audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdAM", 200, 0, 100000);

        WALKIE_TALKIE_TRANSMISSION_DIMINISHMENT = BUILDER
                .comment("How quickly walkie-talkie signal quality falls off over distance")
                .defineInRange("transmissionDiminishment", 1.0, 0.0, 100.0);

        WALKIE_TALKIE_LISTENING_RANGE = BUILDER
                .comment("How far received walkie-talkie audio can be heard from the holder")
                .defineInRange("listeningRange", 4, 0, 256);

        WALKIE_TALKIE_SPEAKING_RANGE = BUILDER
                .comment("How far a walkie-talkie user's transmitted voice can be heard locally")
                .defineInRange("speakingRange", 4, 0, 256);

        BUILDER.pop();

        BUILDER.push("transceiver");

        TRANSCEIVER_RECEPTION_POWER = BUILDER
                .comment("How strongly transceivers receive wireless broadcasts")
                .defineInRange("receptionPower", 350, 0, 100000);

        TRANSCEIVER_RECEPTION_FLOOR = BUILDER
                .comment("Distance that a transceiver can ignore before reception quality starts dropping")
                .defineInRange("receptionFloor", 25, 0, 100000);

        TRANSCEIVER_TRANSMISSION_POWER_FM = BUILDER
                .comment("Base FM transmission strength for transceivers")
                .defineInRange("transmissionPowerFM", 1500, 0, 100000);

        TRANSCEIVER_DIMINISH_THRESHOLD_FM = BUILDER
                .comment("FM power threshold where transceiver audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdFM", 350, 0, 100000);

        TRANSCEIVER_TRANSMISSION_POWER_AM = BUILDER
                .comment("Base AM transmission strength for transceivers")
                .defineInRange("transmissionPowerAM", 1800, 0, 100000);

        TRANSCEIVER_DIMINISH_THRESHOLD_AM = BUILDER
                .comment("AM power threshold where transceiver audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdAM", 300, 0, 100000);

        TRANSCEIVER_TRANSMISSION_DIMINISHMENT = BUILDER
                .comment("How quickly transceiver signal quality falls off over distance")
                .defineInRange("transmissionDiminishment", 1.0, 0.0, 100.0);

        TRANSCEIVER_LISTENING_RANGE = BUILDER
                .comment("How far received transceiver audio can be heard from the holder")
                .defineInRange("listeningRange", 4, 0, 256);

        TRANSCEIVER_SPEAKING_RANGE = BUILDER
                .comment("How far a transceiver user's transmitted voice can be heard locally")
                .defineInRange("speakingRange", 4, 0, 256);

        BUILDER.pop();

        BUILDER.push("transmitter");

        TRANSMITTER_ANTENNA_APTITUDE = BUILDER
                .comment("How much antenna length boosts transmitter reach")
                .defineInRange("antennaAptitude", 10, 0, 100000);

        TRANSMITTER_TRANSMISSION_POWER_FM = BUILDER
                .comment("Base FM transmission strength for placed transmitters")
                .defineInRange("transmissionPowerFM", 3300, 0, 100000);

        TRANSMITTER_DIMINISH_THRESHOLD_FM = BUILDER
                .comment("FM power threshold where placed transmitter audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdFM", 300, 0, 100000);

        TRANSMITTER_TRANSMISSION_POWER_AM = BUILDER
                .comment("Base AM transmission strength for placed transmitters")
                .defineInRange("transmissionPowerAM", 4400, 0, 100000);

        TRANSMITTER_DIMINISH_THRESHOLD_AM = BUILDER
                .comment("AM power threshold where placed transmitter audio quality begins to noticeably degrade")
                .defineInRange("diminishThresholdAM", 500, 0, 100000);

        TRANSMITTER_TRANSMISSION_DIMINISHMENT = BUILDER
                .comment("How quickly placed transmitter signal quality falls off over distance")
                .defineInRange("transmissionDiminishment", 1.0, 0.0, 100.0);

        BUILDER.pop();

        BUILDER.push("receiver");

        RECEIVER_ANTENNA_APTITUDE = BUILDER
                .comment("How much antenna length boosts receiver sensitivity")
                .defineInRange("antennaAptitude", 10, 0, 100000);

        RECEIVER_RECEPTION_POWER = BUILDER
                .comment("How strongly placed receivers pull in wireless broadcasts")
                .defineInRange("receptionPower", 300, 0, 100000);

        RECEIVER_RECEPTION_FLOOR = BUILDER
                .comment("How much of the distance penalty a placed receiver ignores before quality begins dropping")
                .defineInRange("receptionFloor", 20, 0, 100000);

        BUILDER.pop();

        BUILDER.push("wire");

        WIRE_DIMINISHMENT_METHOD = BUILDER
                .comment("How wire quality loss is applied. Allowed values: ADDITIVE or MULTIPLICATIVE")
                .define("diminishmentMethod", "MULTIPLICATIVE", value -> {
                    if (!(value instanceof String stringValue)) {
                        return false;
                    }
                    return "ADDITIVE".equals(stringValue) || "MULTIPLICATIVE".equals(stringValue);
                });

        WIRE_TRANSMISSION_DIMINISHMENT = BUILDER
                .comment("How much quality is lost when audio travels over wire")
                .defineInRange("transmissionDiminishment", 0.01, 0.0, 100.0);

        BUILDER.pop();

        BUILDER.push("antenna");

        ANTENNA_MAX_DISTANCE = BUILDER
                .comment("Maximum antenna chain length that contributes to signal strength")
                .defineInRange("maxDistance", 8, 1, 256);

        BUILDER.pop();

        BUILDER.push("compatibility");

        COMPAT_CREATE = BUILDER
                .comment("Enable Create mod compatibility")
                .define("create", true);

        BUILDER.pop();

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
