package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItem;
import org.zendal.customitems.item.CustomItemProxy;
import org.zendal.customitems.item.manager.CustomItemStackManager;

public final class ListenerUtils {


    @Nullable
    public static CustomItem getCustomItemByItem(CustomItemStackManager customItemStackManager, Item item) {

        try {
            var customItemStack = ListenerUtils.getCustomItemStackByItemStack(customItemStackManager, item.getItemStack());
            if (customItemStack != null) {
                return new CustomItemProxy(item, customItemStack);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static AbstractCustomItemStack getCustomItemStackByItemStack(CustomItemStackManager customItemStackManager, ItemStack itemStack) {

        if (!itemStack.hasItemMeta() || itemStack.getItemMeta() != null && !itemStack.getItemMeta().hasCustomModelData()) {
            return null;
        }

        var customItemStackFactory = customItemStackManager.getCustomItemStackFactory(itemStack.getType(), itemStack.getItemMeta().getCustomModelData());

        if (customItemStackFactory != null) {
            return customItemStackFactory.build(itemStack);
        }
        Bukkit.getLogger().warning("Found custom item without registry: " + itemStack.toString());
        return null;
    }
}
