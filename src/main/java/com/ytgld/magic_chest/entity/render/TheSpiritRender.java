package com.ytgld.magic_chest.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.magic_chest.entity.TheSpirit;
import com.ytgld.magic_chest.entity.state.TheSpiritState;
import com.ytgld.magic_chest.item.BaseItem;
import com.ytgld.magic_chest.renderer.Light;
import com.ytgld.magic_chest.renderer.MagicRender;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class TheSpiritRender  extends EntityRenderer<TheSpirit, TheSpiritState> {
    private final ItemModelResolver itemModelResolver;

    public TheSpiritRender(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
        itemModelResolver = p_173917_.getItemModelResolver();

    }

    public boolean shouldRender(TheSpirit livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    public @NotNull TheSpiritState createRenderState() {
        return new TheSpiritState();
    }

    public void extractRenderState(TheSpirit entity, TheSpiritState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
        if (itemModelResolver != null) {
            this.itemModelResolver.updateForNonLiving(reusedState.item,
                    reusedState.entity.getItem(), ItemDisplayContext.FIXED, entity);
        }
    }

    public void submit(TheSpiritState renderState, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        TheSpirit entity = renderState.entity;
        double x = Mth.lerp((double)renderState.partialTick, entity.xOld, entity.getX());
        double y = Mth.lerp((double)renderState.partialTick, entity.yOld, entity.getY());
        double z = Mth.lerp((double)renderState.partialTick, entity.zOld, entity.getZ());
        poseStack.pushPose();
        poseStack.translate(entity.getX() - x, entity.getY() - y, entity.getZ() - z);
        collector.submitCustomGeometry(poseStack, MagicRender.line, (pose, bufferSource) -> this.setT2(pose, entity, bufferSource));

        if (entity.canSee) {
            if (!renderState.item.isEmpty()) {
                poseStack.mulPose(Axis.YP.rotationDegrees(entity.tickCount));
                poseStack.scale(0.25f, 0.25f, 0.25f);
                renderState.item.submit(poseStack, collector, 255, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
            }
        }
        poseStack.popPose();
    }

    private void setT2(PoseStack.Pose matrices, TheSpirit entity, VertexConsumer vertexConsumers) {
        for(int i = 1; i < entity.getTrailPositions().size(); ++i) {
            Vec3 prevPos = (Vec3)entity.getTrailPositions().get(i - 1);
            Vec3 currPos = (Vec3)entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());
            float alpha = (float)i / (float)entity.getTrailPositions().size();
            alpha *= 255.0f;
            if (entity.getItem().getItem() instanceof BaseItem baseItem) {
                int color = baseItem.color();
                int as = (color >> 24) & 0XFF;
                int rs = (color >> 16) & 0XFF;
                int gs = (color >> 8) & 0XFF;
                int bs = color & 0XFF;
                addSquare(vertexConsumers, matrices, adjustedCurrPos, adjustedPrevPos,
                        Light.ARGB.color((int)alpha, rs, gs, bs),
                        (float)i / (float)entity.getTrailPositions().size() * 10f);
            }

        }

    }

    private static void addSquare(VertexConsumer vertexConsumer, PoseStack.Pose poseStack, Vec3 s, Vec3 e, int colorS,float ss) {
        vertexConsumer.addVertex(poseStack, (float)s.x, (float)s.y, (float)s.z).setColor(colorS).setUv2(255, 255).setNormal(1.0F, 0.0F, 0.0F).setLight(255).setOverlay(OverlayTexture.NO_OVERLAY).setLineWidth(ss);
        vertexConsumer.addVertex(poseStack, (float)e.x, (float)e.y, (float)e.z).setColor(colorS).setUv2(255, 255).setNormal(1.0F, 0.0F, 0.0F).setLight(255).setOverlay(OverlayTexture.NO_OVERLAY).setLineWidth(ss);
    }
}

