package com.ytgld.magic_chest.item;

import com.ytgld.chest_item.renderer.light.Light;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public class BaseSoul extends BaseItem{
    private final int color;

    public BaseSoul(Properties properties, int color) {
        super(properties);
        this.color = color;
    }
    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(color)));
        return co;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, builder, tooltipFlag);
        String namePath = BuiltInRegistries.ITEM.getKey(this).getPath();
        int as = (color >> 24) & 0xFF;
        int rs = (color >> 16) & 0xFF;
        int gs = (color >> 8) & 0xFF;
        int bs = color & 0xFF;

        builder.accept(Component.translatable("magic_chest.soul."+namePath).setStyle(Style.EMPTY.withColor(
                Light.ARGB.color(as,rs/3,gs/3,bs/3)
        ).withItalic(true)));
    }

    @Override
    public int color() {
        return color;
    }
}
