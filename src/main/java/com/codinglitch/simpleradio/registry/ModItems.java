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
    public static final DeferredItem<Item> RADIOSMITHER = ITEMS.register("radiosmithers", () -> new BlockItem(ModBlocks.RADIOSMITHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> RECEIVER = ITEMS.register("receiver", () -> new BlockItem(ModBlocks.RECEIVER.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRANSMITTER = ITEMS.register("transmitter", () -> new BlockItem(ModBlocks.TRANSMITTER.get(), new Item.Properties()));

    // Standalone Items (like the handheld microphone)
    public static final DeferredItem<Item> MICROPHONE = ITEMS.register("microphone", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
