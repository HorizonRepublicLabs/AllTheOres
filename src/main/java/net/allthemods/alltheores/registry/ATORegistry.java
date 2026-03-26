package net.allthemods.alltheores.registry;

import com.ibm.icu.impl.Pair;
import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATOAlloySet;
import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATODustSet;
import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATOGemSet;
import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATOIngotSet;
import net.allthemods.alltheores.content.blocks.sets.vanilla_sets.VanillaDebrisSet;
import net.allthemods.alltheores.content.blocks.sets.vanilla_sets.VanillaGemSet;
import net.allthemods.alltheores.content.blocks.sets.vanilla_sets.VanillaIngotSet;
import net.allthemods.alltheores.content.items.OreHammer;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Map;

public class ATORegistry {

    public static final DeferredRegister<Block> BLOCKS = ATORegisters.BLOCKS;
    public static final DeferredRegister<Item> ITEMS = ATORegisters.ITEMS;

    public static final DeferredRegister<Block> FLUID_BLOCKS = ATORegisters.FLUID_BLOCKS;
    public static final DeferredRegister<Item> FLUID_ITEMS = ATORegisters.FLUID_ITEMS;
    public static final DeferredRegister<Fluid> FLUIDS = ATORegisters.FLUIDS;
    public static final DeferredRegister<FluidType> FLUID_TYPES = ATORegisters.FLUID_TYPES;

    /*
     * Checklist for adding new Resources:
     * For all: Textures obviously :D
     * For Ingots: Define a Byproduct in the map at the bottom (if not added it will datagen a recipe without byproduct)
     * For Alloys: Add a recipe in the following Datagenerators:
     * - ATORecipeProvider (AllTheOres)
     * - ATOAlloySmelterRecipeProvider (EnderIO)
     * - ATOMultiBlockRecipeProvider (Immersive Engineering)
     * For Gems: Nothing, a new entry does everything for you
     * For Dusts: Nothing, a new entry does everything for you
     * */

    public static final ATOIngotSet ALUMINUM = new ATOIngotSet("aluminum", 0xFFE3E3E3, "stone", 24, -64, 320, 4);
    public static final ATOIngotSet LEAD = new ATOIngotSet("lead", 0xFF7C8CC6, "stone", 24, -64, 56, 3);
    public static final ATOIngotSet NICKEL = new ATOIngotSet("nickel", 0xFFA9A984, "iron", 24, -64, 56, 3);
    public static final ATOIngotSet OSMIUM = new ATOIngotSet("osmium", 0xFFC0C9DD, "diamond", 24, -64, 56, 3);
    public static final ATOIngotSet PLATINUM = new ATOIngotSet("platinum", 0xFFB5B5FF, "diamond", 24, -64, 36, 1);
    public static final ATOIngotSet SILVER = new ATOIngotSet("silver", 0xFFA4E0E7, "stone", 24, -64, 56, 3);
    public static final ATOIngotSet TIN = new ATOIngotSet("tin", 0xFF787878, "stone", 24, -64, 196, 4);
    public static final ATOIngotSet URANIUM = new ATOIngotSet("uranium", 0xFF7EE778, "iron", 24, -64, 16, 2);
    public static final ATOIngotSet ZINC = new ATOIngotSet("zinc", 0xFFB5B5B5, "stone", 24, -64, 126, 3);
    public static final ATOIngotSet IRIDIUM = new ATOIngotSet("iridium", 0xFFC0C0C0, "diamond", 10, -64, 32, 4);

    public static final ATOAlloySet STEEL = new ATOAlloySet("steel", 0xFF696969);
    public static final ATOAlloySet INVAR = new ATOAlloySet("invar", 0xFFCFCFCF);
    public static final ATOAlloySet ELECTRUM = new ATOAlloySet("electrum", 0xFFEFE5B2);
    public static final ATOAlloySet BRONZE = new ATOAlloySet("bronze", 0xFFD98A3D);
    public static final ATOAlloySet BRASS = new ATOAlloySet("brass", 0xFFF4BA45);
    public static final ATOAlloySet ENDERIUM = new ATOAlloySet("enderium", 0xFF0E6464);
    public static final ATOAlloySet LUMIUM = new ATOAlloySet("lumium", 0xFFFFDA7E);
    public static final ATOAlloySet SIGNALUM = new ATOAlloySet("signalum", 0xFFDB7F15);
    public static final ATOAlloySet CONSTANTAN = new ATOAlloySet("constantan", 0xFFDAB38E);

