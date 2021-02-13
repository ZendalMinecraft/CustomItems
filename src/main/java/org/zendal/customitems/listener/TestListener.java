package org.zendal.customitems.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.event.PlayerDropCustomItemEvent;
import org.zendal.customitems.test.ServiceItemStack;

public class TestListener implements Listener {


    @EventHandler
    public void onCustomPick(EntityPickupItemEvent event) {
        System.out.println("?");
        event.getEntity().damage(1d);
    }

    @EventHandler
    public void onCustomPick(EntityPickupCustomItemEvent event) {
        if (event.getItem().getItemStack() instanceof ServiceItemStack) {
            System.out.println("Pickup custom item: " + event.getItem().getItemStack());
            event.getEntity().damage(2d);
        }
    }


    @EventHandler
    public void ond(PlayerDropCustomItemEvent e) {
        System.out.println("Drop custom item " + e.getItemDrop().getItemStack().getClass());
        System.out.println(e.getItemDrop().getItemStack());
        // e.setCancelled(true);
        // e.getItemDrop().teleport(new Location(e.getItemDrop().getLocation().getWorld(), e.getItemDrop().getLocation().getBlockX(), 100, e.getItemDrop().getLocation().getBlockZ()));
    }
}
