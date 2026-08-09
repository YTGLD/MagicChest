package com.ytgld.magic_chest.mixin;

import com.ytgld.magic_chest.init.MagicItems;
import com.ytgld.magic_chest.item.BaseItem;
import com.ytgld.magic_chest.renderer.RenderBlackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2ic;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {
    @Shadow
    @Final
    private Matrix3x2fStack pose;

    @Shadow
    public abstract int guiWidth();

    @Shadow
    public abstract int guiHeight();

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private GuiRenderState guiRenderState;

    @Shadow
    public abstract @Nullable ScreenRectangle peekScissorStack();

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
    @Inject(at = @At(value = "RETURN"), method = "tooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/Identifier;Lnet/minecraft/world/item/ItemStack;)V")
    public void ytgld$ClientTooltipPositioner(Font font, List<ClientTooltipComponent> components, int x, int y, ClientTooltipPositioner positioner, Identifier background, ItemStack tooltipStack, CallbackInfo ci) {
        if (tooltipStack.getItem() instanceof BaseItem){
            RenderTooltipEvent.Pre preEvent = ClientHooks.onRenderTooltipPre(tooltipStack, (GuiGraphicsExtractor) (Object) this, x, y, this.guiWidth(), this.guiHeight(), components, font, positioner);
            if (!preEvent.isCanceled()) {
                font = preEvent.getFont();
                x = preEvent.getX();
                y = preEvent.getY();
                int i = 0;
                int j = components.size() == 1 ? -2 : 0;

                ClientTooltipComponent clienttooltipcomponent;
                for (Iterator<ClientTooltipComponent> var11 = components.iterator(); var11.hasNext(); j += clienttooltipcomponent.getHeight(font)) {
                    clienttooltipcomponent = var11.next();
                    int k = clienttooltipcomponent.getWidth(font);
                    if (k > i) {
                        i = k;
                    }
                }
                Vector2ic vector2ic = positioner.positionTooltip(this.guiWidth(), this.guiHeight(), x, y, i, j);
                int l = vector2ic.x();
                int i1 = vector2ic.y();
                if (tooltipStack.getItem() instanceof BaseItem item){
                    this.pose.pushMatrix();
                    item.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
            }
        }
    }
    @Inject(
            at = {@At("HEAD")},
            method = {"item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V"},
            cancellable = true
    )
    public void IGUILight(LivingEntity owner, Level level, ItemStack itemStack, int x, int y, int seed, CallbackInfo ci) {
        if (itemStack.is(MagicItems.DecaySickle_.asItem())) {
            ci.cancel();
            TrackingItemStackRenderState itemStackRenderState = new TrackingItemStackRenderState();
            this.minecraft.getItemModelResolver().updateForTopItem(itemStackRenderState, MagicItems.DecaySickle_Small.asItem().getDefaultInstance(), ItemDisplayContext.GUI, level, owner, seed);
            this.guiRenderState.addItem(new GuiItemRenderState(new Matrix3x2f(this.pose), itemStackRenderState, x, y, this.peekScissorStack()));
        }
    }
}
