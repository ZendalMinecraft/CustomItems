package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.zendal.customitems.CustomItemsApi;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            try {
                event.getPlayer().setResourcePack(configuration.getResourcePackUrl(), configuration.getResourcePackHash());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }, 20L);

    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        if (this.customItemsApi.getConfiguration() == null) {
            return;
        }
        if (this.customItemsApi.getConfiguration().resourcePackRequired()) {
            event.getPlayer().kickPlayer("You did not accept the ResourcePack request.");
        }
    }
}
