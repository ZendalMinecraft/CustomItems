package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.ItemStorage;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.item.CustomItemProxy;

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
        var customItemStack = customItemStackFactory.build(itemStack);

        var event = new EntityPickupCustomItemEvent(e, e.getEntity(), new CustomItemProxy(e.getItem(), customItemStack), e.getRemaining());
        Bukkit.getPluginManager().callEvent(event);
    }

    @EventHandler
    public void onCustomPick(EntityPickupCustomItemEvent event) {
        System.out.println("YEEE" + event.getItem().getItemStack());
        //event.setCancelled(true);
    }
}
