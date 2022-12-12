package test.zendal.customitems.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

@CustomItem(type = Material.DIAMOND_AXE, customModelData = 12414, defaultFactory = false)
public final class MyTestItemWithCustomFactory extends AbstractCustomItemStack {
    public MyTestItemWithCustomFactory(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);

        this.updateItemMeta(itemMeta -> {
            itemMeta.setDisplayName("MyTestItemWithCustomFactory");
            return itemMeta;
        });
    }
}
