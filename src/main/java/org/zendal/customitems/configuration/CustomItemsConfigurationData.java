package org.zendal.customitems.configuration;

import lombok.Builder;
import lombok.Data;

/**
 * Data of plugin configuration
 */
@Data
@Builder
public final class CustomItemsConfigurationData {

    private String resourcePackURL;
    @Builder.Default
    private boolean flag = true;

}
