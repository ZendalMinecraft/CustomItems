package org.zendal.customitems.item.helper;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import org.zendal.customitems.item.storage.CustomItemStackStorage;

public class CustomItemStackHelperImpl implements CustomItemStackHelper {

    private final CustomItemStackManager customItemStackManager;

    private final CustomItemStackStorage customItemStackStorage;

    public CustomItemStackHelperImpl(CustomItemStackManager customItemStackManager, CustomItemStackStorage customItemStackStorage) {
        this.customItemStackManager = customItemStackManager;
        this.customItemStackStorage = customItemStackStorage;
    }

    @Override
    public boolean isCustomItemStack(@NotNull ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }
        var meta = itemStack.getItemMeta();
        if (meta == null || !meta.hasCustomModelData()) {
            return false;
        }

        return customItemStackStorage.getCustomItemStackFactory(itemStack.getType(), meta.getCustomModelData()) != null;
    }

    @Override
    public <T extends AbstractCustomItemStack> T buildItem(Class<? extends T> customItemStackClass) {
        var annotation = customItemStackClass.getAnnotation(CustomItem.class);
        var factory = customItemStackStorage.getCustomItemStackFactory(annotation.type(), annotation.customModelData());
        var itemStack = new ItemStack(annotation.type());
        var meta = itemStack.getItemMeta();
        if (meta == null || factory == null) {
            throw new RuntimeException("Can't create CustomItemStack from: " + customItemStackClass);
        }
        meta.setCustomModelData(annotation.customModelData());
        itemStack.setItemMeta(meta);
        //noinspection unchecked
        return (T) factory.build(customItemStackManager, itemStack);
    }
}
