package org.zendal.customitems.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.nbt.NbtItemTagProvider;
import org.zendal.customitems.nbt.NbtItemTagProviderFactory;

public class AbstractCustomItemStack extends ItemStack {

    /**
     * I think it's bad practices setup field like this, but it's make better user experiences
     * TODO Come up with a better solution
     */
    private static final NbtItemTagProviderFactory factory = new NbtItemTagProviderFactory() {
    };

    private NbtItemTagProvider nbtItemTagProvider;

    public AbstractCustomItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
        if (!stack.hasItemMeta() || !stack.getItemMeta().hasCustomModelData()) {
            throw new RuntimeException("It's not a custom item");
        }
    }

    /**
     * Get component for manipulation with NBT tags.
     * Warning, don't forget after mutation update ItemMeta!
     *
     * @return provider
     */
    protected NbtItemTagProvider getNbtProvider() {
        if (nbtItemTagProvider == null) {
            nbtItemTagProvider = factory.buildProvider(this);
        }
        return nbtItemTagProvider;
    }
}
