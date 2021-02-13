package org.zendal.customitems.item.annotation;

import org.bukkit.Material;
import org.zendal.customitems.CustomItemStackFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomItem {
    Material type();

    int customModelData();

    /**
     * Use auto
     * @return
     */
    boolean defaultFactory() default true;
}
