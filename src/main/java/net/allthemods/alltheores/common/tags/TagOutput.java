package net.allthemods.alltheores.common.tags;

import net.minecraft.tags.TagKey;

public interface TagOutput<T> {
    
    void add(TagKey<T> tag, T value);
    
    void addTag(TagKey<T> tag, TagKey<T> childTag);
}
