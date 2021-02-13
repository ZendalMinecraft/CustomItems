package org.zendal.customitems.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;

/**
 * Little bit modified InventoryClickEvent, invoke when player clicked on inventory and this clicked item is CustomItemStack
 */
public final class PlayerClickOnCustomItemStackInInventoryEvent extends InventoryClickEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final InventoryClickEvent sourceEvent;
    private final AbstractCustomItemStack customItemStack;

    public PlayerClickOnCustomItemStackInInventoryEvent(@NotNull InventoryClickEvent sourceEvent, AbstractCustomItemStack customItemStack) {
        super(
                sourceEvent.getView(),
                sourceEvent.getSlotType(),
                sourceEvent.getSlot(),
                sourceEvent.getClick(),
                sourceEvent.getAction(),
                sourceEvent.getHotbarButton()
        );
        if (!(sourceEvent.getWhoClicked() instanceof Player)) {
            throw new IllegalArgumentException("Who clicked must be a Player");
        }
        this.sourceEvent = sourceEvent;
        this.customItemStack = customItemStack;
    }


    @NotNull
    @Override
    public Player getWhoClicked() {
        return (Player) super.getWhoClicked();
    }

    @NotNull
    @Override
    public AbstractCustomItemStack getCurrentItem() {
        return customItemStack;
    }

    @Override
    public boolean isCancelled() {
        return sourceEvent.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancel) {
        sourceEvent.setCancelled(cancel);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
