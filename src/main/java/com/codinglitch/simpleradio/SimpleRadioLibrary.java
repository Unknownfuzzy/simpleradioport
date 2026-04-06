package com.codinglitch.simpleradio;

import com.codinglitch.simpleradio.client.CommonSimpleRadioClient;
import com.codinglitch.simpleradio.core.SimpleRadioComponents;
import com.codinglitch.simpleradio.core.registry.SimpleRadioBlockEntities;
import com.codinglitch.simpleradio.core.registry.SimpleRadioBlocks;
import com.codinglitch.simpleradio.core.registry.SimpleRadioEntities;
import com.codinglitch.simpleradio.core.registry.SimpleRadioItems;
import com.codinglitch.simpleradio.core.registry.SimpleRadioMenus;
import com.codinglitch.simpleradio.core.registry.SimpleRadioParticles;
import com.codinglitch.simpleradio.core.registry.SimpleRadioSounds;
import com.codinglitch.simpleradio.core.networking.SimpleRadioNetworking;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CommonSimpleRadio.ID)
public class SimpleRadioLibrary {
    public static final ServerConfigBridge SERVER_CONFIG = new ServerConfigBridge();
    public static final ClientConfigBridge CLIENT_CONFIG = new ClientConfigBridge();

    public SimpleRadioLibrary(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(SimpleRadioNetworking::register);
        SimpleRadioBlocks.BLOCKS.register(modEventBus);
        SimpleRadioItems.ITEMS.register(modEventBus);
        SimpleRadioBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        SimpleRadioEntities.ENTITY_TYPES.register(modEventBus);
        SimpleRadioMenus.MENUS.register(modEventBus);
        SimpleRadioMenus.CREATIVE_MODE_TABS.register(modEventBus);
        SimpleRadioParticles.PARTICLES.register(modEventBus);
        SimpleRadioSounds.SOUND_EVENTS.register(modEventBus);
        SimpleRadioComponents.COMPONENTS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, SimpleRadioServerConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, SimpleRadioClientConfig.SPEC);

        CommonSimpleRadio.initialize();
    }

    public static class ClientConfigBridge {
        public final DeviceConfig microphone = new DeviceConfig();
        public final DeviceConfig speaker = new DeviceConfig();
        public final WireConfig wire = new WireConfig();

        // ADDED: These fields are required for the Mixin to compile
        public final SpeedConfig transceiver = new SpeedConfig(0.8);
        public final SpeedConfig walkie_talkie = new SpeedConfig(0.9);

        public static class DeviceConfig {
            public int particleInterval = 5;
        }

        public static class WireConfig {
            public double baseSag = 0.2;
            public double distanceSag = 0.05;
        }

        // ADDED: Helper class for the speed settings
        public static class SpeedConfig {
            public double transceiverSlow;
            public double walkieTalkieSlow;

            public SpeedConfig(double val) {
                this.transceiverSlow = val;
                this.walkieTalkieSlow = val;
            }
        }
    }
}
