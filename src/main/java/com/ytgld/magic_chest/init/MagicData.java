package com.ytgld.magic_chest.init;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.other.SetSoulData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MagicData {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, TheMagicChest.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SetSoulData>> soulMap =
            REGISTRY.register("soul_map",() -> DataComponentType.<SetSoulData>builder().persistent(SetSoulData.CODEC).build());

}
