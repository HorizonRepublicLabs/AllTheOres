package net.allthemods.alltheores.datagen.data.recipe.enderio;

import com.enderio.enderio.content.machines.alloy.AlloySmeltingRecipe;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ATOEIOAlloySmelterRecipeProvider extends RecipeProvider {

    private static final int ALLOY_SMELTER_ENERGY = 4800;

    public ATOEIOAlloySmelterRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    private ResourceLocation alloySmeltingDir(String type) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "alloy_smelting/" + type);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Steel
        alloySmelter(new ItemStack(ATORegistry.STEEL.INGOT.get()),
                List.of(SizedIngredient.of(ATORegistry.IRON.INGOT_TAG, 1),
                        SizedIngredient.of(ItemTags.COALS, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Invar
        alloySmelter(new ItemStack(ATORegistry.INVAR.INGOT.get(), 3),
                List.of(SizedIngredient.of(ATORegistry.IRON.INGOT_TAG, 2),
                        SizedIngredient.of(ATORegistry.NICKEL.INGOT_TAG, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Electrum
        alloySmelter(new ItemStack(ATORegistry.ELECTRUM.INGOT.get(), 2),
                List.of(SizedIngredient.of(ATORegistry.GOLD.INGOT_TAG, 1),
                        SizedIngredient.of(ATORegistry.SILVER.INGOT_TAG, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Bronze
        alloySmelter(new ItemStack(ATORegistry.BRONZE.INGOT.get(), 4),
                List.of(SizedIngredient.of(ATORegistry.COPPER.INGOT_TAG, 3),
                        SizedIngredient.of(ATORegistry.TIN.INGOT_TAG, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Brass
        alloySmelter(new ItemStack(ATORegistry.BRASS.INGOT.get(), 4),
                List.of(SizedIngredient.of(ATORegistry.COPPER.INGOT_TAG, 3),
                        SizedIngredient.of(ATORegistry.ZINC.INGOT_TAG, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Enderium
        alloySmelter(new ItemStack(ATORegistry.ENDERIUM.INGOT.get(), 4),
                List.of(SizedIngredient.of(ATORegistry.LEAD.INGOT_TAG, 3),
                        SizedIngredient.of(ATORegistry.PLATINUM.INGOT_TAG, 1),
                        SizedIngredient.of(Tags.Items.ENDER_PEARLS, 2)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Lumium
        alloySmelter(new ItemStack(ATORegistry.LUMIUM.INGOT.get(), 4),
                List.of(SizedIngredient.of(Tags.Items.DUSTS_GLOWSTONE, 4),
                        SizedIngredient.of(ATORegistry.SILVER.INGOT_TAG, 1),
                        SizedIngredient.of(ATORegistry.TIN.INGOT_TAG, 3)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Signalum
        alloySmelter(new ItemStack(ATORegistry.SIGNALUM.INGOT.get(), 4),
                List.of(SizedIngredient.of(ATORegistry.COPPER.INGOT_TAG, 3),
                        SizedIngredient.of(ATORegistry.SILVER.INGOT_TAG, 1),
                        SizedIngredient.of(Tags.Items.DUSTS_REDSTONE, 4)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
        // Constantan
        alloySmelter(new ItemStack(ATORegistry.CONSTANTAN.INGOT.get(), 2),
                List.of(SizedIngredient.of(ATORegistry.COPPER.INGOT_TAG, 1),
                        SizedIngredient.of(ATORegistry.NICKEL.INGOT_TAG, 1)),
                ALLOY_SMELTER_ENERGY, 0.3f, recipeOutput);
    }

    private void alloySmelter(ItemStack output, List<SizedIngredient> inputs, int energy, float experience, RecipeOutput recipeOutput) {
        recipeOutput.withConditions(new ModLoadedCondition("enderio")).accept(alloySmeltingDir(BuiltInRegistries.ITEM.getKey(output.getItem()).getPath()), new AlloySmeltingRecipe(inputs, output, energy, experience), null);
    }
}
