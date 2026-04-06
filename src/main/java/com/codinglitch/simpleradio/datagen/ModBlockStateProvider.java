package com.codinglitch.simpleradio.datagen;

import com.codinglitch.simpleradio.core.registry.blocks.RadioBlock;
import com.codinglitch.simpleradio.core.registry.SimpleRadioBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "simpleradio", exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile radioModel = models().getExistingFile(modLoc("block/radio"));

        getVariantBuilder(SimpleRadioBlocks.RADIO.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(radioModel)
                .rotationY((state.getValue(RadioBlock.ROTATION) / 4) * 90)
                .build());

        simpleBlockItem(SimpleRadioBlocks.RADIO.get(), radioModel);
    }
}
