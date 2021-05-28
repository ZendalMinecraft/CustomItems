package org.zendal.customitems.item.annotation;

import org.bukkit.Material;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for mark class like a CustomItem
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomItem {

    /**
     * Type of CustomItem
     *
     * @return Material
     */
    Material type();

    /**
     * Custom model data
     *
     * @return custom model data
     */
    int customModelData();

    /**
     * Use auto factory or not
     *
     * @return status about using default factory
     */
    boolean defaultFactory() default true;
}
