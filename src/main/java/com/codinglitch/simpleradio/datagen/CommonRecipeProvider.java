package com.codinglitch.simpleradio.datagen;

import com.codinglitch.simpleradio.core.registry.SimpleRadioItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;

public class CommonRecipeProvider extends RecipeProvider {

    public CommonRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    public static void defineRecipes(RecipeOutput output) {
        // --- TRANSCEIVER ---
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SimpleRadioItems.TRANSCEIVER.get())
                .define('I', Items.IRON_INGOT)
                .define('Q', Items.AMETHYST_SHARD)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('W', SimpleRadioItems.LISTENER_MODULE.get())
                .define('X', SimpleRadioItems.TRANSMITTING_MODULE.get())
                .define('Y', SimpleRadioItems.SPEAKER_MODULE.get())
                .define('Z', SimpleRadioItems.RECEIVING_MODULE.get())
                .pattern("AWC")
                .pattern("XIZ")
                .pattern("QYQ")
                .unlockedBy("has_transmitting_module", has(SimpleRadioItems.TRANSMITTING_MODULE.get()))
                .unlockedBy("has_speaker_module", has(SimpleRadioItems.SPEAKER_MODULE.get()))
                .unlockedBy("has_receiving_module", has(SimpleRadioItems.RECEIVING_MODULE.get()))
                .save(output);

        // --- WALKIE TALKIE ---
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SimpleRadioItems.WALKIE_TALKIE.get())
                .define('I', Items.IRON_INGOT)
                .define('B', Items.COPPER_BLOCK)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .pattern(" I ")
                .pattern(" B ")
                .pattern(" C ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_copper_block", has(Items.COPPER_BLOCK))
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .save(output);

        // --- SPUDDIE TALKIE ---
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SimpleRadioItems.SPUDDIE_TALKIE.get())
                .define('I', Items.IRON_INGOT)
                .define('P', Items.POTATO)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .pattern(" I ")
                .pattern(" P ")
                .pattern(" C ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .save(output);

        // --- COPPER WIRE ---
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimpleRadioItems.COPPER_WIRE.get(), 2)
                .define('C', Items.COPPER_INGOT)
                .pattern(" C ")
                .pattern("C C")
                .pattern(" C ")
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(output);

        // --- INSULATOR ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.INSULATOR.get())
                .define('I', Items.IRON_INGOT)
                .define('P', ItemTags.PLANKS)
                .pattern("PIP")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .save(output);

        // --- RADIO ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.RADIO.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('Y', SimpleRadioItems.SPEAKER_MODULE.get())
                .define('Z', SimpleRadioItems.RECEIVING_MODULE.get())
                .pattern(" ZA")
                .pattern("RIC")
                .pattern(" Y ")
                .unlockedBy("has_speaker_module", has(SimpleRadioItems.SPEAKER_MODULE.get()))
                .unlockedBy("has_receiving_module", has(SimpleRadioItems.RECEIVING_MODULE.get()))
                .save(output);

        // --- SPEAKER ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.SPEAKER.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('Y', SimpleRadioItems.SPEAKER_MODULE.get())
                .pattern(" Y ")
                .pattern("ICI")
                .pattern("RI ")
                .unlockedBy("has_speaker_module", has(SimpleRadioItems.SPEAKER_MODULE.get()))
                .save(output);

        // --- MICROPHONE ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.MICROPHONE.get())
                .define('I', Items.IRON_INGOT)
                .define('W', ItemTags.WOOL)
                .define('R', Items.REDSTONE)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('L', SimpleRadioItems.LISTENER_MODULE.get())
                .pattern(" W ")
                .pattern("RLC")
                .pattern(" I ")
                .unlockedBy("has_listener_module", has(SimpleRadioItems.LISTENER_MODULE.get()))
                .save(output);

        // --- RECEIVER ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.RECEIVER.get())
                .define('B', Items.IRON_BLOCK)
                .define('Q', Items.QUARTZ)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('R', SimpleRadioItems.RECEIVING_MODULE.get())
                .pattern(" A ")
                .pattern("QBR")
                .pattern(" C ")
                .unlockedBy("has_receiving_module", has(SimpleRadioItems.RECEIVING_MODULE.get()))
                .unlockedBy("has_antenna", has(SimpleRadioItems.ANTENNA.get()))
                .save(output);

        // --- TRANSMITTER ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.TRANSMITTER.get())
                .define('B', Items.IRON_BLOCK)
                .define('Q', Items.QUARTZ)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('T', SimpleRadioItems.TRANSMITTING_MODULE.get())
                .pattern(" A ")
                .pattern("QBT")
                .pattern(" C ")
                .unlockedBy("has_transmitting_module", has(SimpleRadioItems.TRANSMITTING_MODULE.get()))
                .unlockedBy("has_antenna", has(SimpleRadioItems.ANTENNA.get()))
                .save(output);

        // --- ANTENNA ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.ANTENNA.get())
                .define('I', Items.IRON_INGOT)
                .define('B', Items.IRON_BARS)
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" I ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_bars", has(Items.IRON_BARS))
                .save(output);

        // --- RADIOSMITHER ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SimpleRadioItems.RADIOSMITHER.get())
                .define('I', Items.IRON_INGOT)
                .define('A', Items.AMETHYST_SHARD)
                .define('D', Items.POLISHED_DEEPSLATE)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .pattern(" C ")
                .pattern("IAI")
                .pattern("DDD")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_polished_deepslate", has(Items.POLISHED_DEEPSLATE))
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(output);

        // --- MODULES ---
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimpleRadioItems.TRANSMITTING_MODULE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('I', Items.IRON_INGOT)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('P', Items.ENDER_PEARL)
                .pattern(" A ")
                .pattern("CIC")
                .pattern(" P ")
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimpleRadioItems.SPEAKER_MODULE.get())
                .define('I', Items.IRON_INGOT)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('N', Items.NOTE_BLOCK)
                .pattern(" N ")
                .pattern(" I ")
                .pattern(" C ")
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .unlockedBy("has_note_block", has(Items.NOTE_BLOCK))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimpleRadioItems.RECEIVING_MODULE.get())
                .define('A', SimpleRadioItems.ANTENNA.get())
                .define('I', Items.IRON_INGOT)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('M', Items.AMETHYST_SHARD)
                .pattern(" A ")
                .pattern("CIC")
                .pattern(" M ")
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimpleRadioItems.LISTENER_MODULE.get())
                .define('I', Items.IRON_INGOT)
                .define('C', SimpleRadioItems.COPPER_WIRE.get())
                .define('W', ItemTags.WOOL)
                .pattern("W")
                .pattern("I")
                .pattern("C")
                .unlockedBy("has_copper_wire", has(SimpleRadioItems.COPPER_WIRE.get()))
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(output);
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        defineRecipes(output);
    }
}
