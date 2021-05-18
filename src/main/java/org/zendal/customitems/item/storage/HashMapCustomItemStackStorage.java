package org.zendal.customitems.item.storage;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of Storage based on HashMap
 */
public class HashMapCustomItemStackStorage implements CustomItemStackStorage {

    private final Map<String, CustomItemStackFactory> customItemStackFactoryStorage = new HashMap<>();


    private final Map<String, Class<? extends AbstractCustomItemStack>> customItemStackClassStorage = new HashMap<>();


    @Override
    public @Nullable CustomItemStackFactory getCustomItemStackFactory(@NotNull Material type, @NotNull Integer customModelData) {
        return customItemStackFactoryStorage.get(this.buildKey(type, customModelData));
    }

    @Override
    public void registerCustomItemStack(Class<? extends AbstractCustomItemStack> classCustomItemStack,
                                        @NotNull Material type,
                                        @NotNull Integer customModelData,
                                        @NotNull CustomItemStackFactory factory
    ) {
        customItemStackFactoryStorage.putIfAbsent(this.buildKey(type, customModelData), factory);
        customItemStackClassStorage.putIfAbsent(this.buildKey(type, customModelData), classCustomItemStack);
    }


    @Override
    public Class<? extends AbstractCustomItemStack> getCustomItemStackClass(@NotNull Material type, @NotNull Integer customModelData) {
        return customItemStackClassStorage.get(this.buildKey(type, customModelData));
    }

    /**
     * Build key (access) for this data
     *
     * @param type            type of data
     * @param customModelData model data
     * @return key for data
     */
    private String buildKey(Material type, Integer customModelData) {
        return type.toString() + "#" + customModelData;
    }


}
