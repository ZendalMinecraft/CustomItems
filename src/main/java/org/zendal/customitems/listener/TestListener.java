package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.ItemStorage;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemProxy;
import org.zendal.customitems.item.ServiceItemStack;

public class TestListener implements Listener {

    private final ItemStorage itemStorage;

    public TestListener(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPick(EntityPickupItemEvent e) {
        ItemStack itemStack = e.getItem().getItemStack();

        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasCustomModelData()) {
            return;
        }

        var customItemStackFactory = itemStorage.getCustomItemStackFactory(itemStack.getType(), itemStack.getItemMeta().getCustomModelData());
        AbstractCustomItemStack customItemStack = null;
        try {
            customItemStack = customItemStackFactory.build(itemStack);
            var event = new EntityPickupCustomItemEvent(e, new CustomItemProxy(e.getItem(), customItemStack));
            Bukkit.getPluginManager().callEvent(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @EventHandler
    public void onCustomPick(EntityPickupCustomItemEvent event) {
        if (event.getItem().getItemStack() instanceof ServiceItemStack) {
            System.out.println("YEEE" + event.getItem().getItemStack());
        }
        //    event.setCancelled(true);
    }
}
