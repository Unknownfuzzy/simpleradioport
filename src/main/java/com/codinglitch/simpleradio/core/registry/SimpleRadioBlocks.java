package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.core.registry.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CommonSimpleRadio.ID);

    public static final DeferredBlock<RadiosmitherBlock> RADIOSMITHER = BLOCKS.register("radiosmither",
            () -> new RadiosmitherBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.DEEPSLATE).pushReaction(PushReaction.IGNORE))
    );
    public static final DeferredBlock<RadioBlock> RADIO = BLOCKS.register("radio",
            () -> new RadioBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );
    public static final DeferredBlock<SpeakerBlock> SPEAKER = BLOCKS.register("speaker",
            () -> new SpeakerBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );
    public static final DeferredBlock<MicrophoneBlock> MICROPHONE = BLOCKS.register("microphone",
            () -> new MicrophoneBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );

    public static final DeferredBlock<TransmitterBlock> TRANSMITTER = BLOCKS.register("transmitter",
            () -> new TransmitterBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );
    public static final DeferredBlock<ReceiverBlock> RECEIVER = BLOCKS.register("receiver",
            () -> new ReceiverBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );

    public static final DeferredBlock<FrequencerBlock> FREQUENCER = BLOCKS.register("frequencer",
            () -> new FrequencerBlock(Block.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL))
    );

    public static final DeferredBlock<AntennaBlock> ANTENNA = BLOCKS.register("antenna",
            () -> new AntennaBlock(Block.Properties.of().strength(2.0F, 4.0F).sound(SoundType.METAL).instabreak())
    );

    public static final DeferredBlock<InsulatorBlock> INSULATOR = BLOCKS.register("insulator",
            () -> new InsulatorBlock(Block.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).instabreak())
    );
}
