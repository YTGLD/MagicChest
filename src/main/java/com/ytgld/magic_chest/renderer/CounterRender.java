package com.ytgld.magic_chest.renderer;

import com.ytgld.chest_item.renderer.MRender;
import com.ytgld.chest_item.renderer.light.Light;
import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.init.MagicAttribute;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.joml.Matrix3x2fStack;

public class CounterRender {
    public static int glow;
    public static double lastCounter;
    public static float time = 0.0F;
    public static int alpha = 255;
    public static void tick(ClientTickEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            float max = (float) player.getAttributeValue(MagicAttribute.counter);
            float now =  (float) player.getData(MagicAttribute.counter_data);
            if (max > (double)0.0F) {
                int down = glow - 25;
                if (down < 0) {
                    down = 0;
                }
                glow = down;
                if (lastCounter != now) {
                    glow = 250;
                }
                lastCounter = now;
                ++time;

                if (now >= max) {
                    int aDown = alpha - 25;
                    if (aDown < 0) {
                        aDown = 0;
                    }
                    alpha = aDown;
                }else {
                    alpha = 255;
                }
            }
        }
    }
    public static void renderShield(GuiGraphicsExtractor guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();
        Matrix3x2fStack poseStack = guiGraphics.pose();
        LocalPlayer player = minecraft.player;
        if (player != null && minecraft.level != null && !player.isCreative() && !player.isSpectator()) {
            float max = (float) player.getAttributeValue(MagicAttribute.counter);
            float now =  (float) player.getData(MagicAttribute.counter_data);
            int cooldown =   player.getData(MagicAttribute.counter_data_cooldown);
            if (max <= 0) {
                return;
            }
            float delta = now / max;
            if (delta > 1.0F) {
                delta = 1.0F;
            }

            poseStack.pushMatrix();
            poseStack.translate((float)guiGraphics.guiWidth() / 2.0F + 4f, (float)guiGraphics.guiHeight() / 2 );
            if ((double)delta <= (double)0.25F && delta > 0f) {
                renderDecaySword(guiGraphics,
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_4.png"),
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_4_glow.png"));
            } else if ((double)delta > (double)0.25F && (double)delta <= (double)0.5F) {
                renderDecaySword(guiGraphics,
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_3.png"),
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_3_glow.png"));
            } else if ((double)delta > (double)0.5F && (double)delta <= (double)0.75F) {
                renderDecaySword(guiGraphics,
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_2.png"),
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_2_glow.png"));
            } else if ((double)delta > (double)0.75F && delta <= 1.0F) {
                renderDecaySword(guiGraphics,
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_1.png"),
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_1_glow.png"));
            }

            if (cooldown > 0) {
                int alpha = (int) (Math.abs(Math.sin(time / 20)) * 255);
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                        Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "textures/gui/layer/counter_cooldown.png"), 0, 0, 0.0F, 0.0F,
                        24, 24, 24, 24,
                        Light.ARGB.color(alpha, 255, 255, 255));
            }
            poseStack.popMatrix();
        }
    }
    private static void renderDecaySword(GuiGraphicsExtractor guiGraphics, Identifier resourceLocation, Identifier glowRes) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player != null) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                    resourceLocation, 0, 0, 0.0F, 0.0F, 24, 24, 24, 24,
                    Light.ARGB.color(alpha, 255, 255, 255));
            guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED,
                    glowRes, 0, 0, 0.0F, 0.0F, 24, 24, 24, 24,
                    Light.ARGB.color(Math.min(alpha,glow), 255, 255, 255));
            guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED,
                    resourceLocation, 0, 0, 0.0F, 0.0F, 24, 24, 24, 24,
                    Light.ARGB.color(Math.min(alpha,glow), 255, 255, 255));
        }

    }
}
