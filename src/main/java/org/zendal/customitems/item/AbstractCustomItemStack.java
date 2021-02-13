package org.zendal.customitems.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AbstractCustomItemStack extends ItemStack {



    public AbstractCustomItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
        if (!stack.hasItemMeta() || !stack.getItemMeta().hasCustomModelData()) {
            throw new RuntimeException("It's not a custom item");
        }
    }
}
