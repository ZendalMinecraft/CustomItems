package org.zendal.customitems;

import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.configuration.CustomItemsConfiguration;
import org.zendal.customitems.configuration.CustomItemsConfigurationData;
import org.zendal.customitems.item.helper.CustomItemStackHelper;
import org.zendal.customitems.item.manager.CustomItemStackManager;

import java.io.IOException;

/**
 * This interface for control custom items
 */
public interface CustomItemsApi {


    /**
     * Get manager of CustomItemStack
     *
     * @return manager
     */
    CustomItemStackManager getCustomItemStackManager();


    /**
     * Get helper for work with CustomItemStack
     *
     * @return helper
     */
    CustomItemStackHelper getCustomItemStackHelper();

    /**
     * Setup configuration of CustomItems
     *
     * @param configurationData instance of configuration
     * @throws IOException           When can't calculate hash of texture pack
     * @throws IllegalStateException When configuration data already define
     */
    void setConfigurationData(CustomItemsConfigurationData configurationData) throws IllegalStateException, IOException;

    /**
     * Get current configuration of plugin
     *
     * @return configuration
     */
    @Nullable
    CustomItemsConfiguration getConfiguration();
}
