package com.ytgld.magic_chest;

import com.ytgld.chest_item.entity.Entitys;
import com.ytgld.chest_item.entity.render.*;
import com.ytgld.chest_item.renderer.particle.ColorPart;
import com.ytgld.chest_item.renderer.particle.FireBlock;
import com.ytgld.chest_item.renderer.particle.OrbPart;
import com.ytgld.chest_item.renderer.particle.SwordEnergy;
import com.ytgld.chest_item.renderer.particle.evilmother.ColorPartEvil;
import com.ytgld.chest_item.renderer.particle.evilmother.CubeEvil;
import com.ytgld.chest_item.renderer.particle.evilmother.EvilTailing;
import com.ytgld.chest_item.renderer.particle.evilmother.OrbPartEvil;
import com.ytgld.chest_item.renderer.particle.other.Particles;
import com.ytgld.chest_item.renderer.particle.sword.SwordShadow1;
import com.ytgld.chest_item.renderer.particle.sword.SwordShadow2;
import com.ytgld.chest_item.renderer.particle.sword.SwordShadow3;
import com.ytgld.chest_item.renderer.particle.sword.SwordShadow4;
import com.ytgld.magic_chest.entity.render.TheSpiritRender;
import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.item.BaseItem;
import com.ytgld.magic_chest.renderer.RenderBlackItem;
import com.ytgld.magic_chest.renderer.particle.has_opt.CubeParticle;
import com.ytgld.magic_chest.renderer.particle.has_opt.MagicChestParticle;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

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
}
