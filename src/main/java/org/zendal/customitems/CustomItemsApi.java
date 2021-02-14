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


    CustomItemStackManager getCustomItemStackManager();


    CustomItemStackHelper getCustomItemStackHelper();

    /**
     * Setup configuration of CustomItems
     *
     * @param configurationData instance of configuration
     * @throws IOException When can't calculate hash of texture pack
     */
    void setConfigurationData(CustomItemsConfigurationData configurationData) throws IOException;

    @Nullable
    CustomItemsConfiguration getConfiguration();
}
