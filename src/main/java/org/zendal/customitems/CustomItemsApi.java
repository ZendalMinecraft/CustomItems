package org.zendal.customitems;

import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.configuration.CustomItemsConfiguration;
import org.zendal.customitems.configuration.CustomItemsConfigurationData;
import org.zendal.customitems.item.helper.CustomItemStackHelper;
import org.zendal.customitems.item.manager.CustomItemStackManager;

/**
 * This interface for control custom items
 */
public interface CustomItemsApi {


    CustomItemStackManager getCustomItemStackManager();


    CustomItemStackHelper getCustomItemStackHelper();

    void setConfigurationData(CustomItemsConfigurationData configurationData);

    @Nullable
    CustomItemsConfiguration getConfiguration();
}
