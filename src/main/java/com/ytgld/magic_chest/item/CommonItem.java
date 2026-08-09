package com.ytgld.magic_chest.item;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.renderer.Light;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class CommonItem extends BaseItem{
    public CommonItem(Properties properties) {
        super(properties);
    }
    public static int textColor(){
        return Light.ARGB.color(255,100,100,65);
    }
    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255,200,200,130))));
        return co;
    }
    public void renderBack(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height) {
        renderBack(guiGraphics, x, y, width, height,
                Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "tooltip/all/frame"),
                Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "tooltip/all/background"),

                Light.ARGB.color(255, 200,200,130),
                Light.ARGB.color(255, 25,25,16)
        );
    }

    @Override
    public boolean canLight() {
        return false;
    }
}
