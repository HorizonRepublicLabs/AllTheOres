package net.allthemods.alltheores.data.provider.energizedpower;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPartType;
import net.allthemods.alltheores.common.parts.ItemPartType;
import net.allthemods.alltheores.core.registry.Materials;
import net.allthemods.alltheores.data.provider.ATOMaterialHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ATOEnergizedPowerPulverizerRecipeProvider implements DataProvider {
    private static final List<Double> INGOT_ORE_CHANCES = List.of(1.0, 1.0, 0.25);
    private static final List<Double> INGOT_ORE_ADVANCED_CHANCES = List.of(1.0, 1.0, 0.5, 0.25);
    private static final List<Double> RAW_CHANCES = List.of(1.0, 0.25);
    private static final List<Double> RAW_ADVANCED_CHANCES = List.of(1.0, 0.5);
    private static final List<Double> RAW_BLOCK_CHANCES = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.25);
    private static final List<Double> RAW_BLOCK_ADVANCED_CHANCES = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.75, 0.5, 0.25, 0.25);
    private static final List<Double> GEM_ORE_CHANCES = List.of(1.0, 0.67, 0.17);
    private static final List<Double> GEM_ORE_ADVANCED_CHANCES = List.of(1.0, 0.67, 0.33, 0.17);
    private static final List<Double> SINGLE_CHANCE = List.of(1.0);

    private final PackOutput.PathProvider recipePath;

    public ATOEnergizedPowerPulverizerRecipeProvider(PackOutput output) {
        this.recipePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "recipe");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        ATOMaterialHelper.applyToAlloy(material -> futures.add(this.save(output, material, "ingot",
                this.recipe(material.get(ItemPartType.INGOT).getTag(), this.dustItem(material), SINGLE_CHANCE))));

        ATOMaterialHelper.applyToIngot(material -> {
            if (material == Materials.TIN) return;

            futures.add(this.save(output, material, "ore",
                    this.recipe(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), this.dustItem(material), INGOT_ORE_CHANCES, INGOT_ORE_ADVANCED_CHANCES)));
            futures.add(this.save(output, material, "raw",
                    this.recipe(material.get(ItemPartType.RAW).getTag(), this.dustItem(material), RAW_CHANCES, RAW_ADVANCED_CHANCES)));
            futures.add(this.save(output, material, "raw_block",
                    this.recipe(this.itemTag(material.get(BlockPartType.RAW_BLOCK).getTag()), this.dustItem(material), RAW_BLOCK_CHANCES, RAW_BLOCK_ADVANCED_CHANCES)));
            futures.add(this.save(output, material, "ingot",
                    this.recipe(material.get(ItemPartType.INGOT).getTag(), this.dustItem(material), SINGLE_CHANCE)));
        });

        ATOMaterialHelper.applyToGem(material -> {
            futures.add(this.save(output, material, "ore",
                    this.recipe(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), Material.item(material.get(ItemPartType.GEM)), GEM_ORE_CHANCES, GEM_ORE_ADVANCED_CHANCES)));
            futures.add(this.save(output, material, "gem",
                    this.recipe(material.get(ItemPartType.GEM).getTag(), this.dustItem(material), SINGLE_CHANCE)));
        });

        ATOMaterialHelper.applyToDust(material -> futures.add(this.save(output, material, "ore",
                this.recipe(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), this.dustItem(material), INGOT_ORE_CHANCES, INGOT_ORE_ADVANCED_CHANCES))));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "AllTheOres Energized Power Pulverizer recipes";
    }

    private CompletableFuture<?> save(CachedOutput output, Material material, String name, JsonObject recipe) {
        Identifier id = ATO.id("energized_power/pulverizer/" + material.getGroup() + "/" + name);
        return DataProvider.saveStable(output, recipe, this.recipePath.json(id));
    }

    private JsonObject recipe(TagKey<Item> input, Item output, List<Double> chances) {
        return this.recipe(input, output, chances, chances);
    }

    private JsonObject recipe(TagKey<Item> input, Item output, List<Double> chances, List<Double> advancedChances) {
        JsonObject recipe = new JsonObject();
        recipe.add("neoforge:conditions", this.modLoaded());
        recipe.addProperty("type", "energizedpower:pulverizer");
        recipe.addProperty("ingredient", "#" + input.location());
        recipe.add("result", this.result(output, chances, advancedChances));
        return recipe;
    }

    private JsonObject result(Item item, List<Double> chances, List<Double> advancedChances) {
        JsonObject result = new JsonObject();
        result.add("percentages", this.chances(chances));
        result.add("percentagesAdvanced", this.chances(advancedChances));

        JsonObject itemJson = new JsonObject();
        itemJson.addProperty("id", BuiltInRegistries.ITEM.getKey(item).toString());
        result.add("result", itemJson);
        return result;
    }

    private JsonArray chances(List<Double> chances) {
        JsonArray json = new JsonArray();
        chances.forEach(json::add);
        return json;
    }

    private JsonArray modLoaded() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:mod_loaded");
        condition.addProperty("modid", "energizedpower");

        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        return conditions;
    }

    private Item dustItem(Material material) {
        return Material.item(material.get(ItemPartType.DUST));
    }

    private TagKey<Item> itemTag(TagKey<Block> tag) {
        return TagKey.create(Registries.ITEM, tag.location());
    }
}
