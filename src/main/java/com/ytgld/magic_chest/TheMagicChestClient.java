package com.ytgld.magic_chest;

import com.ytgld.chest_item.renderer.BlackShieldRenderHandler;
import com.ytgld.chest_item.renderer.ShieldRenderHandler;
import com.ytgld.magic_chest.entity.render.TheSpiritRender;
import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.jei.ModRecipeCache;
import com.ytgld.magic_chest.other.GenMagicSounds;
import com.ytgld.magic_chest.other.ToolTipSpiritItem;
import com.ytgld.magic_chest.renderer.CounterRender;
import com.ytgld.magic_chest.renderer.RenderBlackItem;
import com.ytgld.magic_chest.renderer.model.BigModel;
import com.ytgld.magic_chest.renderer.particle.has_opt.CubeParticle;
import com.ytgld.magic_chest.renderer.particle.has_opt.MagicChestParticle;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Function;

@Mod(value = TheMagicChest.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TheMagicChest.MODID, value = Dist.CLIENT)
public class TheMagicChestClient {
    public TheMagicChestClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MagicParticles.colorOption.get(), MagicChestParticle.Provider::new);
        event.registerSpriteSet(MagicParticles.colorCube.get(), CubeParticle.Provider::new);
    }
    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Pre event) {
        RenderBlackItem.clientTick(event);
    }
    @SubscribeEvent
    public static void RegisterRenderPipelinesEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MagicEntitys.TheSpirit_.get(), TheSpiritRender::new);
    }
    @SubscribeEvent
    public static void registerItemModels(RegisterItemModelsEvent event) {
        event.register(Identifier.fromNamespaceAndPath(TheMagicChest.MODID,"big_model"),
                BigModel.Unbaked.MAP_CODEC);
    }
    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.TITLE,
                Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "counter"),
                (guiGraphics, tracker) -> CounterRender.renderShield(guiGraphics));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(GenMagicSounds::new);
    }
    @SubscribeEvent
    public static void event(RecipesReceivedEvent event) {
        ModRecipeCache.event(event);
    }
    @SubscribeEvent
    public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ToolTipSpiritItem.class, Function.identity());
    }
}
