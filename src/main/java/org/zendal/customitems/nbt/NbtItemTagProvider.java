package org.zendal.customitems.nbt;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Interface for manipulation with NBT tags of ItemStack
 *
 * @see <a href="https://minecraft.gamepedia.com/Tutorials/Command_NBT_tags">About NBT tags</a>
 * @see org.bukkit.inventory.ItemStack
 */
public interface NbtItemTagProvider {

    Collection<String> getAllKey();

    boolean hasKey(String key);

    String getString(String key);

    boolean getBoolean(String key);

    int getInteger(String key);

    double getDouble(String key);


    void setString(String key, String value);

    void setBoolean(String key, Boolean value);

    void setInteger(String key, Integer value);

    void setDouble(String key, Double value);

    ItemStack getItemStack();
}
