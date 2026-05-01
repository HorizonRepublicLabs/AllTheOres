package net.allthemods.alltheores.data.provider.enderio;

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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPartType;
import net.allthemods.alltheores.common.parts.ItemPartType;
import net.allthemods.alltheores.data.provider.ATOMaterialHelper;
import net.allthemods.alltheores.core.registry.Materials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ATOEIOSagMillRecipeProvider implements DataProvider {
    private static final int SAG_MILL_ENERGY = 2400;

    private static final Map<Material, Byproduct> RAW_BYPRODUCTS = Map.of(
            Materials.ALUMINUM, new Byproduct(Materials.ZINC, 0.05f),
            Materials.LEAD, new Byproduct(Materials.SILVER, 0.45f),
            Materials.NICKEL, new Byproduct(Materials.PLATINUM, 0.25f),
            Materials.OSMIUM, new Byproduct(Materials.IRIDIUM, 0.30f),
            Materials.PLATINUM, new Byproduct(Materials.GOLD, 0.10f),
            Materials.SILVER, new Byproduct(Materials.LEAD, 0.45f),
            Materials.TIN, new Byproduct(Materials.IRON, 0.20f),
            Materials.URANIUM, new Byproduct(Materials.LEAD, 0.50f),
            Materials.ZINC, new Byproduct(Materials.ALUMINUM, 0.05f),
            Materials.IRIDIUM, new Byproduct(Materials.OSMIUM, 0.30f)
    );

    private final PackOutput.PathProvider recipePath;

    public ATOEIOSagMillRecipeProvider(PackOutput output) {
        this.recipePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "recipe");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        ATOMaterialHelper.applyToAlloy(material -> futures.add(this.save(output, material, "ingot",
                this.direct(material.get(ItemPartType.INGOT).getTag(), this.dustItem(material)))));

        ATOMaterialHelper.applyToIngot(material -> {
            futures.add(this.save(output, material, "raw", this.raw(material)));
            futures.add(this.save(output, material, "ore", this.ore(material)));
            futures.add(this.save(output, material, "ingot",
                    this.direct(material.get(ItemPartType.INGOT).getTag(), this.dustItem(material))));
        });

        ATOMaterialHelper.applyToGem(material -> {
            futures.add(this.save(output, material, "ore", this.gemOre(material)));
            futures.add(this.save(output, material, "gem",
                    this.direct(material.get(ItemPartType.GEM).getTag(), this.dustItem(material))));
        });

        ATOMaterialHelper.applyToDust(material -> futures.add(this.save(output, material, "ore",
                this.direct(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), this.dustItem(material), 6))));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "AllTheOres EnderIO SAG Mill recipes";
    }

    private CompletableFuture<?> save(CachedOutput output, Material material, String name, JsonObject recipe) {
        Identifier id = ATO.id("sag_milling/" + material.getGroup() + "/" + name);
        return DataProvider.saveStable(output, recipe, this.recipePath.json(id));
    }

    private JsonObject raw(Material material) {
        List<JsonObject> outputs = new ArrayList<>();
        Item dust = this.dustItem(material);
        outputs.add(this.output(dust));
        outputs.add(this.output(dust, 1, 0.25f));

        Byproduct byproduct = RAW_BYPRODUCTS.get(material);
        if (byproduct != null) {
            outputs.add(this.output(this.dustItem(byproduct.material()), 1, byproduct.chance()));
        }

        return this.multiply(material.get(ItemPartType.RAW).getTag(), outputs);
    }

    private JsonObject ore(Material material) {
        Item raw = Material.item(material.get(ItemPartType.RAW));
        return this.multiply(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), List.of(
                this.output(raw),
                this.output(raw, 1, 0.33f),
                this.output(Items.COBBLESTONE, 1, 0.15f)
        ));
    }

    private JsonObject gemOre(Material material) {
        return this.multiply(this.itemTag(material.get(BlockPartType.STONE_ORE).getTag()), List.of(
                this.output(Material.item(material.get(ItemPartType.GEM)), 6),
                this.output(Items.COBBLESTONE, 1, 0.15f)
        ));
    }

    private JsonObject direct(TagKey<Item> input, Item output) {
        return this.direct(input, output, 1);
    }

    private JsonObject direct(TagKey<Item> input, Item output, int count) {
        JsonObject recipe = this.base(input, List.of(this.output(output, count)));
        recipe.addProperty("bonus", "none");
        return recipe;
    }

    private JsonObject multiply(TagKey<Item> input, List<JsonObject> outputs) {
        return this.base(input, outputs);
    }

    private JsonObject base(TagKey<Item> input, List<JsonObject> outputs) {
        JsonObject recipe = new JsonObject();
        recipe.add("neoforge:conditions", this.conditions());
        recipe.addProperty("type", "enderio:sag_milling");
        recipe.addProperty("energy", SAG_MILL_ENERGY);

        recipe.addProperty("input", "#" + input.location());

        JsonArray outputJson = new JsonArray();
        outputs.forEach(outputJson::add);
        recipe.add("outputs", outputJson);
        return recipe;
    }

    private JsonArray conditions() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:mod_loaded");
        condition.addProperty("modid", "enderio");

        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        return conditions;
    }

    private JsonObject output(Item item) {
        return this.output(item, 1);
    }

    private JsonObject output(Item item, int count) {
        JsonObject output = new JsonObject();
        output.add("item", this.item(item, count));
        return output;
    }

    private JsonObject output(Item item, int count, float chance) {
        JsonObject output = this.output(item, count);
        output.addProperty("chance", chance);
        return output;
    }

    private JsonObject item(Item item, int count) {
        JsonObject itemJson = new JsonObject();
        itemJson.addProperty("count", count);
        itemJson.addProperty("id", BuiltInRegistries.ITEM.getKey(item).toString());
        return itemJson;
    }

    private Item dustItem(Material material) {
        return Material.item(material.get(ItemPartType.DUST));
    }

    private TagKey<Item> itemTag(TagKey<Block> tag) {
        return TagKey.create(Registries.ITEM, tag.location());
    }

    private record Byproduct(Material material, float chance) {
    }
}
