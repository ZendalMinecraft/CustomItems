package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.zendal.customitems.configuration.CustomItemsConfiguration;

public class ResourcePackListener implements Listener {
    private final Plugin plugin;

    private final CustomItemsConfiguration configuration;

    public ResourcePackListener(Plugin plugin, CustomItemsConfiguration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> event.getPlayer().setResourcePack(configuration.getResourcePackUrl(), configuration.getResourcePackHash()), 20L);

    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        //TODO update event check is Required Ivan
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().kickPlayer(" You did not accept the resourcepack request.");
        }
    }
}
