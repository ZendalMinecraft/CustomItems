package org.zendal.customitems.test;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

@CustomItem(type = Material.BARRIER, customModelData = 11)
public class AnotherServiceItemStack extends AbstractCustomItemStack {
    public AnotherServiceItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }
}
