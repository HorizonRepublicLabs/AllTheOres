package net.allthemods.alltheores.core.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.item.OreHammer;
import net.allthemods.alltheores.common.parts.BlockPartType;

import java.util.Locale;
import java.util.function.Supplier;

public final class ATORegistry {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ATO.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ATO.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ATO.MOD_ID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = ATORegistry.TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(String.format(Locale.ROOT, "creative_tab.%s", ATO.MOD_ID)))
            .icon(() -> Materials.ALUMINUM.get(BlockPartType.STONE_ORE).getHolder().get().asItem().getDefaultInstance())
            .displayItems((param, out) ->
                    ATORegistry.ITEMS.getEntries().stream()
                            .map(Supplier::get)
                            .map(Item::getDefaultInstance)
                            .forEach(out::accept))
            .build()
    );
    
    public static final DeferredHolder<Item, Item> COPPER_ORE_HAMMER = ATORegistry.ITEMS.register("copper_ore_hammer", k -> new OreHammer(new Item.Properties().durability(64).setNoCombineRepair().setId(ResourceKey.create(Registries.ITEM, k))));
    public static final DeferredHolder<Item, Item> IRON_ORE_HAMMER = ATORegistry.ITEMS.register("iron_ore_hammer", k -> new OreHammer(new Item.Properties().durability(96).setNoCombineRepair().setId(ResourceKey.create(Registries.ITEM, k))));
    public static final DeferredHolder<Item, Item> BRONZE_ORE_HAMMER = ATORegistry.ITEMS.register("bronze_ore_hammer", k -> new OreHammer(new Item.Properties().durability(128).setNoCombineRepair().setId(ResourceKey.create(Registries.ITEM, k))));
    public static final DeferredHolder<Item, Item> INVAR_ORE_HAMMER = ATORegistry.ITEMS.register("invar_ore_hammer", k -> new OreHammer(new Item.Properties().durability(192).setNoCombineRepair().setId(ResourceKey.create(Registries.ITEM, k))));
    public static final DeferredHolder<Item, Item> PLATINUM_ORE_HAMMER = ATORegistry.ITEMS.register("platinum_ore_hammer", k -> new OreHammer(new Item.Properties().durability(256).setNoCombineRepair().setId(ResourceKey.create(Registries.ITEM, k))));
    
    
    public static final TagKey<Block> ORES_IN_GROUND_END_STONE = TagKey.create(Registries.BLOCK, ATO.c("ores_in_ground/end_stone"));
    public static final TagKey<Block> ORES_IN_GROUND_ANCIENT_STONE = TagKey.create(Registries.BLOCK, ATO.c("ores_in_ground/ancient_stone"));
    
    public static final TagKey<Item> PLATES = TagKey.create(Registries.ITEM, ATO.c("plates"));
    public static final TagKey<Item> GEARS = TagKey.create(Registries.ITEM, ATO.c("gears"));
    
    public static final TagKey<Item> ORE_HAMMER = TagKey.create(Registries.ITEM, ATO.id("ore_hammers"));
    
    public static void register(IEventBus bus) {
        Materials.init();
        ATORegistry.BLOCKS.register(bus);
        ATORegistry.ITEMS.register(bus);
        ATORegistry.TABS.register(bus);
    }
}
