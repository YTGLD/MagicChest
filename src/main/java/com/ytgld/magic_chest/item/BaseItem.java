package com.ytgld.magic_chest.item;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.ytgld.chest_item.event.use.EventMain;
import com.ytgld.chest_item.renderer.MRender;
import com.ytgld.chest_item.renderer.light.Light;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.joml.Matrix3x2fStack;

import java.util.function.Consumer;

public class BaseItem extends Item {
    public BaseItem(Properties properties) {
        super(properties);
    }
    public boolean canLight(){
        return true;
    }
    public boolean asBlackLight(){
        return false;
    }
    public int color(){
        return Light.ARGB.color(255,255,0,0);
    }
    public Identifier identifier(){
        return Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"textures/gui/star.png");
    }

    @Override
    public final void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        this.appendHoverText(itemStack, builder, tooltipFlag);
    }

    public void appendHoverText(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {

    }
    public static void renderLight(GuiGraphicsExtractor guiGraphicsExtractor,
                                   ItemStack itemStack, int x, int y, int seed){
        if (itemStack.getItem() instanceof BaseItem item) {
            if (!item.canLight()) {
                return;
            }
            int time = EventMain.time;
            Identifier identifier = item.identifier();
            Matrix3x2fStack pose  = guiGraphicsExtractor.pose();
            RenderPipeline renderPipeline = MRender.RenderPs.GUI_TEXTURED;
            if (item.asBlackLight()) {
                renderPipeline = MRender.RenderPs.GUI_TEXTURED_BLACK_BlendFunction;
            }
            float rotate = (float) (time / 125f + Math.abs(Math.sin(seed) * 100));
            int color = item.color();
            rotateImage(24,renderPipeline,
                    identifier,
                    guiGraphicsExtractor,pose,rotate,x,y,color);
            int rs = (color >> 16) & 0xFF * 255;
            int gs = (color >> 8) & 0xFF * 255;
            int bs = color & 0xFF * 255;
            rotateImage(16,renderPipeline,
                    Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"textures/gui/all.png"),
                    guiGraphicsExtractor,pose,rotate,x,y,color);
        }
    }

    private static void rotateImage(int imageSize,RenderPipeline renderPipeline,Identifier identifier,
                                    GuiGraphicsExtractor guiGraphicsExtractor,
                                    Matrix3x2fStack pose,float rotate,int x, int y,int color){
        pose.pushMatrix();
        pose.translate(x + 8, y + 8 );
        pose.rotate(rotate);
        pose.translate(- imageSize/2f, -imageSize/2f);
        guiGraphicsExtractor.blit(renderPipeline, identifier,
                0,0,0,0,
                imageSize,imageSize,imageSize,imageSize,
                color);
        pose.popMatrix();
    }
}
