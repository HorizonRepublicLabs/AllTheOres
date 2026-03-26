package net.allthemods.alltheores.common.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import net.allthemods.alltheores.core.registry.ATORegistry;

import org.jspecify.annotations.Nullable;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

public class OreHammer extends Item {
    
    public OreHammer(Properties properties) {
        super(properties);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        int durability = stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
        int damage = stack.getOrDefault(DataComponents.DAMAGE, 0);
        if (durability <= 0 || damage <= 0) return ; 
        builder.accept(Component.literal(String.format(Locale.ROOT, "[%d/%d]", durability - damage, durability)));
    }
    
    @Override
    public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        if (!instance.typeHolder().is(ATORegistry.ORE_HAMMER)) return null;
        
        ItemStack stack = switch (instance) {
            case ItemStack itemStack -> itemStack.copyWithCount(1);
            case ItemStackTemplate template -> template.create();
            default -> ItemStack.EMPTY;
        };
        
        stack.setDamageValue(stack.getDamageValue() + 1);
        if (stack.isEmpty() || stack.isBroken()) return null;
        
        return ItemStackTemplate.fromNonEmptyStack(stack);
    }
}
