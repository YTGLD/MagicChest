package com.ytgld.magic_chest.mixin;

import com.ytgld.magic_chest.item.BaseItem;
import com.ytgld.magic_chest.renderer.RenderBlackItem;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public class GuiGraphicsExtractorMixin {
    @Shadow
    @Final
    private Matrix3x2fStack pose;

    @Inject(at = {@At("RETURN")}, method = {"item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V"})
    public void magic$item(LivingEntity owner, Level level, ItemStack itemStack, int x, int y, int seed, CallbackInfo ci) {
        GuiGraphicsExtractor guiGraphicsExtractor = (GuiGraphicsExtractor)(Object) this;
        BaseItem.renderLight(guiGraphicsExtractor,itemStack,x,y,seed);
    }
    @Inject(at = {@At("HEAD")}, method = {"item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V"})
    public void magic$itemBlack(LivingEntity owner, Level level, ItemStack itemStack, int x, int y, int seed, CallbackInfo ci) {
        GuiGraphicsExtractor guiGraphicsExtractor = (GuiGraphicsExtractor)(Object) this;
        RenderBlackItem.renderItem(guiGraphicsExtractor,pose,itemStack,x,y,seed);
    }
}
