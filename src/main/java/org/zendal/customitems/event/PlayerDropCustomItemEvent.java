package org.zendal.customitems.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.CustomItem;

/**
 * Proxy for PlayerDropItemEvent
 */
public class PlayerDropCustomItemEvent extends PlayerDropItemEvent {

    private static final HandlerList handlers = new HandlerList();
    private final PlayerDropItemEvent sourceEvent;

    public PlayerDropCustomItemEvent(@NotNull PlayerDropItemEvent sourceEvent, @NotNull CustomItem drop) {
        super(sourceEvent.getPlayer(), drop);
        this.sourceEvent = sourceEvent;
    }

    @NotNull
    @Override
    public CustomItem getItemDrop() {
        return (CustomItem) super.getItemDrop();
    }

    @Override
    public boolean isCancelled() {
        return this.sourceEvent.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.sourceEvent.setCancelled(cancel);
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
