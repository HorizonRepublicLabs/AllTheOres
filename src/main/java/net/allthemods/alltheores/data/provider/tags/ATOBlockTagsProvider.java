package net.allthemods.alltheores.data.provider.tags;

import net.neoforged.neoforge.common.data.BlockTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;

import java.util.concurrent.CompletableFuture;

public class ATOBlockTagsProvider extends BlockTagsProvider {
    
    public ATOBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ATO.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        UniqueTagOutput<Block> output = new UniqueTagOutput<>(this::tag, BuiltInRegistries.BLOCK::getKey);
        Material.forAll(material -> material.createBlockTags(output));
    }
}
