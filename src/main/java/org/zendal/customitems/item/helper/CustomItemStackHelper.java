package org.zendal.customitems.item.helper;

import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;

public interface CustomItemStackHelper {

    boolean isCustomItemStack(ItemStack itemStack);

    <T extends AbstractCustomItemStack> T buildItem(Class<? extends T> customItemStackClass);
}
