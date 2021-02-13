package org.zendal.customitems.reflection;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface ReflectionHelper {

        Collection<Class<?>> getAllClassesWithAnnotation(String prefixPackage, Class<? extends Annotation> annotation);

}
