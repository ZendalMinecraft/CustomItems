package org.zendal.customitems.item.manager;

import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;
import org.zendal.customitems.item.annotation.CustomItem;
import org.zendal.customitems.item.storage.CustomItemStackStorage;
import org.zendal.customitems.reflection.ReflectionHelper;

import java.lang.reflect.Constructor;

public class CustomItemStackManagerImpl implements CustomItemStackManager {

    private final CustomItemStackStorage customItemStackStorage;
    private final ReflectionHelper reflectionHelper;

    public CustomItemStackManagerImpl(ReflectionHelper reflectionHelper, CustomItemStackStorage customItemStackStorage) {
        this.reflectionHelper = reflectionHelper;
        this.customItemStackStorage = customItemStackStorage;
    }

    @Override
    public void scanPackagesForCustomItemStack(String... packages) {
        for (String onePackage : packages) {
            var classes = reflectionHelper.getAllClassesWithAnnotation(onePackage, CustomItem.class);

            classes.forEach(clazz -> {
                var annotation = clazz.getAnnotation(CustomItem.class);
                if (!annotation.defaultFactory()) {
                    return;
                }
                if (!clazz.getSuperclass().isAssignableFrom(AbstractCustomItemStack.class)) {
                    throw new IllegalStateException("You custom item must be extends from AbstractCustomItemStack");
                }
                customItemStackStorage.registerCustomItemStack(annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
                    @SneakyThrows
                    @Override
                    public AbstractCustomItemStack build(ItemStack itemStack) {
                        //noinspection unchecked
                        return tryFindDefaultConstructorCustomItem((Class<? extends AbstractCustomItemStack>) clazz, itemStack);
                    }
                });
            });
        }
    }

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
    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz) {
        var annotation = clazz.getAnnotation(CustomItem.class);
        if (!annotation.defaultFactory()) {
            throw new RuntimeException("You can't use default factory for this type Item: " + clazz + ";" +
                    " Please select defaultFactory = true in CustomItem annotation");
        }
        this.customItemStackStorage.registerCustomItemStack(annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
            @Override
            public AbstractCustomItemStack build(ItemStack itemStack) {
                return tryFindDefaultConstructorCustomItem(clazz, itemStack);
            }
        });
    }

    @Override
    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazz, CustomItemStackFactory factory) {
        var annotation = clazz.getAnnotation(CustomItem.class);
        if (annotation.defaultFactory()) {
            throw new RuntimeException("You can't use custom factory for this type Item: " + clazz + ";" +
                    " Please select defaultFactory = false in CustomItem annotation");
        }
        this.customItemStackStorage.registerCustomItemStack(annotation.type(), annotation.customModelData(), factory);
    }
}
