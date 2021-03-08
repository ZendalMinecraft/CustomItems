package org.zendal.customitems.configuration;

/**
 * Interface of configuration of plugin
 */
public interface CustomItemsConfiguration {

    /**
     * Get flag about resource pack is required or not
     *
     * @return flag about resource pack is required or not
     */
    boolean resourcePackRequired();

    /**
     * Get resource pack URL
     *
     * @return resource pack URL
     */
    String getResourcePackUrl();

    /**
     * Get hash (SHA-1) of resource pack
     *
     * @return hash of resource pack
     */
    byte[] getResourcePackHash();
}
