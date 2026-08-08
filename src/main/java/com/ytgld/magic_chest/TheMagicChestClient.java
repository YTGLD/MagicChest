package com.ytgld.magic_chest;

import com.ytgld.magic_chest.entity.render.TheSpiritRender;
import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.other.ToolTipSpiritItem;
import com.ytgld.magic_chest.renderer.RenderBlackItem;
import com.ytgld.magic_chest.renderer.particle.has_opt.CubeParticle;
import com.ytgld.magic_chest.renderer.particle.has_opt.MagicChestParticle;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

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
    public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ToolTipSpiritItem.class, Function.identity());
    }
}
