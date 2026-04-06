package com.codinglitch.simpleradio;

import com.codinglitch.simpleradio.datagen.CommonRecipeProvider;
import com.codinglitch.simpleradio.datagen.ModBlockStateProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@EventBusSubscriber(modid = "simpleradio", bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // This is where you add your providers
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(),
                new CommonRecipeProvider(packOutput, event.getLookupProvider()));
    }
}
