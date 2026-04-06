package com.codinglitch.simpleradio;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SimpleRadioServerConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue ROUTER_ACTIVITY_TIME;
    public static final ModConfigSpec.IntValue FREQUENCY_DECIMAL_PLACES;
    // FIX: Added for RadioRouter.java and RadioManager.java
    public static final ModConfigSpec.DoubleValue ROUTER_ACTIVITY_REDSTONE_FACTOR;
    // FIX: Added for CompatCore.java
    public static final ModConfigSpec.BooleanValue COMPAT_CREATE;

    static {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        BUILDER.push("Server");

        ROUTER_ACTIVITY_TIME = BUILDER
                .comment("How long radio activity lasts")
                .defineInRange("router_activity_time", 100, 0, 1000);

        FREQUENCY_DECIMAL_PLACES = BUILDER
                .comment("Decimal precision for frequencies")
                .defineInRange("decimal_places", 2, 0, 5);

        ROUTER_ACTIVITY_REDSTONE_FACTOR = BUILDER
                .comment("Factor for redstone activity calculation")
                .defineInRange("router_activity_redstone_factor", 1.0, 0.0, 15.0);

        COMPAT_CREATE = BUILDER
                .comment("Enable Create mod compatibility")
                .define("compat_create", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}