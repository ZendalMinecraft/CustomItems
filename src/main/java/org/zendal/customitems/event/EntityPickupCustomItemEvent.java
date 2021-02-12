package org.zendal.customitems.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.CustomItem;

/**
 * Proxy of EntityPickupItemEvent but
 */
public class EntityPickupCustomItemEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final CustomItem item;
    private final EntityPickupItemEvent sourceEvent;
    private final int remaining;

    public EntityPickupCustomItemEvent(EntityPickupItemEvent e, @NotNull LivingEntity entity, @NotNull CustomItem item, int remaining) {
        super(entity);
        this.sourceEvent = e;
        this.item = item;
        this.remaining = remaining;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets the Item picked up by the entity.
     *
     * @return Item
     */
    @NotNull
    public CustomItem getItem() {
        return item;
    }

    /**
     * Gets the amount remaining on the ground, if any
     *
     * @return amount remaining on the ground
     */
    public int getRemaining() {
        return remaining;
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
        return sourceEvent.getHandlers();
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }


}
