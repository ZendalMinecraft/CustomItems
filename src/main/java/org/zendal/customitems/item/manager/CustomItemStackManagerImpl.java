package org.zendal.customitems.item.manager;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;
import org.zendal.customitems.item.annotation.CustomItem;
import org.zendal.customitems.item.storage.CustomItemStackStorage;
import org.zendal.customitems.reflection.ReflectionHelper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implements of manager
 */
public class CustomItemStackManagerImpl implements CustomItemStackManager {

    /**
     * Instance of logger
     */
    private final Logger logger;

    /**
     * Storage
     */
    private final CustomItemStackStorage customItemStackStorage;
    private final ReflectionHelper reflectionHelper;

    public CustomItemStackManagerImpl(Logger pluginLogger, ReflectionHelper reflectionHelper, CustomItemStackStorage customItemStackStorage) {
        this.logger = pluginLogger;
        this.reflectionHelper = reflectionHelper;
        this.customItemStackStorage = customItemStackStorage;
    }

    @Override
    public void scanPackagesForCustomItemStack(ClassLoader classLoader, String... packages) {
        logger.info("[CustomItemManager] Start scanning packages: " + Arrays.toString(packages));
        for (String onePackage : packages) {
            var classes = reflectionHelper.getAllClassesWithAnnotation(classLoader, onePackage, CustomItem.class);

            classes.forEach(clazz -> {
                var annotation = clazz.getAnnotation(CustomItem.class);
                if (!annotation.defaultFactory()) {
                    return;
                }
                if (!AbstractCustomItemStack.class.isAssignableFrom(clazz)) {
                    throw new IllegalStateException("You custom item must be extends from AbstractCustomItemStack");
                }
                //noinspection unchecked
                customItemStackStorage.registerCustomItemStack((Class<? extends AbstractCustomItemStack>) clazz, annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
                    @SneakyThrows
                    @Override
                    public AbstractCustomItemStack build(CustomItemStackManager targetClazz, ItemStack itemStack) {
                        //noinspection unchecked
                        return tryFindDefaultConstructorCustomItem((Class<? extends AbstractCustomItemStack>) clazz, itemStack);
                    }
                });
                logger.info("[CustomItemManager] Successful loaded item: " + clazz);
            });
        }
    }

    /**
     * Try create instance of CustomItemStack
     *
     * @param clazz     target class
     * @param itemStack source data
     * @return instance of CustomItemStack
     */
    private AbstractCustomItemStack tryFindDefaultConstructorCustomItem(Class<? extends AbstractCustomItemStack> clazz, ItemStack itemStack) {
        try {
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (constructor.getParameters().length == 1 && constructor.getParameters()[0].getType().equals(ItemStack.class)) {
                    return (AbstractCustomItemStack) constructor.newInstance(itemStack);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't create " + clazz, e);
        }
        throw new RuntimeException("Can't find default constructor for: " + clazz);
    }

    @Override
    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack>... classes) {
        for (Class<? extends AbstractCustomItemStack> clazz : classes) {
            var annotation = clazz.getAnnotation(CustomItem.class);
            if (!annotation.defaultFactory()) {
                throw new RuntimeException("You can't use default factory for this type Item: " + clazz + ";" +
                        " Please select defaultFactory = true in CustomItem annotation");
            }
            this.customItemStackStorage.registerCustomItemStack(clazz, annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
                @Override
                public AbstractCustomItemStack build(CustomItemStackManager customItemStackManager, ItemStack itemStack) {
                    return tryFindDefaultConstructorCustomItem(clazz, itemStack);
                }
            });
            logger.info("[CustomItemManager] Successful loaded item, with default factory : " + clazz);
        }
    }

    @Override
    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz, CustomItemStackFactory factory) {
        var annotation = clazz.getAnnotation(CustomItem.class);
        if (annotation.defaultFactory()) {
            throw new RuntimeException("You can't use custom factory for this type Item: " + clazz + ";" +
                    " Please select defaultFactory = false in CustomItem annotation");
        }
        this.customItemStackStorage.registerCustomItemStack(clazz, annotation.type(), annotation.customModelData(), factory);
        logger.info("[CustomItemManager] Successful loaded item, with custom factory: " + clazz);
    }


    @Nullable
    @Override
    public CustomItemStackFactory getCustomItemStackFactory(Material type, Integer customModelData) {
        return this.customItemStackStorage.getCustomItemStackFactory(type, customModelData);
    }


    @Override
    public @Nullable Class<? extends AbstractCustomItemStack> getCustomItemStackClass(ItemStack itemStack) {

        if (!itemStack.hasItemMeta() || itemStack.getItemMeta() != null && !itemStack.getItemMeta().hasCustomModelData()) {
            return null;
        }

        return this.customItemStackStorage.getCustomItemStackClass(itemStack.getType(), itemStack.getItemMeta().getCustomModelData());
    }


    @Override
    public List<Class<? extends AbstractCustomItemStack>> getCustomItemsStackListClassWithNotDefaultFactory(ClassLoader classLoader, String... packages) {
        logger.info("[CustomItemManager] Start scanning packages: " + Arrays.toString(packages));
        var result = new ArrayList<Class<? extends AbstractCustomItemStack>>();
        for (String onePackage : packages) {
            var classes = reflectionHelper.getAllClassesWithAnnotation(classLoader, onePackage, CustomItem.class);

            classes.forEach(clazz -> {
                var annotation = clazz.getAnnotation(CustomItem.class);
                if (annotation.defaultFactory()) {
                    return;
                }
                if (!AbstractCustomItemStack.class.isAssignableFrom(clazz)) {
                    throw new IllegalStateException("You custom item must be extends from AbstractCustomItemStack");
                }
                logger.info("[CustomItemManager] Found class without at default factory: " + clazz);
                //noinspection unchecked
                result.add((Class<? extends AbstractCustomItemStack>) clazz);
            });
        }
        return result;
    }
}
