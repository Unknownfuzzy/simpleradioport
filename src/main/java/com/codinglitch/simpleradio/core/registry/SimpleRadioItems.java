package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.core.registry.items.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class SimpleRadioItems {
    // Standard NeoForge DeferredRegister
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CommonSimpleRadio.ID);

    // --- TOOLS & RADIOS ---
    public static final DeferredItem<WireItem> COPPER_WIRE =
            ITEMS.register("copper_wire", () -> new WireItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<TransceiverItem> TRANSCEIVER =
            ITEMS.register("transceiver", () -> new TransceiverItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<WalkieTalkieItem> WALKIE_TALKIE =
            ITEMS.register("walkie_talkie", () -> new WalkieTalkieItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<WalkieTalkieItem> SPUDDIE_TALKIE =
            ITEMS.register("spuddie_talkie", () -> new WalkieTalkieItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<RadioItem> RADIO =
            ITEMS.register("radio", () -> new RadioItem(new Item.Properties()));

    public static final DeferredItem<SpeakerItem> SPEAKER =
            ITEMS.register("speaker", () -> new SpeakerItem(new Item.Properties()));

    public static final DeferredItem<MicrophoneItem> MICROPHONE =
            ITEMS.register("microphone", () -> new MicrophoneItem(new Item.Properties()));

    public static final DeferredItem<ReceiverItem> RECEIVER =
            ITEMS.register("receiver", () -> new ReceiverItem(new Item.Properties()));

    public static final DeferredItem<TransmitterItem> TRANSMITTER =
            ITEMS.register("transmitter", () -> new TransmitterItem(new Item.Properties()));

    public static final DeferredItem<BlockItem> ANTENNA =
            ITEMS.register("antenna", () -> new BlockItem(SimpleRadioBlocks.ANTENNA.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> INSULATOR =
            ITEMS.register("insulator", () -> new BlockItem(SimpleRadioBlocks.INSULATOR.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> RADIOSMITHER =
            ITEMS.register("radiosmither", () -> new BlockItem(SimpleRadioBlocks.RADIOSMITHER.get(), new Item.Properties()));

    public static final DeferredItem<Item> TRANSMITTING_MODULE =
            ITEMS.register("transmitting_module", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RECEIVING_MODULE =
            ITEMS.register("receiving_module", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SPEAKER_MODULE =
            ITEMS.register("speaker_module", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LISTENER_MODULE =
            ITEMS.register("listener_module", () -> new Item(new Item.Properties()));

    public static void reload() {
        // Logic for reloading if necessary
    }
}
