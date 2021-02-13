package org.zendal.customitems.reflection;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class ReflectionHelperImpl implements ReflectionHelper {
    @Override
    public Collection<Class<?>> getAllClassesWithAnnotation(String prefixPackage, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(prefixPackage);
        return reflections.getTypesAnnotatedWith(annotation);
    }
}
