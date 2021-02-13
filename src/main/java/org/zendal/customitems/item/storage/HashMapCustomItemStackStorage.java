package org.zendal.customitems.item.storage;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.CustomItemStackFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of Storage based on HashMap
 */
public class HashMapCustomItemStackStorage implements CustomItemStackStorage {

    private final Map<String, CustomItemStackFactory> storage = new HashMap<>();


    @Override
    public @Nullable CustomItemStackFactory getCustomItemStackFactory(@NotNull Material type, @NotNull Integer customModelData) {
        return storage.get(this.buildKey(type, customModelData));
    }

    @Override
    public void registerCustomItemStack(@NotNull Material type, @NotNull Integer customModelData, @NotNull CustomItemStackFactory factory) {
        storage.putIfAbsent(this.buildKey(type, customModelData), factory);
    }

    private String buildKey(Material type, Integer customModelData) {
        return type.toString() + "#" + customModelData;
    }


}
