package com.ytgld.magic_chest.renderer;

import com.ytgld.chest_item.items.InitItems;
import com.ytgld.chest_item.items.ItemBase;
import com.ytgld.chest_item.renderer.book.CIBookScreen;
import com.ytgld.chest_item.renderer.book.tool.AddBookPage;
import com.ytgld.chest_item.renderer.book.tool.RegisterBookPage;
import com.ytgld.chest_item.renderer.light.Light;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;

import java.util.List;
//
//@AddBookPage
//public class RecipeBook implements RegisterBookPage {
//
//    @Override
//    public void addPage(List<CIBookScreen.CIBookGuiAdd> list) {
//        list.add(new CIBookScreen.CIBookGuiAdd(InitItems.MAGIC_IRON.asItem(),
//                new Vec2(32, 0),
//                Component.translatable("chest_item.book.dry_bones.main"),
//                List.of(Component.translatable("chest_item.book.dry_bones.1"),
//                        Component.translatable("chest_item.book.dry_bones.2"),
//                        Component.translatable("chest_item.book.dry_bones.3")),
//                Light.ARGB.color(255, 200, 200, 130),
//                Light.ARGB.color(255, 100, 100, 65),
//                CIBookScreen.ThePage.BLACK,
//                Light.ARGB.color(255, 200, 200, 130)));
//    }
//}
