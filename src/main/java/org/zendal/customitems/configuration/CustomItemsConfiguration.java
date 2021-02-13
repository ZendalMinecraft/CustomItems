package org.zendal.customitems.configuration;

/**
 * Interface of configuration of plugin
 */
public interface CustomItemsConfiguration {

     boolean resourcePackRequired();

     String getResourcePackUrl();


     byte[] getResourcePackHash();
}
