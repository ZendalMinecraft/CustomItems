package org.zendal.customitems;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ItemStorage {

    private static final Map<String, CustomItemStackFactory> storage = new HashMap<>();


   // private static CustomItemStackFactory factory = new ReflectionCustomItemStackFactory();

    public void registerCustomItemStack(Material type, Integer customModelData) {
        storage.putIfAbsent(type.toString() + "#" + customModelData, new CustomItemStackFactory() {

        });
    }


    public void registerCustomItemStack(Material type, Integer customModelData, CustomItemStackFactory factory) {
        storage.putIfAbsent(type.toString() + "#" + customModelData, factory);
    }

    public CustomItemStackFactory getCustomItemStackFactory(Material type, Integer customModelData) {
        return storage.get(type.toString() + "#" + customModelData);
    }


    public void init(String prefixPackage) {
        Reflections reflections = new Reflections(prefixPackage);
        var classes = reflections.getTypesAnnotatedWith(CustomItem.class);
        classes.forEach(clazz -> {
            var annotation = clazz.getAnnotation(CustomItem.class);
            if (!annotation.defaultFactory()) {
                return;
            }
            registerCustomItemStack(annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
                @SneakyThrows
                @Override
                public AbstractCustomItemStack build(ItemStack itemStack) {
                    for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                        if (constructor.getParameters().length == 1 && constructor.getParameters()[0].getType().equals(ItemStack.class)) {
                            return (AbstractCustomItemStack) constructor.newInstance(itemStack);
                        }
                    }
                    throw new RuntimeException("Can't create " + clazz);
                }
            });
        });
    }

    static class ReflectionCustomItemStackFactory implements CustomItemStackFactory {
        @SneakyThrows
        public AbstractCustomItemStack build(Class<?> clazz, ItemStack itemStack) {
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (constructor.getParameters().length == 1 && constructor.getParameters()[0].getType().equals(ItemStack.class)) {
                    return (AbstractCustomItemStack) constructor.newInstance(itemStack);
                }
            }
            throw new RuntimeException("Can't create " + clazz);
        }
    }
}
