package org.zendal.customitems.item.annotation;

import org.bukkit.Material;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomItem {
    Material type();

    int customModelData();

    /**
     * Use auto
     * @return
     */
    boolean defaultFactory() default true;
}
