package org.zendal.customitems.item;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CustomItemProxy implements CustomItem {

    private final Item item;
    private AbstractCustomItemStack customItemStack;

    public CustomItemProxy(Item item, AbstractCustomItemStack customItemStack) {
        this.item = item;
        this.customItemStack = customItemStack;
    }

    @Override
    public @NotNull AbstractCustomItemStack getItemStack() {
        return this.customItemStack;
    }

    @Override
    public void setItemStack(@NotNull ItemStack stack) {
        if (!(stack instanceof AbstractCustomItemStack)) {
            throw new IllegalArgumentException("Can't change custom item stack to default item stack");
        }
        this.customItemStack = (AbstractCustomItemStack) stack;
        this.item.setItemStack(stack);
    }

    @Override
    public int getPickupDelay() {
        return item.getPickupDelay();
    }

    @Override
    public void setPickupDelay(int delay) {
        item.setPickupDelay(delay);
    }

    @Override
    public void setOwner(@Nullable UUID owner) {
        item.setOwner(owner);
    }

    @Nullable
    @Override
    public UUID getOwner() {
        return item.getOwner();
    }

    @Override
    public void setThrower(@Nullable UUID uuid) {
        item.setThrower(uuid);
    }

    @Nullable
    @Override
    public UUID getThrower() {
        return item.getThrower();
    }

    @NotNull
    @Override
    public Location getLocation() {
        return item.getLocation();
    }

    @Nullable
    @Override
    public Location getLocation(@Nullable Location loc) {
        return item.getLocation(loc);
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {
        item.setVelocity(velocity);
    }

    @NotNull
    @Override
    public Vector getVelocity() {
        return item.getVelocity();
    }

    @Override
    public double getHeight() {
        return item.getHeight();
    }

    @Override
    public double getWidth() {
        return item.getWidth();
    }

    @NotNull
    @Override
    public BoundingBox getBoundingBox() {
        return item.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return item.isOnGround();
    }

    @Override
    public boolean isInWater() {
        return item.isInWater();
    }

    @NotNull
    @Override
    public World getWorld() {
        return item.getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        item.setRotation(yaw, pitch);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return item.teleport(location);
    }

    @Override
    public boolean teleport(@NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause cause) {
        return item.teleport(location, cause);
    }

    @Override
    public boolean teleport(@NotNull Entity destination) {
        return item.teleport(destination);
    }

    @Override
    public boolean teleport(@NotNull Entity destination, @NotNull PlayerTeleportEvent.TeleportCause cause) {
        return item.teleport(destination, cause);
    }

    @NotNull
    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return item.getNearbyEntities(x, y, z);
    }

    @Override
    public int getEntityId() {
        return item.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return item.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return item.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        item.setFireTicks(ticks);
    }

    @Override
    public void remove() {
        item.remove();
    }

    @Override
    public boolean isDead() {
        return item.isDead();
    }

    @Override
    public boolean isValid() {
        return item.isValid();
    }

    @Override
    public void sendMessage(@NotNull String message) {
        item.sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull String[] messages) {
        item.sendMessage(messages);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {
        item.sendMessage(sender, message);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {
        item.sendMessage(sender, messages);
    }

    @NotNull
    @Override
    public Server getServer() {
        return item.getServer();
    }

    @NotNull
    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public boolean isPersistent() {
        return item.isPersistent();
    }

    @Override
    public void setPersistent(boolean persistent) {
        item.setPersistent(persistent);
    }

    @Nullable
    @Deprecated
    @Override
    public Entity getPassenger() {
        return item.getPassenger();
    }

    @Deprecated
    @Override
    public boolean setPassenger(@NotNull Entity passenger) {
        return item.setPassenger(passenger);
    }

    @NotNull
    @Override
    public List<Entity> getPassengers() {
        return item.getPassengers();
    }

    @Override
    public boolean addPassenger(@NotNull Entity passenger) {
        return item.addPassenger(passenger);
    }

    @Override
    public boolean removePassenger(@NotNull Entity passenger) {
        return item.removePassenger(passenger);
    }

    @Override
    public boolean isEmpty() {
        return item.isEmpty();
    }

    @Override
    public boolean eject() {
        return item.eject();
    }

    @Override
    public float getFallDistance() {
        return item.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        item.setFallDistance(distance);
    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent event) {
        item.setLastDamageCause(event);
    }

    @Nullable
    @Override
    public EntityDamageEvent getLastDamageCause() {
        return item.getLastDamageCause();
    }

    @NotNull
    @Override
    public UUID getUniqueId() {
        return item.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return item.getTicksLived();
    }

    @Override
    public void setTicksLived(int value) {
        item.setTicksLived(value);
    }

    @Override
    public void playEffect(@NotNull EntityEffect type) {
        item.playEffect(type);
    }

    @NotNull
    @Override
    public EntityType getType() {
        return item.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return item.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return item.leaveVehicle();
    }

    @Nullable
    @Override
    public Entity getVehicle() {
        return item.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        item.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return item.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        item.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return item.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        item.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return item.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return item.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        item.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return item.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        item.setGravity(gravity);
    }

    @Override
    public int getPortalCooldown() {
        return item.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        item.setPortalCooldown(cooldown);
    }

    @NotNull
    @Override
    public Set<String> getScoreboardTags() {
        return item.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String tag) {
        return item.addScoreboardTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String tag) {
        return item.removeScoreboardTag(tag);
    }

    @NotNull
    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return item.getPistonMoveReaction();
    }

    @NotNull
    @Override
    public BlockFace getFacing() {
        return item.getFacing();
    }

    @NotNull
    @Override
    public Pose getPose() {
        return item.getPose();
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return item.spigot();
    }

    @Nullable
    @Override
    public String getCustomName() {
        return item.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable String name) {
        item.setCustomName(name);
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        item.setMetadata(metadataKey, newMetadataValue);
    }

    @NotNull
    @Override
    public List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return item.getMetadata(metadataKey);
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return item.hasMetadata(metadataKey);
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        item.removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return item.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return item.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return item.hasPermission(name);
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return item.hasPermission(perm);
    }

    @NotNull
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return item.addAttachment(plugin, name, value);
    }

    @NotNull
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return item.addAttachment(plugin);
    }

    @Nullable
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return item.addAttachment(plugin, name, value, ticks);
    }

    @Nullable
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return item.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        item.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        item.recalculatePermissions();
    }

    @NotNull
    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return item.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return item.isOp();
    }

    @Override
    public void setOp(boolean value) {
        item.setOp(value);
    }

    @NotNull
    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return item.getPersistentDataContainer();
    }
}
