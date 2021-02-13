package org.zendal.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.zendal.customitems.item.manager.CustomItemStackHelper;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import org.zendal.customitems.listener.PlayerListener;
import org.zendal.customitems.listener.TestListener;

public final class CustomItems extends JavaPlugin {

    private CustomItemsApi api;

    @Override
    public void onEnable() {
        var storage = new ItemStorage();
        this.api = this.buildCustomItemsApi();
        storage.init("org.zendal.customitems");
        this.getServer().getPluginManager().registerEvents(new PlayerListener(storage), this);
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);
    }


    private CustomItemsApi buildCustomItemsApi() {
        return  new CustomItemsApi() {
            @Override
            public CustomItemStackManager getCustomItemStackManager() {
                return null;
            }

            @Override
            public CustomItemStackHelper getCustomItemStackHelper() {
                return null;
            }
        };
    }


    public CustomItemsApi getCustomItemsApi() {
        return this.api;
    }

    @Override
    public void onDisable() {

    }
}
