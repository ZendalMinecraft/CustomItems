package org.zendal.customitems.item.storage;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;

/**
 * Interface of storage at CustomItemStack and factories for build them
 *
 * @see org.zendal.customitems.item.AbstractCustomItemStack
 */
public interface CustomItemStackStorage {


    @Nullable
    CustomItemStackFactory getCustomItemStackFactory(@NotNull Material type, @NotNull Integer customModelData);


    /**
     * Register
     *
     * @param classCustomItemStack
     * @param type
     * @param customModelData
     * @param factory
     */
    void registerCustomItemStack(Class<? extends AbstractCustomItemStack> classCustomItemStack,
                                 @NotNull Material type,
                                 @NotNull Integer customModelData,
                                 @NotNull CustomItemStackFactory factory);


    @Nullable Class<? extends AbstractCustomItemStack> getCustomItemStackClass(@NotNull Material type, @NotNull Integer customModelData);
}
