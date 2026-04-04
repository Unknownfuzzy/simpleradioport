package com.codinglitch.simpleradio;

import com.codinglitch.simpleradio.registry.ModBlockEntities;
import com.codinglitch.simpleradio.registry.ModBlocks;
import com.codinglitch.simpleradio.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SimpleRadio.MODID)
public class SimpleRadio {

    public static final String MODID = "simpleradio";
    public static final Logger LOG = LoggerFactory.getLogger(MODID);

    public SimpleRadio(IEventBus modEventBus, ModContainer modContainer) {
        LOG.info("SimpleRadio: Initializing all systems for 1.21.1...");

        // 1. Register Registries (Blocks, Items, and Block Entities)
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // 2. Init Common Logic
        CommonSimpleRadio.init(modEventBus, modContainer);

        // 3. Mod Bus Listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOG.info("SimpleRadio: Common setup complete.");
    }

    /**
     * Adds ALL registered items from your blockstates list to the Creative Menu.
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            // Main Blocks
            event.accept(ModItems.RADIO.get());
            event.accept(ModItems.SPEAKER.get());
            event.accept(ModItems.ANTENNA.get());

            // Tech Blocks from your screenshot
            event.accept(ModItems.FREQUENCER.get());
            event.accept(ModItems.INSULATOR.get());
            event.accept(ModItems.MICROPHONE_BLOCK.get());
            event.accept(ModItems.RADIOSMITHER.get());
            event.accept(ModItems.RECEIVER.get());
            event.accept(ModItems.TRANSMITTER.get());

            // Items
            event.accept(ModItems.TRANSCEIVER.get());
            event.accept(ModItems.COPPER_WIRE.get());
            event.accept(ModItems.DIAMOND_MODULE.get());
            event.accept(ModItems.GOLD_MODULE.get());
            event.accept(ModItems.IRON_MODULE.get());
            event.accept(ModItems.NETHERITE_MODULE.get());
            event.accept(ModItems.LISTENER_MODULE.get());
        }
    }
}
