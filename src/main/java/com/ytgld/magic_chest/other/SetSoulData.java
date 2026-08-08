package com.ytgld.magic_chest.other;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ytgld.magic_chest.init.MagicData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public record SetSoulData(HashMap<String, Integer> soulMap) {
    public static final Codec<SetSoulData> CODEC =
            RecordCodecBuilder.create((instance) ->
                    instance.group(Codec.unboundedMap(Codec.STRING, Codec.INT)
                                    .xmap(HashMap::new, (map) -> map)
                                    .fieldOf("soul_map").forGetter((setSoulData) -> setSoulData.soulMap))
                            .apply(instance, SetSoulData::new));

    public SetSoulData add(String string, Integer integer) {
        this.soulMap.put(string, integer);
        return this;
    }

    public static Set<Item> getAllSpirit(ItemStack stack){
        Set<Item> set = new HashSet<>();
        SetSoulData setSoulData = stack.get(MagicData.soulMap);
        if (setSoulData != null) {
            for (String string : setSoulData.soulMap().keySet()) {
                Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(string));
                set.add(item);
            }
        }
        return set;
    }
}