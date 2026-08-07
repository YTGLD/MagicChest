package com.ytgld.magic_chest;

import com.ytgld.magic_chest.event.MagicEvent;
import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.init.MagicItems;
import com.ytgld.magic_chest.init.MagicTab;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(TheMagicChest.MODID)
public class TheMagicChest {
    public static final String MODID = "magic_chest";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheMagicChest(IEventBus modEventBus, ModContainer modContainer) {
        MagicItems.ITEMS.register(modEventBus);
        MagicTab.CREATIVE_MODE_TABS.register(modEventBus);
        MagicEntitys.REGISTRY.register(modEventBus);
        MagicParticles.PARTICLE_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(new MagicEvent());
    }
}
