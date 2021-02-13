package org.zendal.customitems;

import org.zendal.customitems.item.manager.CustomItemStackHelper;
import org.zendal.customitems.item.manager.CustomItemStackManager;

/**
 * This interface for control custom items
 */
public interface CustomItemsApi {


    CustomItemStackManager getCustomItemStackManager();


    CustomItemStackHelper getCustomItemStackHelper();

}
