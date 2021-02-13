package org.zendal.customitems.configuration;

import java.util.logging.Logger;

/**
 * Simple implement of configuration
 */
public class CustomItemsConfigurationImpl implements CustomItemsConfiguration {

    private final Logger logger;

    public CustomItemsConfigurationImpl(Logger logger, CustomItemsConfigurationData configurationData) {
        this.logger = logger;
        //TODO logic Ivan
    }

    @Override
    public boolean resourcePackRequired() {
        return false;
    }

    @Override
    public String getResourcePackUrl() {
        return null;
    }

    @Override
    public byte[] getResourcePackHash() {
        return null;
    }
}
