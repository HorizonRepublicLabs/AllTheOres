package net.allthemods.alltheores.data.provider.loot;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPart;
import net.allthemods.alltheores.common.parts.BlockPartType;
import net.allthemods.alltheores.common.parts.ItemPart;
import net.allthemods.alltheores.common.parts.ItemPartType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class ATOBlockLoot extends BlockLootSubProvider {
    private final Map<ResourceKey<LootTable>, LootTable.Builder> tables = new HashMap<>();
    
    protected ATOBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }
    
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        this.tables.forEach(output);
    }
    
    @Override
    protected void generate() {
        Material.forAll(material -> {
            material.apply(BlockPartType.STONE_ORE, p -> this.dropOre(p, material));
            material.apply(BlockPartType.DEEPSLATE_ORE, p -> this.dropOre(p, material));
            material.apply(BlockPartType.NETHER_ORE, p -> this.dropOre(p, material));
            material.apply(BlockPartType.END_ORE, p -> this.dropOre(p, material));
            material.apply(BlockPartType.OTHER_ORE, p -> this.dropOre(p, material));
            
            material.apply(BlockPartType.RAW_BLOCK, b -> this.dropSelf(b.getHolder()));
            material.apply(BlockPartType.BLOCK, b -> this.dropSelf(b.getHolder()));
        });
    }
    
    private void dropOre(BlockPart ore, Material material) {
        if (ore.isVanilla()) return; // don't double generate stuff we already have
        
        ItemPart raw = material.get(ItemPartType.RAW);
        ItemPart gem = material.get(ItemPartType.GEM);
        ItemPart dust = material.get(ItemPartType.DUST);
        if (raw != null) {
            this.add(ore.getHolder().get(), b -> this.createOreDrop(b, raw.getHolder().get()));
        } else if (gem != null) {
            this.add(ore.getHolder().get(), b -> this.createOreDrop(b, gem.getHolder().get()));
        } else if (dust != null) {
            this.add(ore.getHolder().get(), b -> this.createOreDrop(b, dust.getHolder().get()));
        }
    }
    
    private void dropSelf(DeferredHolder<Block, ? extends Block> block) {
        this.dropSelf(block.get());
    }
    
    
    @Override
    protected void add(Block block, LootTable.Builder builder) {
        block.getLootTable().ifPresent(key -> this.tables.put(key, builder));
    }
}
