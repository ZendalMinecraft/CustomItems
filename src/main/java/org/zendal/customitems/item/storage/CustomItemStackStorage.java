package org.zendal.customitems.item.storage;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.CustomItemStackFactory;

/**
 * Interface of storage at CustomItemStack and factories for build them
 *
 * @see org.zendal.customitems.item.AbstractCustomItemStack
 */
public interface CustomItemStackStorage {


    @Nullable
    CustomItemStackFactory getCustomItemStackFactory(@NotNull Material type, @NotNull Integer customModelData);


    void registerCustomItemStack(@NotNull Material type, @NotNull Integer customModelData, @NotNull CustomItemStackFactory factory);
}
