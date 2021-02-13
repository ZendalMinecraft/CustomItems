package org.zendal.customitems;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;
import org.zendal.customitems.item.annotation.CustomItem;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ItemStorage {

    private static final Map<String, CustomItemStackFactory> storage = new HashMap<>();


    // private static CustomItemStackFactory factory = new ReflectionCustomItemStackFactory();


    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazzCustomItem) {
        var annotation = clazzCustomItem.getAnnotation(CustomItem.class);
        if (!annotation.defaultFactory()) {
            throw new RuntimeException("You can't use default factory for this type Item: " + clazzCustomItem + ";" +
                    " Please select defaultFactory = true in CustomItem annotation");
        }
        storage.putIfAbsent(annotation.type().toString() + "#" + annotation.customModelData(), new CustomItemStackFactory() {
            @Override
            public AbstractCustomItemStack build(ItemStack itemStack) {
                return tryFindDefaultConstructorCustomItem(clazzCustomItem, itemStack);
            }
        });
    }

    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> clazzCustomItem, CustomItemStackFactory factory) {
        var annotation = clazzCustomItem.getAnnotation(CustomItem.class);
        if (annotation.defaultFactory()) {
            throw new RuntimeException("You can't use custom factory for this type Item: " + clazzCustomItem + ";" +
                    " Please select defaultFactory = false in CustomItem annotation");
        }
        storage.putIfAbsent(annotation.type().toString() + "#" + annotation.customModelData(), factory);
    }
    @Nullable
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
            if (!clazz.getSuperclass().isAssignableFrom(AbstractCustomItemStack.class)) {
                throw new IllegalStateException("You custom item must be extends from AbstractCustomItemStack");
            }
            registerCustomItemStack(annotation.type(), annotation.customModelData(), new CustomItemStackFactory() {
                @SneakyThrows
                @Override
                public AbstractCustomItemStack build(ItemStack itemStack) {
                    //noinspection unchecked
                    return tryFindDefaultConstructorCustomItem((Class<? extends AbstractCustomItemStack>) clazz, itemStack);
                }
            });
        });
    }

    private void registerCustomItemStack(Material type, Integer customModelData, CustomItemStackFactory factory) {
        storage.putIfAbsent(type.toString() + "#" + customModelData, factory);
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

    public <T extends AbstractCustomItemStack> T buildItem(Class<? extends T> customItemStackClass) {
        var annotation = customItemStackClass.getAnnotation(CustomItem.class);
        var factory = storage.get(annotation.type().toString() + "#" + annotation.customModelData());
        var itemStack = new ItemStack(annotation.type());
        var meta = itemStack.getItemMeta();
        meta.setCustomModelData(annotation.customModelData());
        itemStack.setItemMeta(meta);
       return (T) factory.build(itemStack);
    }
}
