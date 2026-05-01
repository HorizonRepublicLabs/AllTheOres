package net.allthemods.alltheores.data.provider;

import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPartType;
import net.allthemods.alltheores.common.parts.ItemPartType;

import java.util.function.Consumer;

public final class ATOMaterialHelper {
    
    private ATOMaterialHelper() { }
    
    public static void applyToAlloy(Consumer<Material> consumer) {
        Material.forAll(material -> {
            if (isAlloy(material)) consumer.accept(material);
        });
    }
    
    public static void applyToIngot(Consumer<Material> consumer) {
        Material.forAll(material -> {
            if (isIngot(material)) consumer.accept(material);
        });
    }
    
    public static void applyToGem(Consumer<Material> consumer) {
        Material.forAll(material -> {
            if (isGem(material)) consumer.accept(material);
        });
    }
    
    public static void applyToDust(Consumer<Material> consumer) {
        Material.forAll(material -> {
            if (isDust(material)) consumer.accept(material);
        });
    }
    
    private static boolean isAlloy(Material material) {
        return material.has(ItemPartType.INGOT)
                && material.has(ItemPartType.DUST)
                && !material.has(ItemPartType.RAW)
                && !material.has(ItemPartType.GEM)
                && !material.get(ItemPartType.INGOT).isVanilla();
    }
    
    private static boolean isIngot(Material material) {
        return material.has(ItemPartType.RAW)
                && material.has(ItemPartType.INGOT)
                && material.has(ItemPartType.DUST)
                && material.has(BlockPartType.STONE_ORE)
                && !material.get(ItemPartType.RAW).isVanilla()
                && !material.get(ItemPartType.INGOT).isVanilla();
    }
    
    private static boolean isGem(Material material) {
        return material.has(ItemPartType.GEM)
                && material.has(ItemPartType.DUST)
                && material.has(BlockPartType.STONE_ORE)
                && !material.get(ItemPartType.GEM).isVanilla();
    }
    
    private static boolean isDust(Material material) {
        return !material.has(ItemPartType.INGOT)
                && !material.has(ItemPartType.GEM)
                && material.has(ItemPartType.DUST)
                && material.has(BlockPartType.STONE_ORE)
                && !material.get(ItemPartType.DUST).isVanilla();
    }
}
