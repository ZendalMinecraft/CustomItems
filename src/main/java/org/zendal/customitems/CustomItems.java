package org.zendal.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.zendal.customitems.item.helper.CustomItemStackHelper;
import org.zendal.customitems.item.helper.CustomItemStackHelperImpl;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import org.zendal.customitems.item.manager.CustomItemStackManagerImpl;
import org.zendal.customitems.item.storage.CustomItemStackStorage;
import org.zendal.customitems.item.storage.HashMapCustomItemStackStorage;
import org.zendal.customitems.listener.PlayerListener;
import org.zendal.customitems.listener.TestListener;
import org.zendal.customitems.reflection.ReflectionHelper;
import org.zendal.customitems.reflection.ReflectionHelperImpl;

import java.util.logging.Logger;

public final class CustomItems extends JavaPlugin {

    private CustomItemsApi api;

    @Override
    public void onEnable() {
        var reflection = new ReflectionHelperImpl();
        var storage = new HashMapCustomItemStackStorage();
        this.api = this.buildCustomItemsApi(this.getLogger(), reflection, storage);

        this.api.getCustomItemStackManager().scanPackagesForCustomItemStack("org.zendal");

        this.getServer().getPluginManager().registerEvents(new PlayerListener(this.api.getCustomItemStackManager()), this);
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);
    }


    private CustomItemsApi buildCustomItemsApi(Logger logger, ReflectionHelper reflection, CustomItemStackStorage storage) {
        return new CustomItemsApi() {
            private final CustomItemStackManager customItemStackManager = new CustomItemStackManagerImpl(logger, reflection, storage);

            private final CustomItemStackHelper customItemStackHelper = new CustomItemStackHelperImpl(storage);

            @Override
            public CustomItemStackManager getCustomItemStackManager() {
                return this.customItemStackManager;
            }

            @Override
            public CustomItemStackHelper getCustomItemStackHelper() {
                return customItemStackHelper;
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
