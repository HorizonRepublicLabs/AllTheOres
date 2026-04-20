package net.allthemods.alltheores.common.material;

import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.parts.BlockPart;
import net.allthemods.alltheores.common.parts.BlockPartType;
import net.allthemods.alltheores.common.parts.ItemPart;
import net.allthemods.alltheores.common.parts.ItemPartType;
import net.allthemods.alltheores.common.parts.MaterialPart;
import net.allthemods.alltheores.common.parts.MaterialPartType;
import net.allthemods.alltheores.common.tags.TagOutput;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@SuppressWarnings("DuplicatedCode")
public class Material {
    
    private static final Set<Material> materials = new LinkedHashSet<>();
    
    protected final String group;
    protected final Map<BlockPartType, BlockPart> blocks;
    protected final Map<ItemPartType, ItemPart> items;
    protected final WorldGen worldGen;
    
    private Material(final Builder builder) {
        this.group = builder.group;
        this.blocks = builder.blocks;
        this.items = builder.items;
        this.worldGen = builder.worldGen;
        Material.materials.add(this);
    }
    
    public static Builder builder(String group, TagKey<Block> hardness) {
        return new Builder(group, hardness);
    }
    
    public static Builder builder(String group) {
        return new Builder(group, null);
    }
    
    public static void forAll(Consumer<Material> consumer) {
        Material.materials.forEach(consumer);
    }
    
    public void forEach(Consumer<MaterialPart<?, ?, ?>> consumer) {
        this.blocks.values().forEach(consumer);
        this.items.values().forEach(consumer);
    }
    
    public String getGroup() {
        return this.group;
    }
    
