package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.zendal.customitems.ItemStorage;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.event.PlayerDropCustomItemEvent;

public class PlayerListener implements Listener {

    private final ItemStorage itemStorage;

    public PlayerListener(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityPickupItem(EntityPickupItemEvent e) {

        var value = ListenerUtils.getCustomItemByItem(itemStorage, e.getItem());

        if (value != null) {
            var event = new EntityPickupCustomItemEvent(e, value);
            Bukkit.getPluginManager().callEvent(event);
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        var value = ListenerUtils.getCustomItemByItem(itemStorage, e.getItemDrop());
        if (value != null) {
            var event = new PlayerDropCustomItemEvent(e, value);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void on(InventoryClickEvent e){
        System.out.println(e.getCurrentItem());
        System.out.println(e.getCursor());
    }

}
