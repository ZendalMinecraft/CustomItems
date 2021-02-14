package org.zendal.customitems.configuration;

import lombok.Builder;
import lombok.Data;

import java.net.URL;

/**
 * Data of plugin configuration
 */
@Data
@Builder
public final class CustomItemsConfigurationData {

    /**
     * Resource pack url
     */
    private URL resourcePackUrl;


    /**
     * Is a texture pack required
     */
    @Builder.Default
    private boolean resourcePackRequired = true;

}