    public WorldGen getWorldGen() {
        return this.worldGen;
    }
    
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredOreFeatureKey() {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ATO.id("ore_" + this.group));
    }
    
    public ResourceKey<PlacedFeature> getPlacedOreFeatureKey() {
        return ResourceKey.create(Registries.PLACED_FEATURE, ATO.id("ore_" + this.group + "_placed"));
    }
    
    public ResourceKey<BiomeModifier> getOverworldBiomeModifierKey() {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATO.id(this.group + "_overworld"));
    }
    
    public ResourceKey<BiomeModifier> getNetherBiomeModifierKey() {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATO.id(this.group + "_nether"));
    }
    
    public ResourceKey<BiomeModifier> getEndBiomeModifierKey() {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATO.id(this.group + "_end"));
    }
    
    public ResourceKey<BiomeModifier> getOtherBiomeModifierKey() {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATO.id(this.group + "_other"));
    }
    
    public boolean has(MaterialPartType<?, ?, ?> type) {
        if (type instanceof BlockPartType block) return this.blocks.containsKey(block);
        if (type instanceof ItemPartType item) return this.items.containsKey(item);
        return false;
    }
    
    public void createBlockTags(TagOutput<Block> output) {
        this.blocks.values().forEach(part -> part.buildTags(output));
    }
    
    public void createItemTags(TagOutput<Item> output) {
        this.items.values().forEach(part -> part.buildTags(output));
        this.blocks.values().forEach(part -> part.buildItemTags(output));
    }
    
    @SuppressWarnings("unchecked")
    public <P extends MaterialPart<?, P, ?>, T extends MaterialPartType<?, P, ?>> P get(T type) {
        if (type instanceof BlockPartType block) return (P) this.blocks.get(block);
        if (type instanceof ItemPartType item) return (P) this.items.get(item);
        return null;
    }
    
    public static <P extends MaterialPart<?, P, ?>> Item item(P part) {
        if (part instanceof BlockPart block) return block.getHolder().get().asItem();
        if (part instanceof ItemPart item) return item.getHolder().get().asItem();
        throw new IllegalArgumentException("Unknown type " + part);
    }
    
    public <A extends MaterialPart<?, A, ?>> void apply(
            MaterialPartType<?, A, ?> a,
            Consumer<A> consumer
    ) {
        A partA = this.get(a);
        if (partA == null) return;
        consumer.accept(partA);
    }
    
    public <
            A extends MaterialPart<?, A, ?>,
            B extends MaterialPart<?, B, ?>
            > void apply(
            MaterialPartType<?, A, ?> a,
            MaterialPartType<?, B, ?> b,
            BiConsumer<A, B> consumer
    ) {
        A partA = this.get(a);
        if (partA == null) return;
        B partB = this.get(b);
        if (partB == null) return;
        consumer.accept(partA, partB);
    }
    
    public <
            A extends MaterialPart<?, A, ?>,
            B extends MaterialPart<?, B, ?>,
            C extends MaterialPart<?, C, ?>
            > void apply(
            MaterialPartType<?, A, ?> a,
            MaterialPartType<?, B, ?> b,
            MaterialPartType<?, C, ?> c,
            TriConsumer<A, B, C> consumer
    ) {
        A partA = this.get(a);
        if (partA == null) return;
        B partB = this.get(b);
        if (partB == null) return;
        C partC = this.get(c);
        if (partC == null) return;
        consumer.accept(partA, partB, partC);
    }
    
    public <
            A extends MaterialPart<?, A, ?>,
            B extends MaterialPart<?, B, ?>,
            C extends MaterialPart<?, C, ?>,
            D extends MaterialPart<?, D, ?>
            > void apply(
            MaterialPartType<?, A, ?> a,
            MaterialPartType<?, B, ?> b,
            MaterialPartType<?, C, ?> c,
            MaterialPartType<?, D, ?> d,
            QuadConsumer<A, B, C, D> consumer
    ) {
        A partA = this.get(a);
        if (partA == null) return;
        B partB = this.get(b);
        if (partB == null) return;
        C partC = this.get(c);
        if (partC == null) return;
        D partD = this.get(d);
        if (partD == null) return;
        consumer.accept(partA, partB, partC, partD);
    }
    
    public <
            A extends MaterialPart<?, A, ?>,
            B extends MaterialPart<?, B, ?>,
            C extends MaterialPart<?, C, ?>,
            D extends MaterialPart<?, D, ?>,
            E extends MaterialPart<?, E, ?>
            > void apply(
            MaterialPartType<?, A, ?> a,
            MaterialPartType<?, B, ?> b,
            MaterialPartType<?, C, ?> c,
            MaterialPartType<?, D, ?> d,
            MaterialPartType<?, E, ?> e,
            PentaConsumer<A, B, C, D, E> consumer
    ) {
        A partA = this.get(a);
        if (partA == null) return;
        B partB = this.get(b);
        if (partB == null) return;
        C partC = this.get(c);
        if (partC == null) return;
        D partD = this.get(d);
        if (partD == null) return;
        E partE = this.get(e);
        if (partE == null) return;
        consumer.accept(partA, partB, partC, partD, partE);
    }
    
    @FunctionalInterface
    public interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }
    
    @FunctionalInterface
    public interface QuadConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }
    
    @FunctionalInterface
    public interface PentaConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }
    
    public static class Builder {
        
        private final String group;
        private final TagKey<Block> hardness;
        private final Map<BlockPartType, BlockPart> blocks = new LinkedHashMap<>();
        private final Map<ItemPartType, ItemPart> items = new LinkedHashMap<>();
        private WorldGen worldGen;
        
        private Builder(String group, TagKey<Block> hardness) {
            this.group = group;
            this.hardness = hardness;
        }
        
        public Builder add(BlockPartType type) {
            this.addBlock(type, type.create(this.group, this.hardness));
            return this;
        }
        
        public Builder add(BlockPartType type, UnaryOperator<BlockBehaviour.Properties> factory) {
            this.addBlock(type, type.create(this.group, this.hardness, factory));
            return this;
        }
        
        public Builder add(BlockPartType type, Block block) {
            this.addBlock(type, BlockPart.fromVanilla(this.group, this.hardness, type, block));
            return this;
        }
        
        public Builder add(ItemPartType type) {
            this.addItem(type, type.create(this.group));
            return this;
        }
        
        public Builder add(ItemPartType type, Item item) {
            this.addItem(type, ItemPart.fromVanilla(this.group, type, item));
            return this;
        }
        
        public Builder addAll(BlockPartType... types) {
            Arrays.stream(types).forEach(this::add);
            return this;
        }
        
        public Builder addAll(ItemPartType... types) {
            Arrays.stream(types).forEach(this::add);
            return this;
        }
        
        public Builder worldgen(int veinSize, int count, int minY, int maxY) {
            this.worldGen = new WorldGen(veinSize, count, minY, maxY);
            return this;
        }
        
        public Material build() {
            return new Material(this);
        }
        
        private void addBlock(BlockPartType type, BlockPart part) {
            if (this.blocks.putIfAbsent(type, part) != null) {
                throw new IllegalStateException("Duplicate block part " + type + " for material " + this.group);
            }
        }
        
        private void addItem(ItemPartType type, ItemPart part) {
            if (this.items.putIfAbsent(type, part) != null) {
                throw new IllegalStateException("Duplicate item part " + type + " for material " + this.group);
            }
        }
    }
    
    public record WorldGen(int veinSize, int count, int minY, int maxY) { }
}
