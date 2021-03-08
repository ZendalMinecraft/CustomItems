package org.zendal.customitems.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.zendal.customitems.CustomItems;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

/**
 * Configuration class
 */
public class PluginConfiguration {

    /**
     * Constructor
     *
     * @param plugin CustomItems plugin
     */
    public PluginConfiguration(CustomItems plugin) {
        plugin.saveDefaultConfig();
        this.processConfig(plugin.getConfig()).ifPresent(configurationData -> {
            try {
                plugin.getCustomItemsApi().setConfigurationData(configurationData);
                plugin.getLogger().info("Set resource pack url from config.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Get configuration data from config
     *
     * @param configuration target config
     * @return optional configuration data
     */
    private Optional<CustomItemsConfigurationData> processConfig(FileConfiguration configuration) {
        try {
            var url = new URL(Objects.requireNonNull(configuration.getString("resource-pack.url")));
            return Optional.of(CustomItemsConfigurationData.builder()
                    .resourcePackUrl(url)
                    .resourcePackRequired(configuration.getBoolean("resource-pack.required"))
                    .build());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
