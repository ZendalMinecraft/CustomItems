package org.zendal.customitems.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.nbt.NbtItemTagProvider;
import org.zendal.customitems.nbt.NbtItemTagProviderFactory;

import java.util.Objects;
import java.util.function.Function;

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
        if (!stack.hasItemMeta() || !Objects.requireNonNull(stack.getItemMeta()).hasCustomModelData()) {
            throw new RuntimeException("It's not a custom item");
        }
    }

    /**
     * Update item meta via snippet.
     *
     * @param itemMetaUpdateFunction function which return an updated item meta.
     */
    protected void updateItemMeta(Function<ItemMeta, ItemMeta> itemMetaUpdateFunction) {
        final var meta = this.getItemMeta();
        this.setItemMeta(itemMetaUpdateFunction.apply(meta));
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
