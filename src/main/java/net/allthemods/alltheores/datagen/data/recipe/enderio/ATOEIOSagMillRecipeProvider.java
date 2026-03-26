package net.allthemods.alltheores.datagen.data.recipe.enderio;

import com.enderio.enderio.content.machines.sag_mill.SagMillingRecipe;
import net.allthemods.alltheores.content.blocks.sets.ATOSetHelper;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ATOEIOSagMillRecipeProvider extends RecipeProvider {

    private static final int SAG_MILL_ENERGY = 2400;

    public ATOEIOSagMillRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private ResourceLocation sagMillDir(String type) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sag_milling/" + type);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {

        ATOSetHelper.applyToAlloy(set ->
                sagMillDirect(String.format("/%s/ingot", set.name),
                        Ingredient.of(set.INGOT_TAG),
                        output(set.DUST.get(), 1),
                        output)
        );

        ATOSetHelper.applyToIngot(set -> {

            sagMillMultiply(
                    String.format("/%s/raw", set.name),
                    Ingredient.of(set.RAW_TAG),
                    Stream.of(output(set.DUST.get()),
                            output(set.DUST.get(), 0.25f),
                            ATORegistry.getByproducts().containsKey(set.RAW.get())
                                    ? output(ATORegistry.getByproducts().get(set.RAW.get()).first, ATORegistry.getByproducts().get(set.RAW.get()).second)
                                    : null
                    ).filter(Objects::nonNull).toList(),
                    output
            );


            sagMillOre(String.format("/%s/ore", set.name),
                    Ingredient.of(set.ORES.ORE_BLOCK_ITEM_TAG),
                    set.RAW.get(),
                    output);
        });

        ATOSetHelper.applyToGem(set -> {

            sagMillGem(String.format("/%s/ore", set.name),
                    Ingredient.of(set.ORES.ORE_BLOCK_ITEM_TAG),
                    set.GEM.get(),
                    output);

            sagMillDirect(String.format("/%s/gem", set.name),
                    Ingredient.of(set.GEM_TAG),
                    output(set.DUST.get(), 1),
                    output);
        });

        ATOSetHelper.applyToDust(set ->
                sagMillDirect(String.format("/%s/ore", set.name),
                        Ingredient.of(set.ORES.ORE_BLOCK_ITEM_TAG),
                        output(set.DUST.get(), 6),
                        output)
        );
    }

    private void sagMillOre(String name, Ingredient input, Item output, RecipeOutput recipeOutput) {
        sagMillMultiply(name, input, List.of(output(output), output(output, 0.33f), output(Items.COBBLESTONE, 0.15f)), recipeOutput);
    }

    private void sagMillGem(String name, Ingredient input, Item output, RecipeOutput recipeOutput) {
        sagMillMultiply(name, input, List.of(output(output, 6), output(Items.COBBLESTONE, 0.15f)), recipeOutput);
    }

    private void sagMillMultiply(String name, Ingredient input, List<SagMillingRecipe.OutputItem> outputs, RecipeOutput recipeOutput) {
        sagMill(name, input, outputs, SagMillingRecipe.BonusType.MULTIPLY_OUTPUT, recipeOutput);
    }

    private void sagMillDirect(String name, Ingredient input, SagMillingRecipe.OutputItem output, RecipeOutput recipeOutput) {
        sagMill(name, input, List.of(output), SagMillingRecipe.BonusType.NONE, recipeOutput);
    }

    private void sagMill(String name, Ingredient input, List<SagMillingRecipe.OutputItem> outputs, SagMillingRecipe.BonusType bonusType, RecipeOutput recipeOutput) {
        recipeOutput.withConditions(new ModLoadedCondition("enderio")).accept(sagMillDir(name), new SagMillingRecipe(input, outputs, SAG_MILL_ENERGY, bonusType), null);
    }

    private SagMillingRecipe.OutputItem output(ItemLike item) {
        return SagMillingRecipe.OutputItem.of(item.asItem(), 1, 1.0f, false);
    }

    private SagMillingRecipe.OutputItem output(ItemLike item, int count) {
        return SagMillingRecipe.OutputItem.of(item.asItem(), count, 1.0f, false);
    }

    private SagMillingRecipe.OutputItem output(ItemLike item, float chance) {
        return SagMillingRecipe.OutputItem.of(item.asItem(), 1, chance, false);
    }
}
