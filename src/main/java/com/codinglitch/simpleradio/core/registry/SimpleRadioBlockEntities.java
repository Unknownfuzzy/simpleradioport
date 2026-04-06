package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.core.registry.blocks.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CommonSimpleRadio.ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RadiosmitherBlockEntity>> RADIOSMITHER = BLOCK_ENTITIES.register(
            "radiosmither",
            () -> BlockEntityType.Builder.of(RadiosmitherBlockEntity::new, SimpleRadioBlocks.RADIOSMITHER.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RadioBlockEntity>> RADIO = BLOCK_ENTITIES.register(
            "radio",
            () -> BlockEntityType.Builder.of(RadioBlockEntity::new, SimpleRadioBlocks.RADIO.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpeakerBlockEntity>> SPEAKER = BLOCK_ENTITIES.register(
            "speaker",
            () -> BlockEntityType.Builder.of(SpeakerBlockEntity::new, SimpleRadioBlocks.SPEAKER.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MicrophoneBlockEntity>> MICROPHONE = BLOCK_ENTITIES.register(
            "microphone",
            () -> BlockEntityType.Builder.of(MicrophoneBlockEntity::new, SimpleRadioBlocks.MICROPHONE.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TransmitterBlockEntity>> TRANSMITTER = BLOCK_ENTITIES.register(
            "transmitter",
            () -> BlockEntityType.Builder.of(TransmitterBlockEntity::new, SimpleRadioBlocks.TRANSMITTER.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReceiverBlockEntity>> RECEIVER = BLOCK_ENTITIES.register(
            "receiver",
            () -> BlockEntityType.Builder.of(ReceiverBlockEntity::new, SimpleRadioBlocks.RECEIVER.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InsulatorBlockEntity>> INSULATOR = BLOCK_ENTITIES.register(
            "insulator",
            () -> BlockEntityType.Builder.of(InsulatorBlockEntity::new, SimpleRadioBlocks.INSULATOR.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FrequencerBlockEntity>> FREQUENCER = BLOCK_ENTITIES.register(
            "frequencer",
            () -> BlockEntityType.Builder.of(FrequencerBlockEntity::new, SimpleRadioBlocks.FREQUENCER.get()).build(null)
    );

    public static void load() {}
}
