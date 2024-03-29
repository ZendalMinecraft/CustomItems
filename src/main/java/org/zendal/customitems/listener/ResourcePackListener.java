package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.zendal.customitems.CustomItemsApi;

public class ResourcePackListener implements Listener {
    private final Plugin plugin;

    private final CustomItemsApi customItemsApi;

    public ResourcePackListener(Plugin plugin, CustomItemsApi customItemsApi) {
        this.plugin = plugin;
        this.customItemsApi = customItemsApi;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (this.customItemsApi.getConfiguration() == null) {
            return;
        }
        var configuration = this.customItemsApi.getConfiguration();
        Bukkit.getScheduler().runTaskLater(plugin, () ->
                        event.getPlayer().setResourcePack(configuration.getResourcePackUrl(), configuration.getResourcePackHash()),
                20L
        );

    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        if (this.customItemsApi.getConfiguration() == null) {
            return;
        }
        if (this.customItemsApi.getConfiguration().resourcePackRequired() &&
                (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED || event.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
            event.getPlayer().kickPlayer("You did not accept the ResourcePack request.");
        }
    }
}
