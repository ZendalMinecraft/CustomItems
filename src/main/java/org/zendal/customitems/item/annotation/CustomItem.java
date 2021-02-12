package org.zendal.customitems.item.annotation;

import org.bukkit.Material;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomItem {
    Material type();

    int customModelData();
}