    public static final ATOGemSet RUBY = new ATOGemSet("ruby", "stone", 7, -64, 228, 3);
    public static final ATOGemSet PERIDOT = new ATOGemSet("peridot", "stone", 7, -64, 196, 3);
    public static final ATOGemSet SAPPHIRE = new ATOGemSet("sapphire", "stone", 7, -64, 296, 3);
    public static final ATOGemSet CINNABAR = new ATOGemSet("cinnabar", "iron", 27, -64, 228, 3);
    public static final ATOGemSet FLUORITE = new ATOGemSet("fluorite", "iron", 27, -64, 228, 3);

    public static final ATODustSet SALT = new ATODustSet("salt", "stone", 27, -64, 228, 3);
    public static final ATODustSet SULFUR = new ATODustSet("sulfur", "iron", 17, -64, 25, 1);

    public static final DeferredHolder<Item, Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));

    public static final VanillaIngotSet COPPER = new VanillaIngotSet("copper", 0xFFC66740, Items.RAW_COPPER, Blocks.RAW_COPPER_BLOCK, Items.COPPER_INGOT, Blocks.COPPER_BLOCK);
    public static final VanillaIngotSet IRON = new VanillaIngotSet("iron", 0xFFC49C6D, Items.RAW_IRON, Blocks.RAW_IRON_BLOCK, Items.IRON_INGOT, Blocks.IRON_BLOCK);
    public static final VanillaIngotSet GOLD = new VanillaIngotSet("gold", 0xFFE2B928, Items.RAW_GOLD, Blocks.RAW_GOLD_BLOCK, Items.GOLD_INGOT, Blocks.GOLD_BLOCK);
    public static final VanillaGemSet DIAMOND = new VanillaGemSet("diamond", Items.DIAMOND, Blocks.DIAMOND_BLOCK);
    public static final VanillaDebrisSet NETHERITE = new VanillaDebrisSet("netherite", 0xFF262626, Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP, Items.NETHERITE_INGOT, Blocks.NETHERITE_BLOCK);

    public static final DeferredHolder<Item, Item> COPPER_ORE_HAMMER = ITEMS.register("copper_ore_hammer", () -> new OreHammer(new Item.Properties(), 64));
    public static final DeferredHolder<Item, Item> IRON_ORE_HAMMER = ITEMS.register("iron_ore_hammer", () -> new OreHammer(new Item.Properties(), 96));
    public static final DeferredHolder<Item, Item> BRONZE_ORE_HAMMER = ITEMS.register("bronze_ore_hammer", () -> new OreHammer(new Item.Properties(), 128));
    public static final DeferredHolder<Item, Item> INVAR_ORE_HAMMER = ITEMS.register("invar_ore_hammer", () -> new OreHammer(new Item.Properties(), 192));
    public static final DeferredHolder<Item, Item> PLATINUM_ORE_HAMMER = ITEMS.register("platinum_ore_hammer", () -> new OreHammer(new Item.Properties(), 256));

    // If you don't define an entry here the recipe will be generated without a byproduct
    // This setup is kina weird, but it'll crash with a NullPointer if you define it directly
    public static Map<ItemLike, Pair<ItemLike, Float>> getByproducts() {
        return Map.of(
                ALUMINUM.RAW.get(), Pair.of(ALUMINUM.DUST.get(), 0.05f),
                LEAD.RAW.get(), Pair.of(LEAD.DUST.get(), 0.45f),
                NICKEL.RAW.get(), Pair.of(NICKEL.DUST.get(), 0.25f),
                OSMIUM.RAW.get(), Pair.of(OSMIUM.DUST.get(), 0.3f),
                PLATINUM.RAW.get(), Pair.of(PLATINUM.DUST.get(), 0.1f),
                SILVER.RAW.get(), Pair.of(SILVER.DUST.get(), 0.45f),
                TIN.RAW.get(), Pair.of(TIN.DUST.get(), 0.2f),
                URANIUM.RAW.get(), Pair.of(URANIUM.DUST.get(), 0.5f),
                ZINC.RAW.get(), Pair.of(ZINC.DUST.get(), 0.05f),
                IRIDIUM.RAW.get(), Pair.of(IRIDIUM.DUST.get(), 0.3f)
        );
    }

    public static void init() {
        // Just a placeholder to ensure the class is loaded during mod initialization
    }
    // ###################### HELPER METHODS

    public static DeferredHolder<Item, BlockItem> blockItem(String path, DeferredHolder<Block, Block> block) {
        return ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static DeferredHolder<Item, Item> item(String path) {
        return ITEMS.register(path, () -> new Item(new Item.Properties()));
    }

}

