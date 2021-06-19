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

    /**
     * Get all nbt-keys of item
     *
     * @return collection of nbt-keys
     */
    Collection<String> getAllKey();

    /**
     * Check exists key
     *
     * @param key searched key
     * @return {@code true} if key exists else {@code false}
     */
    boolean hasKey(String key);

    /**
     * Get value String by key
     *
     * @param key key
     * @return value of string
     */
    String getString(String key);

    /**
     * Get value Boolean by key
     *
     * @param key key
     * @return value of boolean
     */
    boolean getBoolean(String key);


    /**
     * Get value Integer by key
     *
     * @param key key
     * @return value of integer
     */
    int getInteger(String key);

    /**
     * Get value Double by key
     *
     * @param key key
     * @return value of double
     */
    double getDouble(String key);


    /**
     * Set value String by key
     *
     * @param key key
     */
    void setString(String key, String value);

    /**
     * Set value Boolean by key
     *
     * @param key key
     */
    void setBoolean(String key, Boolean value);

    /**
     * Set value Integer by key
     *
     * @param key key
     */
    void setInteger(String key, Integer value);

    /**
     * Set value Double by key
     *
     * @param key key
     */
    void setDouble(String key, Double value);

    /**
     * Get prepared ItemStack
     *
     * @return itemstack
     */
    ItemStack getItemStack();
}
