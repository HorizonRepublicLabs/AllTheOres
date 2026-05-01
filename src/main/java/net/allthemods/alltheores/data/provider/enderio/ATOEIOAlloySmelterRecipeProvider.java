package net.allthemods.alltheores.data.provider.enderio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.ItemPartType;
import net.allthemods.alltheores.core.registry.Materials;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ATOEIOAlloySmelterRecipeProvider implements DataProvider {
    private static final int ALLOY_SMELTER_ENERGY = 4800;
    private static final float ALLOY_SMELTER_EXPERIENCE = 0.3f;

    private final PackOutput.PathProvider recipePath;

    public ATOEIOAlloySmelterRecipeProvider(PackOutput output) {
        this.recipePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "recipe");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        futures.add(this.save(output, Materials.STEEL, 1, List.of(
                this.input(Materials.IRON, 1),
                this.input(ItemTags.COALS, 1)
        )));
        futures.add(this.save(output, Materials.INVAR, 3, List.of(
                this.input(Materials.IRON, 2),
                this.input(Materials.NICKEL, 1)
        )));
        futures.add(this.save(output, Materials.ELECTRUM, 2, List.of(
                this.input(Materials.GOLD, 1),
                this.input(Materials.SILVER, 1)
        )));
        futures.add(this.save(output, Materials.BRONZE, 4, List.of(
                this.input(Materials.COPPER, 3),
                this.input(Materials.TIN, 1)
        )));
        futures.add(this.save(output, Materials.BRASS, 4, List.of(
                this.input(Materials.COPPER, 3),
                this.input(Materials.ZINC, 1)
        )));
        futures.add(this.save(output, Materials.ENDERIUM, 4, List.of(
                this.input(Materials.LEAD, 3),
                this.input(Materials.PLATINUM, 1),
                this.input(ATO.c("ender_pearls"), 2)
        )));
        futures.add(this.save(output, Materials.LUMIUM, 4, List.of(
                this.input(ATO.c("dusts/glowstone"), 4),
                this.input(Materials.SILVER, 1),
                this.input(Materials.TIN, 3)
        )));
        futures.add(this.save(output, Materials.SIGNALUM, 4, List.of(
                this.input(Materials.COPPER, 3),
                this.input(Materials.SILVER, 1),
                this.input(ATO.c("dusts/redstone"), 4)
        )));
        futures.add(this.save(output, Materials.CONSTANTAN, 2, List.of(
                this.input(Materials.COPPER, 1),
                this.input(Materials.NICKEL, 1)
        )));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "AllTheOres EnderIO Alloy Smelter recipes";
    }

    private CompletableFuture<?> save(CachedOutput output, Material material, int count, List<JsonObject> inputs) {
        Item item = Material.item(material.get(ItemPartType.INGOT));
        Identifier id = ATO.id("alloy_smelting/" + BuiltInRegistries.ITEM.getKey(item).getPath());
        return DataProvider.saveStable(output, this.alloySmelting(inputs, item, count), this.recipePath.json(id));
    }

    private JsonObject alloySmelting(List<JsonObject> inputs, Item output, int count) {
        JsonObject recipe = new JsonObject();
        recipe.add("neoforge:conditions", this.conditions());
        recipe.addProperty("type", "enderio:alloy_smelting");
        recipe.addProperty("energy", ALLOY_SMELTER_ENERGY);
        recipe.addProperty("experience", ALLOY_SMELTER_EXPERIENCE);

        JsonArray inputJson = new JsonArray();
        inputs.forEach(inputJson::add);
        recipe.add("inputs", inputJson);
        recipe.add("output", this.item(output, count));
        return recipe;
    }

    private JsonObject input(Material material, int count) {
        return this.input(material.get(ItemPartType.INGOT).getTag(), count);
    }

    private JsonObject input(Identifier tag, int count) {
        return this.input(TagKey.create(Registries.ITEM, tag), count);
    }

    private JsonObject input(TagKey<Item> tag, int count) {
        JsonObject input = new JsonObject();
        input.addProperty("count", count);
        input.addProperty("tag", tag.location().toString());
        return input;
    }

    private JsonObject item(Item item, int count) {
        JsonObject itemJson = new JsonObject();
        itemJson.addProperty("count", count);
        itemJson.addProperty("id", BuiltInRegistries.ITEM.getKey(item).toString());
        return itemJson;
    }

    private JsonArray conditions() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:mod_loaded");
        condition.addProperty("modid", "enderio");

        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        return conditions;
    }
}
