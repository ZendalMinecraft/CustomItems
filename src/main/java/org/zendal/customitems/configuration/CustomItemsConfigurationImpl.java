package org.zendal.customitems.configuration;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Simple implement of configuration
 */
public final class CustomItemsConfigurationImpl implements CustomItemsConfiguration {

    /**
     * Configuration data
     */
    private final CustomItemsConfigurationData customItemsConfigurationData;

    /**
     * Hash of texture pack
     */
    private final byte[] hash;

    /**
     * Constructor.
     *
     * @param configurationData configuration data
     * @throws IOException when can't load texture pack
     */
    public CustomItemsConfigurationImpl(CustomItemsConfigurationData configurationData) throws IOException {
        this.customItemsConfigurationData = configurationData;
        this.hash = encrypt(this.loadFile());
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

    /**
     * Load texture pack
     *
     * @return stream of texture pack
     * @throws IOException when can't load texture pack
     */
    private BufferedInputStream loadFile() throws IOException {
        return new BufferedInputStream(customItemsConfigurationData.getResourcePackUrl().openStream());
    }

    @Override
    public boolean resourcePackRequired() {
        return customItemsConfigurationData.isResourcePackRequired();
    }

    @Override
    public String getResourcePackUrl() {
        return customItemsConfigurationData.getResourcePackUrl().toString();
    }

    @Override
    public byte[] getResourcePackHash() {
        return this.hash;
    }
}
