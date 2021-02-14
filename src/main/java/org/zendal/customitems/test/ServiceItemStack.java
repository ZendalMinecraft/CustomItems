package org.zendal.customitems.test;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.annotation.CustomItem;

@CustomItem(type = Material.BARRIER, customModelData = 12)
public class ServiceItemStack extends AbstractCustomItemStack {

    public ServiceItemStack(ItemStack itemStack) {
        super(itemStack);

        if (!super.getNbtProvider().hasKey("attackMutation")) {
            super.getNbtProvider().setDouble("attackMutation", 0.5d);
        }

        itemStack.setItemMeta(getNbtProvider().getItemStack().getItemMeta());
    }

    public double getAttackMutation() {
        return super.getNbtProvider().getDouble("attackMutation");
    }

    public void setAttackMutation(double attack) {
        super.getNbtProvider().setDouble("attackMutation", attack);
        System.out.println("Attack = " + getAttackMutation());
    }
}
