package org.zendal.customitems;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.configuration.CustomItemsConfiguration;
import org.zendal.customitems.configuration.CustomItemsConfigurationData;
import org.zendal.customitems.configuration.CustomItemsConfigurationImpl;
import org.zendal.customitems.configuration.PluginConfiguration;
import org.zendal.customitems.item.helper.CustomItemStackHelper;
import org.zendal.customitems.item.helper.CustomItemStackHelperImpl;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import org.zendal.customitems.item.manager.CustomItemStackManagerImpl;
import org.zendal.customitems.item.storage.CustomItemStackStorage;
import org.zendal.customitems.item.storage.HashMapCustomItemStackStorage;
import org.zendal.customitems.listener.PlayerListener;
import org.zendal.customitems.listener.ResourcePackListener;
import org.zendal.customitems.reflection.GoogleClassPathReflectionHelper;
import org.zendal.customitems.reflection.ReflectionHelper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class CustomItems extends JavaPlugin {


    public CustomItems()
    {
        super();
    }

    protected CustomItems(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }

    /**
     * Instance of API
     */
    private CustomItemsApi api;

    @Override
    public void onLoad() {
        var reflection = new GoogleClassPathReflectionHelper();
        var storage = new HashMapCustomItemStackStorage();
        this.api = this.buildCustomItemsApi(this.getLogger(), reflection, storage);

        new PluginConfiguration(this);
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this.api.getCustomItemStackManager()), this);
        this.getServer().getPluginManager().registerEvents(new ResourcePackListener(this, this.api), this);
    }

    private CustomItemsApi buildCustomItemsApi(Logger logger, ReflectionHelper reflection, CustomItemStackStorage storage) {
        return new CustomItemsApi() {
            private final CustomItemStackManager customItemStackManager = new CustomItemStackManagerImpl(logger, reflection, storage);

            private final CustomItemStackHelper customItemStackHelper = new CustomItemStackHelperImpl(customItemStackManager, storage);

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
            public void setConfigurationData(CustomItemsConfigurationData configurationData) throws IllegalStateException, IOException {
                if (configuration != null) {
                    throw new IllegalStateException("Configuration already defined");
                }
                this.configuration = new CustomItemsConfigurationImpl(configurationData);
                StringBuilder stringBuilder = new StringBuilder();
                for (byte b : this.configuration.getResourcePackHash()) {
                    stringBuilder.append(String.format("%02X", b));
                }
                logger.info("Calculate hash for texture pack: " + stringBuilder.toString());
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
