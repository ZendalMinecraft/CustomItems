package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.zendal.customitems.configuration.CustomItemsConfiguration;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ResourcePackListener implements Listener {
    private final Plugin plugin;

    private final CustomItemsConfiguration configuration;

    public ResourcePackListener(Plugin plugin, CustomItemsConfiguration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
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
        if (configuration.resourcePackRequired()){
            event.getPlayer().kickPlayer(" You did not accept the resourcepack request.");
        }
    }
}
