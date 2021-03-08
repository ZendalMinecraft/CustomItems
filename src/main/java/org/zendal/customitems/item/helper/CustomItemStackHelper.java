package org.zendal.customitems.item.helper;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;

/**
 * Interface of Common helper for working with your CustomItemStack's
 */
public interface CustomItemStackHelper {

    /**
     * Checking ItemStack it's CustomItemStack
     *
     * @param itemStack any of itemStack
     * @return {@code true} if is CustomItemStack else {@code false}
     */
    boolean isCustomItemStack(@NotNull ItemStack itemStack);

    /**
     * Build CustomItemStack through class
     *
     * @param customItemStackClass any class who extended AbstractCustomItemStack
     * @param <T>                  your CustomItemStack
     * @return your CustomItemStack
     */
    <T extends AbstractCustomItemStack> T buildItem(Class<? extends T> customItemStackClass);
}
