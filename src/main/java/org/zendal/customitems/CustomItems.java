package org.zendal.customitems;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.zendal.customitems.listener.TestListener;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        var storage = new ItemStorage();
        storage.registerCustomItemStack(Material.BARRIER, 12);
        this.getServer().getPluginManager().registerEvents(new TestListener(storage), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
