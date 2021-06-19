package org.zendal.customitems.nbt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Implementation based on tr7Zw library
 *
 * @see <a href="https://github.com/tr7zw/Item-NBT-API">GitHub Page of NBT API</a>
 * @see <a href="https://github.com/tr7zw/Item-NBT-API/wiki/Using-the-NBT-API">GitHub doc page of NBT API</a>
 */
public final class tr7ZwNbtItemTagProvider implements NbtItemTagProvider {


    private final NBTItem nbtItem;

    public tr7ZwNbtItemTagProvider(ItemStack itemStack) {
        this.nbtItem = new NBTItem(itemStack, true);
    }

    @Override
    public Collection<String> getAllKey() {
        return this.nbtItem.getKeys();
    }

    @Override
    public boolean hasKey(String key) {
        return this.nbtItem.hasKey(key);
    }

    @Override
    public String getString(String key) {
        return this.nbtItem.getString(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return this.nbtItem.getBoolean(key);
    }

    @Override
    public int getInteger(String key) {
        return this.nbtItem.getInteger(key);
    }

    @Override
    public double getDouble(String key) {
        return this.nbtItem.getDouble(key);
    }

    @Override
    public void setString(String key, String value) {
        this.nbtItem.setString(key, value);
    }

    @Override
    public void setBoolean(String key, Boolean value) {
        this.nbtItem.setBoolean(key, value);
    }

    @Override
    public void setInteger(String key, Integer value) {
        this.nbtItem.setInteger(key, value);
    }

    @Override
    public void setDouble(String key, Double value) {
        this.nbtItem.setDouble(key, value);
    }

    @Override
    public ItemStack getItemStack() {
        return this.nbtItem.getItem();
    }
}
