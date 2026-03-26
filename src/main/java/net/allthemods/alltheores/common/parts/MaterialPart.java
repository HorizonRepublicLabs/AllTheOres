package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.tags.TagKey;

import net.allthemods.alltheores.common.tags.TagOutput;

public abstract class MaterialPart<M, P extends MaterialPart<M, P, T>, T extends MaterialPartType<M, P, T>> {
    
    protected final String group;
    protected final T type;
    protected final TagKey<M> tag;
    protected final DeferredHolder<M, ? extends M> holder;
    
    protected final boolean isVanilla;
    
    public MaterialPart(String group, T type, DeferredHolder<M, ? extends M> holder, boolean isVanilla) {
        this.group = group;
        this.type = type;
        this.tag = type.getTag(group);
        this.holder = holder;
        this.isVanilla = isVanilla;
    }
    
    public TagKey<M> getTag() {
        return this.tag;
    }
    
    public DeferredHolder<M, ? extends M> getHolder() {
        return this.holder;
    }
    
    public boolean isVanilla() {
        return this.isVanilla;
    }
    
    @SuppressWarnings("unchecked")
    public void buildTags(TagOutput<M> output) {
        this.type.createTags((P) this, output);
    }
}
