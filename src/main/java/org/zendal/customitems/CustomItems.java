package org.zendal.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.zendal.customitems.listener.PlayerListener;
import org.zendal.customitems.listener.TestListener;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        var storage = new ItemStorage();
        storage.init("org.zendal.customitems");
        this.getServer().getPluginManager().registerEvents(new PlayerListener(storage), this);
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
