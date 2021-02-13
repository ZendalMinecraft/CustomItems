package org.zendal.customitems.item;

import org.bukkit.inventory.ItemStack;

public interface CustomItemStackFactory {

    default AbstractCustomItemStack build(ItemStack itemStack) {
        return new AbstractCustomItemStack(itemStack);
    }
}
