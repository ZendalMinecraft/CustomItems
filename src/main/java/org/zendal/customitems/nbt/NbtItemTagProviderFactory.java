package org.zendal.customitems.nbt;

import org.bukkit.inventory.ItemStack;

/**
 * Interface for create NbtItemTag provider
 */
public interface NbtItemTagProviderFactory {

    /**
     * Create default provider
     *
     * @param itemStack target item
     * @return provider
     */
    default NbtItemTagProvider buildProvider(ItemStack itemStack) {
        return new tr7ZwNbtItemTagProvider(itemStack);
    }
}
