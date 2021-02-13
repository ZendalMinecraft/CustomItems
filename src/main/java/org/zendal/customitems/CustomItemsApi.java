package org.zendal.customitems;

import org.zendal.customitems.item.AbstractCustomItemStack;

/**
 * This interface for control custom items
 */
public interface CustomItemsApi {

    void scanPackagesForCustomItem(String... packages);

    void registerCustomItem(Class<? extends AbstractCustomItemStack> clazz);

    void registerCustomItem(Class<? extends AbstractCustomItemStack> clazz, CustomItemStackFactory factory);
}
