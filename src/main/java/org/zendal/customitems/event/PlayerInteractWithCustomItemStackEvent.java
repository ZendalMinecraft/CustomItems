package org.zendal.customitems.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zendal.customitems.item.AbstractCustomItemStack;

public class PlayerInteractWithCustomItemStackEvent extends PlayerInteractEvent {

    private static final HandlerList handlers = new HandlerList();
    private final PlayerInteractEvent sourceEvent;

    private final AbstractCustomItemStack customItemStack;


    public PlayerInteractWithCustomItemStackEvent(PlayerInteractEvent sourceEvent, @NotNull AbstractCustomItemStack customItemStack) {
        super(
                sourceEvent.getPlayer(),
                sourceEvent.getAction(),
                customItemStack,
                sourceEvent.getClickedBlock(),
                sourceEvent.getBlockFace(),
                sourceEvent.getHand()
        );
        this.sourceEvent = sourceEvent;
        this.customItemStack = customItemStack;
    }


    @Nullable
    @Override
    public AbstractCustomItemStack getItem() {
        return this.customItemStack;
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

    @NotNull
    @Override
    public Result useInteractedBlock() {
        return this.sourceEvent.useInteractedBlock();
    }


    @NotNull
    @Override
    public Result useItemInHand() {
        return this.sourceEvent.useItemInHand();
    }


    @Override
    public void setUseInteractedBlock(@NotNull Event.Result useInteractedBlock) {
        this.sourceEvent.setUseInteractedBlock(useInteractedBlock);
    }


    @Override
    public void setUseItemInHand(@NotNull Event.Result useItemInHand) {
        this.sourceEvent.setUseItemInHand(useItemInHand);
    }
}
