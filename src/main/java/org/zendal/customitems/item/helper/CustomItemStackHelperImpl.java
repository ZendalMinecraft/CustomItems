package org.zendal.customitems.item.helper;

import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;
import org.zendal.customitems.item.storage.CustomItemStackStorage;

public class CustomItemStackHelperImpl implements CustomItemStackHelper {

    private final CustomItemStackStorage customItemStackStorage;

    public CustomItemStackHelperImpl(CustomItemStackStorage customItemStackStorage) {
        this.customItemStackStorage = customItemStackStorage;
    }

    @Override
    public boolean isCustomItemStack(ItemStack itemStack) {
        return false;
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
        return (T) factory.build(itemStack);
    }
}
