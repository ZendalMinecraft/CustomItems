package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.event.PlayerClickOnCustomItemStackInInventoryEvent;
import org.zendal.customitems.event.PlayerDropCustomItemEvent;
import org.zendal.customitems.item.manager.CustomItemStackManager;

public class PlayerListener implements Listener {

    private final CustomItemStackManager customItemStackManager;
    private final org.bukkit.plugin.Plugin plugin;

    public PlayerListener(CustomItemStackManager customItemStackManager, Plugin plugin) {
        this.customItemStackManager = customItemStackManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                event.getPlayer().setResourcePack("https://tlauncher.org/download/12817");
            }
        }, 20);

    }

    @EventHandler
    public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event) {
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().kickPlayer(" You did not accept the resourcepack request.");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityPickupItem(EntityPickupItemEvent e) {

        var value = ListenerUtils.getCustomItemByItem(customItemStackManager, e.getItem());

        if (value != null) {
            var event = new EntityPickupCustomItemEvent(e, value);
            Bukkit.getPluginManager().callEvent(event);
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        var value = ListenerUtils.getCustomItemByItem(customItemStackManager, e.getItemDrop());
        if (value != null) {
            var event = new PlayerDropCustomItemEvent(e, value);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player) || e.getCurrentItem() == null) {
            return;
        }
        var customItemStack = ListenerUtils.getCustomItemStackByItemStack(this.customItemStackManager, e.getCurrentItem());
        if (customItemStack != null) {
            Bukkit.getPluginManager().callEvent(new PlayerClickOnCustomItemStackInInventoryEvent(e, customItemStack));
        }
    }

}
