package org.zendal.customitems.item.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;

import java.util.List;

/**
 * Interface of manager for register CustomItemStack or find factories
 */
public interface CustomItemStackManager {


    /**
     * Scan packages and find classes with Annotation @CustomItems and registered it
     *
     * @param classLoader loader of classes your plugin
     * @param packages    array of packages prefix (scope)
     * @see org.zendal.customitems.item.annotation.CustomItem
     */
    void scanPackagesForCustomItemStack(ClassLoader classLoader, String... packages);


    /**
     * Register CustomItemStack with default factory
     *
     * @param classes target CustomItemStack class
     */
    void registerCustomItemStack(Class<? extends AbstractCustomItemStack>... classes);

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


    /**
     * Get custom item stack class by ItemStack
     *
     * @param itemStack source ItemStack
     * @return class of CustomItemStack
     */
    @Nullable Class<? extends AbstractCustomItemStack> getCustomItemStackClass(ItemStack itemStack);


    /**
     * Get list of classes AbstractCustomItemStack which annotated with none default factory
     *
     * @param classLoader loader of classes your plugin
     * @param packages    array of packages prefix (scope)
     * @return List of classes
     */
    List<Class<? extends AbstractCustomItemStack>> getCustomItemsStackListClassWithNotDefaultFactory(ClassLoader classLoader, String... packages);
}
