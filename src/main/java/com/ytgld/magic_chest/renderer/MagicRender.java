package com.ytgld.magic_chest.renderer;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.ytgld.chest_item.Chestitem;
import com.ytgld.chest_item.renderer.outline.ILevelRendererWarped;
import com.ytgld.chest_item.renderer.outline.IWarped;
import com.ytgld.chest_item.renderer.outline.MFramebufferBlack;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.Identifier;

import static com.mojang.blaze3d.platform.BlendFactor.*;
import static net.minecraft.client.renderer.RenderPipelines.*;

public abstract class MagicRender {

    public static final RenderPipeline TRANSLUCENT_PARTICLE =
            RenderPipeline.builder(
                            RenderPipeline.builder(MATRICES_FOG_SNIPPET)
                                    .withVertexShader(Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"core/particle"))
                                    .withFragmentShader(Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"core/particle"))
                                    .withCull(false)
                                    .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
                                    .withVertexBinding(0, DefaultVertexFormat.PARTICLE)
                                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
                                    .withDepthStencilState(
                                            new DepthStencilState(
                                                    CompareOp.GREATER_THAN_OR_EQUAL,
                                                    false
                                            )
                                    )
                                    .buildSnippet()
                    )
                    .withLocation("pipeline/translucent_particle")
                    .withColorTargetState(
                            new ColorTargetState(
                                    new BlendFunction(
                                            SRC_ALPHA,
                                            ONE,
                                            ONE,
                                            ZERO
                                    )
                            )
                    )
                    .build();
}
