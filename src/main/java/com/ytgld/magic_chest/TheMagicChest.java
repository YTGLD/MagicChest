package com.ytgld.magic_chest;

import com.mojang.logging.LogUtils;
import com.ytgld.magic_chest.crafting.ModRecipes;
import com.ytgld.magic_chest.event.MagicEvent;
import com.ytgld.magic_chest.init.MagicData;
import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.init.MagicItems;
import com.ytgld.magic_chest.init.MagicTab;
import com.ytgld.magic_chest.item.GatherItemModel;
import com.ytgld.magic_chest.other.MagicSounds;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(TheMagicChest.MODID)
public class TheMagicChest {
    public static final String MODID = "magic_chest";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheMagicChest(IEventBus modEventBus, ModContainer modContainer) {
        MagicItems.ITEMS.register(modEventBus);
        MagicTab.CREATIVE_MODE_TABS.register(modEventBus);
        MagicEntitys.REGISTRY.register(modEventBus);
        MagicParticles.PARTICLE_TYPES.register(modEventBus);
        MagicData.REGISTRY.register(modEventBus);
        ModRecipes.SERIALIZERS.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
        MagicSounds.REGISTRY.register(modEventBus);

        modEventBus.addListener(this::gatherData);

        NeoForge.EVENT_BUS.register(new MagicEvent());
    }

    public void gatherData(GatherDataEvent.Client event) {
        event.createProvider(GatherItemModel::new);
    }
}
