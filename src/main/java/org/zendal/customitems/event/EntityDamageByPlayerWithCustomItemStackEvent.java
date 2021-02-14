package org.zendal.customitems.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;

/**
 * Invoke when player damage another entity with CustomItemStack.
 * It's just wrapper for control event or data event use EntityDamageByPlayerWithCustomItemStackEvent#getSourceEvent()
 *
 * @see AbstractCustomItemStack
 */
public final class EntityDamageByPlayerWithCustomItemStackEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final EntityDamageByEntityEvent sourceEvent;
    private final AbstractCustomItemStack abstractCustomItemStack;

    public EntityDamageByPlayerWithCustomItemStackEvent(EntityDamageByEntityEvent sourceEvent, AbstractCustomItemStack abstractCustomItemStack) {
        this.sourceEvent = sourceEvent;
        this.abstractCustomItemStack = abstractCustomItemStack;
    }

    /**
     * Get called event
     *
     * @return root event
     */
    public EntityDamageByEntityEvent getSourceEvent() {
        return sourceEvent;
    }

    public AbstractCustomItemStack getAbstractCustomItemStack() {
        return abstractCustomItemStack;
    }

    /**
     * Get damager as Player
     *
     * @return player who damage entity
     */
    public Player getDamager() {
        return (Player) this.sourceEvent.getDamager();
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
