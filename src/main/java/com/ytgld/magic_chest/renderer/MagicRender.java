package com.ytgld.magic_chest.renderer;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

import static com.mojang.blaze3d.platform.BlendFactor.*;
import static net.minecraft.client.renderer.RenderPipelines.GLOBALS_SNIPPET;
import static net.minecraft.client.renderer.RenderPipelines.MATRICES_FOG_SNIPPET;

public abstract class MagicRender {


    public static final RenderType line =
            RenderType.create(
                    "lines",
                    RenderSetup.builder(RenderPipeline.builder(RenderPs.renderPipeline)
                                    .withLocation("pipeline/lines_translucent")
                                    .withDepthStencilState(new DepthStencilState(CompareOp.GREATER_THAN_OR_EQUAL, false))
                                    .build())
                            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                            .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                            .createRenderSetup());


    public static class RenderPs {
        public static final RenderPipeline.Snippet  GUI_TEXTURED_SNIPPET = RenderPipeline.builder(GLOBALS_SNIPPET).
                withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION).
                withVertexShader("core/position_tex_color")
                .withFragmentShader("core/position_tex_color")
                .withBindGroupLayout(BindGroupLayouts.SAMPLER0).withColorTargetState(new ColorTargetState(new BlendFunction(
                        SRC_ALPHA,
                        ONE,
                        ONE,
                        ZERO)))
                .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR).withPrimitiveTopology(PrimitiveTopology.QUADS).buildSnippet();

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
        public static final RenderPipeline.Snippet renderPipeline =   RenderPipeline.builder(MATRICES_FOG_SNIPPET)
                .withVertexShader("core/rendertype_lines")
                .withFragmentShader("core/rendertype_lines")
                .withColorTargetState(new ColorTargetState(new BlendFunction(SRC_ALPHA,
                        ONE,
                        ONE,
                        ZERO)))
                .withCull(false)
                .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH)
                .withPrimitiveTopology(PrimitiveTopology.LINES)
                .withDepthStencilState(DepthStencilState.DEFAULT)
                .buildSnippet();

        public static final RenderPipeline GUI_TEXTURED =
                (RenderPipeline.builder(GUI_TEXTURED_SNIPPET).
                        withLocation("pipeline/gui_textured").build());


        public static final RenderPipeline GUI_TEXTURED_BLACK_BlendFunction =
                (RenderPipeline.builder(GUI_TEXTURED_SNIPPET).withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                        .withDepthStencilState(DepthStencilState.DEFAULT)
                        .withLocation(Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"pipeline/gui_textured")).build());

    }
}
