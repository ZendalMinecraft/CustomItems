package org.zendal.customitems.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.zendal.customitems.item.annotation.CustomItem;

public class AbstractCustomItemStack extends ItemStack {

    private Integer customModel;

    /**
     * Trying getting info from annotation
     */
    public AbstractCustomItemStack() {
        super(Material.AIR);
        var customAnnotationData = getClass().getAnnotation(CustomItem.class);
        final Material type = customAnnotationData.type();
        setType(type);
        this.customModel = customAnnotationData.customModelData();
        this.initCustomModelData();

    }

    public AbstractCustomItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
        if (!stack.hasItemMeta() || !stack.getItemMeta().hasCustomModelData()) {
            throw new RuntimeException("It's not a custom item");
        }

        this.customModel = stack.getItemMeta().getCustomModelData();
    }


    private void initCustomModelData() {
        var meta = this.getItemMeta();
        meta.setCustomModelData(this.customModel);
        this.setItemMeta(meta);
    }


    public Integer getCustomModel() {
        return customModel;
    }
}
