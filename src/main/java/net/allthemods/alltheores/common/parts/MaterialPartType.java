package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.tags.TagKey;

import net.allthemods.alltheores.common.tags.TagOutput;

public abstract class MaterialPartType<M, P extends MaterialPart<M, P, T>, T extends MaterialPartType<M, P, T>> {
    
    private final TagKey<M> tag;
    
    protected MaterialPartType(TagKey<M> tag) {
        this.tag = tag;
    }
    
    public TagKey<M> getTag() {
        return this.tag;
    }
    
    public abstract TagKey<M> getTag(String group);
    
    public abstract DeferredHolder<M, ? extends M> createHolder(String group);
    
    public void createTags(P part, TagOutput<M> output) {
        output.add(part.getTag(), part.holder.get());
        output.addTag(this.getTag(), part.getTag());
        this.constructTags(part, output);
    }
    
    protected void constructTags(P part, TagOutput<M> output) { }
}
