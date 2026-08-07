package com.ytgld.magic_chest.init;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.entity.TheSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MagicEntitys {
    public static final DeferredRegister.Entities REGISTRY = DeferredRegister.createEntities(TheMagicChest.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<TheSpirit>> TheSpirit_ =
            REGISTRY.register("spirit", () -> EntityType.Builder.<TheSpirit>of(TheSpirit::new,
                    MobCategory.MISC).sized(0.1F, 0.1F).clientTrackingRange(
                            50).build(ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "spirit"))));
}
