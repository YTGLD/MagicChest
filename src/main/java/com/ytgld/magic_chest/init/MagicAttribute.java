package com.ytgld.magic_chest.init;

import com.mojang.serialization.Codec;
import com.ytgld.chest_item.other.IntSyncHandler;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = TheMagicChest.MODID)
public class MagicAttribute {

    public static final DeferredRegister<Attribute> REGISTER = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, TheMagicChest.MODID);
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, TheMagicChest.MODID);


    /**
     * 反制
     * <p>
     * 增加对攻击者造成的伤害
     * <p>
     * 攻击有效伤害时造成暴击和横扫
     */
    public static final DeferredHolder<Attribute, ?> counter = REGISTER.register("counter",
            () -> (new RangedAttribute(TheMagicChest.MODID + ".attribute.counter",
                    0.0F, 0, 100)).setSyncable(true));
    public static final Supplier<AttachmentType<Integer>> counter_data=
            ATTACHMENT_TYPES.register("counter_data",
            () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler())
                    .serialize(Codec.INT.fieldOf("counter_data")).build());

    public static final Supplier<AttachmentType<Integer>> counter_data_cooldown=
            ATTACHMENT_TYPES.register("counter_data_cooldown",
            () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler())
                    .serialize(Codec.INT.fieldOf("counter_data_cooldown")).build());

    /**
     * 连斩
     * <p>
     * 与反制搭配
     * <p>
     * 适当连击可提高伤害
     */
    public static final Supplier<AttachmentType<Integer>> score_consecutive_victories=
            ATTACHMENT_TYPES.register("score_consecutive_victories",
            () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler())
                    .serialize(Codec.INT.fieldOf("score_consecutive_victories")).build());




    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event) {
        event.add(EntityTypes.PLAYER, counter, 0);
    }

}
