package org.zendal.customitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.ServiceItemStack;
import org.zendal.customitems.listener.TestListener;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        var storage = new ItemStorage();
        storage.init("org.zendal.customitems");
        this.getServer().getPluginManager().registerEvents(new TestListener(storage), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
