package com.codinglitch.simpleradio;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import com.codinglitch.simpleradio.radio.RadioManager;
import com.codinglitch.simpleradio.core.registry.SimpleRadioCatalysts;
import com.codinglitch.simpleradio.core.registry.SimpleRadioFrequencing;
import com.codinglitch.simpleradio.core.registry.SimpleRadioModules;
import java.util.ServiceLoader;

public class CommonSimpleRadio {
    public static final String ID = "simpleradio";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void initialize() {
        // Force static registries to initialize before items/blocks begin using them.
        SimpleRadioModules.load();
        SimpleRadioCatalysts.load();
        SimpleRadioFrequencing.load();

        // Force API singleton initialization before items/blocks tick or place.
        RadioManager.load();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static <T> T loadService(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst()
                .orElseThrow(() -> new RuntimeException("Failed to load service: " + clazz.getName()));
    }

    public static void info(String message, Object... params) { LOGGER.info(message, params); }
    public static void warn(String message, Object... params) { LOGGER.warn(message, params); }
    public static void error(String message, Object... params) { LOGGER.error(message, params); }
    public static void debug(String message, Object... params) {
        LOGGER.debug(message, params);
    }

}
