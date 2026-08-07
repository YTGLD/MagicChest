package com.ytgld.magic_chest.entity.state;

import com.ytgld.magic_chest.entity.TheSpirit;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class TheSpiritState extends EntityRenderState {
    public TheSpirit entity;
    public float partialTick;
    public final ItemStackRenderState item = new ItemStackRenderState();
}
