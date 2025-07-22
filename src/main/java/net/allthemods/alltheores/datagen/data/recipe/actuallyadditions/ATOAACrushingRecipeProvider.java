package net.allthemods.alltheores.datagen.data.recipe.actuallyadditions;

import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import de.ellpeck.actuallyadditions.data.CrushingRecipeGenerator.CrushingBuilder;
import de.ellpeck.actuallyadditions.mod.crafting.CrushingRecipe;
import net.allthemods.alltheores.content.blocks.sets.ATOSetHelper;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ATOAACrushingRecipeProvider extends RecipeProvider {

    public ATOAACrushingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    private ResourceLocation crushingRecipeDir(String type) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "crushing/" + type);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ATOSetHelper.applyToAlloy(set -> {
            // Ingot -> Dust
            new CrushingBuilder(
                    Ingredient.of(set.INGOT_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 1.0f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_dust", set.name)));
        });

        ATOSetHelper.applyToIngot(set -> {
            // Ore -> Dust
            new CrushingBuilder(
                    Ingredient.of(set.ORES.ORE_BLOCK_ITEM_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 2), 1.0f))
                    .addResult2(new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 0.5f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_ore", set.name)));

            // Raw -> Dust
            CrushingBuilder rawBuilder = new CrushingBuilder(
                    Ingredient.of(set.RAW_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 1.0f)
            );
            if (ATORegistry.getByproducts().containsKey(set.RAW.get())) {
                rawBuilder.addResult2(new CrushingRecipe.CrushingResult(new ItemStack(ATORegistry.getByproducts().get(set.RAW.get()).first, 1), ATORegistry.getByproducts().get(set.RAW.get()).second));
            }
            rawBuilder.save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_raw", set.name)));

            // Raw Block -> 12x Dust
            new CrushingBuilder(
                    Ingredient.of(set.RAW_BLOCK_ITEM_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 12), 1.0f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_raw_block", set.name)));
        });

        ATOSetHelper.applyToGem(set -> {
            // Ore -> Gem
            new CrushingBuilder(
                    Ingredient.of(set.ORES.ORE_BLOCK_ITEM_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.GEM, 6), 1.0f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_ore", set.name)));

            // Gem -> Dust
            new CrushingBuilder(
                    Ingredient.of(set.GEM_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 1.0f))
                    .addResult2(new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 0.5f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_gem", set.name)));
        });

        ATOSetHelper.applyToVanillaIngot(set -> {
            // Ingot -> Dust
            new CrushingBuilder(
                    Ingredient.of(set.INGOT_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 1.0f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_ingot", set.name)));
        });

        ATOSetHelper.applyToVanillaGem(set -> {
            // Gem -> Dust
            new CrushingBuilder(
                    Ingredient.of(set.GEM_TAG),
                    new CrushingRecipe.CrushingResult(new ItemStack(set.DUST, 1), 1.0f))
                    .save(recipeOutput.withConditions(new ModLoadedCondition("actuallyadditions")), crushingRecipeDir(String.format("%s/from_gem", set.name)));
        });
    }
}
