package org.zendal.customitems.item.manager;

import org.zendal.customitems.item.CustomItemStackFactory;
import org.zendal.customitems.item.AbstractCustomItemStack;

public interface CustomItemStackManager {

    void scanPackagesForCustomItemStack(String... packages);

    void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz);

    void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz, CustomItemStackFactory factory);
}
