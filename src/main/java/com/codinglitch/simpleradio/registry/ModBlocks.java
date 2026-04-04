package com.codinglitch.simpleradio.registry;

import com.codinglitch.simpleradio.SimpleRadio;
import com.codinglitch.simpleradio.block.RadioBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimpleRadio.MODID);

    // Main Blocks
    public static final DeferredBlock<RadioBlock> RADIO = BLOCKS.register("radio", () -> new RadioBlock(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> SPEAKER = BLOCKS.register("speaker", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> ANTENNA = BLOCKS.register("antenna", () -> new Block(BlockBehaviour.Properties.of().strength(3.0f).noOcclusion()));

    // Additional Blocks from your list
    public static final DeferredBlock<Block> FREQUENCER = BLOCKS.register("frequencer", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> INSULATOR = BLOCKS.register("insulator", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> MICROPHONE_BLOCK = BLOCKS.register("microphone", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> RADIOSMITHER = BLOCKS.register("radiosmither", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> RECEIVER = BLOCKS.register("receiver", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> TRANSMITTER = BLOCKS.register("transmitter", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));
    public static final DeferredBlock<Block> TRANSCEIVER = BLOCKS.register("transceiver", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
