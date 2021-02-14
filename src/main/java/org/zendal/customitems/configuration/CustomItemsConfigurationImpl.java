package org.zendal.customitems.configuration;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
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

    private byte[] encrypt(String x) throws NoSuchAlgorithmException {
        var digest = java.security.MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(x.getBytes(StandardCharsets.UTF_8));
        return digest.digest();
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
    public byte[] getResourcePackHash() throws NoSuchAlgorithmException {
        return encrypt(customItemsConfigurationData.getResourcePackURL());
    }
}
