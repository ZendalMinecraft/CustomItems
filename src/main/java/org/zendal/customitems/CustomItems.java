package org.zendal.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.configuration.CustomItemsConfiguration;
import org.zendal.customitems.configuration.CustomItemsConfigurationData;
import org.zendal.customitems.configuration.CustomItemsConfigurationImpl;
import org.zendal.customitems.item.helper.CustomItemStackHelper;
import org.zendal.customitems.item.helper.CustomItemStackHelperImpl;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import org.zendal.customitems.item.manager.CustomItemStackManagerImpl;
import org.zendal.customitems.item.storage.CustomItemStackStorage;
import org.zendal.customitems.item.storage.HashMapCustomItemStackStorage;
import org.zendal.customitems.listener.PlayerListener;
import org.zendal.customitems.listener.ResourcePackListener;
import org.zendal.customitems.reflection.ReflectionHelper;
import org.zendal.customitems.reflection.ReflectionHelperImpl;
import org.zendal.customitems.test.TestListener;

import java.io.IOException;
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
        this.getServer().getPluginManager().registerEvents(new ResourcePackListener(this, this.api), this);
    }


    private CustomItemsApi buildCustomItemsApi(Logger logger, ReflectionHelper reflection, CustomItemStackStorage storage) {
        return new CustomItemsApi() {
            private final CustomItemStackManager customItemStackManager = new CustomItemStackManagerImpl(logger, reflection, storage);

            private final CustomItemStackHelper customItemStackHelper = new CustomItemStackHelperImpl(storage);

            private CustomItemsConfiguration configuration;

            @Override
            public CustomItemStackManager getCustomItemStackManager() {
                return this.customItemStackManager;
            }

            @Override
            public CustomItemStackHelper getCustomItemStackHelper() {
                return customItemStackHelper;
            }

            @Override
            public void setConfigurationData(CustomItemsConfigurationData configurationData) throws IOException {
                if (configuration != null) {
                    throw new IllegalStateException("Configuration already defined");
                }
                this.configuration = new CustomItemsConfigurationImpl(logger, configurationData);
            }

            @Nullable
            @Override
            public CustomItemsConfiguration getConfiguration() {
                return configuration;
            }
        };
    }

    /**
     * Get API of plugin
     *
     * @return API of plugin
     */
    @NotNull
    public CustomItemsApi getCustomItemsApi() {
        return this.api;
    }

    @Override
    public void onDisable() {

    }
}
