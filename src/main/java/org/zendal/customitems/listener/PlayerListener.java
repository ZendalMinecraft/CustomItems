package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.zendal.customitems.event.EntityDamageByPlayerWithCustomItemStackEvent;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.event.PlayerClickOnCustomItemStackInInventoryEvent;
import org.zendal.customitems.event.PlayerDropCustomItemEvent;
import org.zendal.customitems.item.manager.CustomItemStackManager;

public class PlayerListener implements Listener {

    private final CustomItemStackManager customItemStackManager;

    public PlayerListener(CustomItemStackManager customItemStackManager) {
        this.customItemStackManager = customItemStackManager;
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


    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamageEntityWithCustomItemStack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            var player = (Player) e.getDamager();
            var customItemStack = ListenerUtils.getCustomItemStackByItemStack(
                    this.customItemStackManager,
                    player.getInventory().getItemInMainHand()
            );
            if (customItemStack != null) {
                Bukkit.getPluginManager().callEvent(new EntityDamageByPlayerWithCustomItemStackEvent(e, customItemStack));
            }
        }
    }
}
