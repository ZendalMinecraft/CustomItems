package org.zendal.customitems.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.ItemStorage;
import org.zendal.customitems.event.EntityPickupCustomItemEvent;
import org.zendal.customitems.event.PlayerDropCustomItemEvent;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItem;
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
        try {
            AbstractCustomItemStack customItemStack = customItemStackFactory.build(itemStack);
            var event = new EntityPickupCustomItemEvent(e, new CustomItemProxy(e.getItem(), customItemStack));
            Bukkit.getPluginManager().callEvent(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        var value = this.getCustomItemByItem(e.getItemDrop());
        if (value != null) {
            var event = new PlayerDropCustomItemEvent(e, value);
            Bukkit.getPluginManager().callEvent(event);
        }
    }


    @Nullable
    private CustomItem getCustomItemByItem(Item item) {
        ItemStack itemStack = item.getItemStack();

        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasCustomModelData()) {
            return null;
        }

        try {
            var customItemStackFactory = itemStorage.getCustomItemStackFactory(itemStack.getType(), itemStack.getItemMeta().getCustomModelData());
            AbstractCustomItemStack customItemStack = customItemStackFactory.build(itemStack);
            return new CustomItemProxy(item, customItemStack);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @EventHandler
    public void onCustomPick(EntityPickupCustomItemEvent event) {
        if (event.getItem().getItemStack() instanceof ServiceItemStack) {
            System.out.println("YEEE" + event.getItem().getItemStack());
        }
        //    event.setCancelled(true);
    }


    @EventHandler
    public void ond(PlayerDropCustomItemEvent e) {
        System.out.println("Custom " + e.getItemDrop().getItemStack().getClass());
        System.out.println(e.getItemDrop().getItemStack());
    }
}
