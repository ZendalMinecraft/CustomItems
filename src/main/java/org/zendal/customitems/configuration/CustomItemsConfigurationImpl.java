package org.zendal.customitems.configuration;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Simple implement of configuration
 */
public class CustomItemsConfigurationImpl implements CustomItemsConfiguration {

    private final Logger logger;
    private final CustomItemsConfigurationData customItemsConfigurationData;

    public CustomItemsConfigurationImpl(Logger logger, CustomItemsConfigurationData configurationData) {
        this.logger = logger;
        this.customItemsConfigurationData = configurationData;
    }

    @SneakyThrows
    private byte[] encrypt(BufferedInputStream file) {
        var digest = java.security.MessageDigest.getInstance("SHA-1");
        int n = 0;
        byte[] buffer = new byte[8192];
        while (n != -1) {
            n = file.read(buffer);
            if (n > 0) {
                digest.update(buffer, 0, n);
            }
        }
        return digest.digest();
    }

    @SneakyThrows
    private BufferedInputStream loadFile() {
        return new BufferedInputStream(new URL(customItemsConfigurationData.getResourcePackURL()).openStream());
    }

    @Override
    public boolean resourcePackRequired() {
        return false;
    }

    @Override
    public String getResourcePackUrl() {
        return customItemsConfigurationData.getResourcePackURL();
    }

    @Override
    public byte[] getResourcePackHash() {
        return encrypt(this.loadFile());
    }
}
