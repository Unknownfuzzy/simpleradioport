package com.codinglitch.simpleradio;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SimpleRadioClientConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<String> SOME_VARIABLE;
    public static final ModConfigSpec.BooleanValue WIRE_EFFECT;
    // FIX: Added for SimpleRadioClientNetworking.java
    public static final ModConfigSpec.DoubleValue WIRE_EFFECT_TIME;

    // Logic wrapper to keep your existing renderer syntax clean
    public static final WireClient wire = new WireClient();

    public static class WireClient {
        public double baseSag() { return WIRE_BASE_SAG.get(); }
        public double distanceSag() { return WIRE_DISTANCE_SAG.get(); }
    }

    private static ModConfigSpec.DoubleValue WIRE_BASE_SAG;
    private static ModConfigSpec.DoubleValue WIRE_DISTANCE_SAG;

    static {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        BUILDER.push("General");
        SOME_VARIABLE = BUILDER.define("some_variable", "default");

        WIRE_EFFECT = BUILDER
                .comment("Whether to show visual effects on wires")
                .define("wire_effect", true);

        WIRE_EFFECT_TIME = BUILDER
                .comment("Duration of the wire pulse effect")
                .defineInRange("wire_effect_time", 20.0, 1.0, 200.0);
        BUILDER.pop();

        BUILDER.push("Wire Rendering");
        WIRE_BASE_SAG = BUILDER.defineInRange("base_sag", 0.2, 0.0, 1.0);
        WIRE_DISTANCE_SAG = BUILDER.defineInRange("distance_sag", 0.05, 0.0, 1.0);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}