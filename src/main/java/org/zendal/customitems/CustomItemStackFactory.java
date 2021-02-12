package org.zendal.customitems;

import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;

public interface CustomItemStackFactory {


     default AbstractCustomItemStack build(ItemStack itemStack){
         return new AbstractCustomItemStack(itemStack);
     }
}
