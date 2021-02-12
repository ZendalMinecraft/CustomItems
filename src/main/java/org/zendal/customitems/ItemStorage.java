package org.zendal.customitems;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ItemStorage {

    private static final Map<String, CustomItemStackFactory> storage = new HashMap<>();


    public void registerCustomItemStack(Material type, Integer customModelData) {
        storage.putIfAbsent(type.toString() + "#" + customModelData, new CustomItemStackFactory() {

        });
    }


    public void registerCustomItemStack(Material type, Integer customModelData, CustomItemStackFactory factory) {
        storage.putIfAbsent(type.toString() + "#" + customModelData, factory);
    }

    public CustomItemStackFactory getCustomItemStackFactory(Material type, Integer customModelData){
       return storage.get(type.toString() + "#" + customModelData);
    }


    public void init(){
        Reflections reflections = new Reflections("my.package");
    }
}
