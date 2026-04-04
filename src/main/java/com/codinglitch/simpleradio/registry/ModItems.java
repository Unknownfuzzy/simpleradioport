package com.codinglitch.simpleradio.registry;

import com.codinglitch.simpleradio.SimpleRadio;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleRadio.MODID);

    // Blocks as Items
    public static final DeferredItem<Item> RADIO = ITEMS.register("radio", () -> new BlockItem(ModBlocks.RADIO.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPEAKER = ITEMS.register("speaker", () -> new BlockItem(ModBlocks.SPEAKER.get(), new Item.Properties()));
    public static final DeferredItem<Item> ANTENNA = ITEMS.register("antenna", () -> new BlockItem(ModBlocks.ANTENNA.get(), new Item.Properties()));
    public static final DeferredItem<Item> FREQUENCER = ITEMS.register("frequencer", () -> new BlockItem(ModBlocks.FREQUENCER.get(), new Item.Properties()));
    public static final DeferredItem<Item> INSULATOR = ITEMS.register("insulator", () -> new BlockItem(ModBlocks.INSULATOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> MICROPHONE_BLOCK = ITEMS.register("microphone_block", () -> new BlockItem(ModBlocks.MICROPHONE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> RADIOSMITHER = ITEMS.register("radiosmither", () -> new BlockItem(ModBlocks.RADIOSMITHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> RECEIVER = ITEMS.register("receiver", () -> new BlockItem(ModBlocks.RECEIVER.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRANSMITTER = ITEMS.register("transmitter", () -> new BlockItem(ModBlocks.TRANSMITTER.get(), new Item.Properties()));

    // Standalone Items (like the handheld microphone)
    public static final DeferredItem<Item> TRANSCEIVER = ITEMS.register("transceiver", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_MODULE = ITEMS.register("diamond_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_MODULE = ITEMS.register("gold_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_MODULE = ITEMS.register("iron_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LISTENER_MODULE = ITEMS.register("listener_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_MODULE = ITEMS.register("netherite_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WALKIE_TALKIE = ITEMS.register("walkie_talkie", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPUDDIE_TALKIE = ITEMS.register("spuddie_talkie", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRANSMITTING_MODULE = ITEMS.register("transmitting_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RECEIVING_MODULE = ITEMS.register("receiving_module", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEAKER_MODULE = ITEMS.register("speaker_module", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
