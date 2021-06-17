package org.zendal.customitems.reflection;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Component for work with Reflection
 */
public interface ReflectionHelper {


    /**
     * Get all classes with annotation
     *
     * @param classLoader   class loader
     * @param prefixPackage root
     * @param annotation    searched annotation
     * @return classes with annotation
     */
    Collection<Class<?>> getAllClassesWithAnnotation(ClassLoader classLoader, String prefixPackage, Class<? extends Annotation> annotation);

}
