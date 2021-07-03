package test.zendal.customitems.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

import java.util.List;

@CustomItem(type = Material.ACACIA_BOAT, customModelData = 12414)
public final class MyTestItem extends AbstractCustomItemStack {
    public MyTestItem(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);

        var meta = this.getItemMeta();

        meta.setDisplayName("TestItem");
        meta.setLore(List.of(
                "lore - 1",
                "lore - 2"
        ));

        this.setItemMeta(meta);
    }
}
