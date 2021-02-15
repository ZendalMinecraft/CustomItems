package org.zendal.customitems.item.manager;

import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;

/**
 * Interface of manager for register CustomItemStack or find factories
 */
public interface CustomItemStackManager {


    /**
     * Scan packages and find classes with Annotation @CustomItems and registered it
     *
     * @param packages array of packages prefix
     * @see org.zendal.customitems.item.annotation.CustomItem
     */
    void scanPackagesForCustomItemStack(ClassLoader classLoader, String... packages);


    /**
     * Register CustomItemStack with default factory
     *
     * @param classes target CustomItemStack class
     */
    void registerCustomItemStack(Class<? extends AbstractCustomItemStack> ... classes);

    /**
     * Register CustomItemStack with custom factory
     *
     * @param clazz   target CustomItemStack class
     * @param factory factory for creation target
     */
    void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz, CustomItemStackFactory factory);

    /**
     * Get CustomItemStack factory by parameters
     *
     * @param type            type of material
     * @param customModelData id of model
     * @return factory if exists
     */
    @Nullable CustomItemStackFactory getCustomItemStackFactory(Material type, Integer customModelData);
}
