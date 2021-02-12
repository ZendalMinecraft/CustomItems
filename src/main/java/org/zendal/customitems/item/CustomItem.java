package org.zendal.customitems.item;

import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

public interface CustomItem extends Item {

    @NotNull
    @Override
    AbstractCustomItemStack getItemStack();

}
