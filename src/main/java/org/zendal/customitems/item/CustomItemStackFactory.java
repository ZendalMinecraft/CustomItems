package org.zendal.customitems.item;

import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.manager.CustomItemStackManager;

/**
 * Factory interface for create CustomItemStack
 */
public interface CustomItemStackFactory {

    /**
     * Build CustomItemStack by ItemStack
     *
     * @param customItemStackManager manager of CustomItemStack's
     * @param itemStack              source ItemStack
     * @return instance of CustomItemStack
     */
    default AbstractCustomItemStack build(CustomItemStackManager customItemStackManager, ItemStack itemStack) {
        return new AbstractCustomItemStack(itemStack);
    }
}
