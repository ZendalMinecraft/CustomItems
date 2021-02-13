package org.zendal.customitems.listener;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.ItemStorage;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItem;
import org.zendal.customitems.item.CustomItemProxy;

public final class ListenerUtils {


    @Nullable
    public static CustomItem getCustomItemByItem(ItemStorage itemStorage, Item item) {
        ItemStack itemStack = item.getItemStack();

        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasCustomModelData()) {
            return null;
        }

        try {
            var customItemStackFactory = itemStorage.getCustomItemStackFactory(itemStack.getType(), itemStack.getItemMeta().getCustomModelData());
            if (customItemStackFactory != null) {
                AbstractCustomItemStack customItemStack = customItemStackFactory.build(itemStack);
                return new CustomItemProxy(item, customItemStack);
            }
            System.out.println("Found custom item without registry: " + itemStack.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
