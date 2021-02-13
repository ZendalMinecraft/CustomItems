package org.zendal.customitems.test;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

@CustomItem(type = Material.BARRIER, customModelData = 12)
public class ServiceItemStack extends AbstractCustomItemStack {

    public ServiceItemStack(ItemStack itemStack) {
        super(itemStack);
    }
}
