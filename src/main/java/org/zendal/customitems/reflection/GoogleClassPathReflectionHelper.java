package org.zendal.customitems.reflection;

import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation based of GoogleClassPath
 */
public class GoogleClassPathReflectionHelper implements ReflectionHelper {

    @SneakyThrows
    @Override
    public Collection<Class<?>> getAllClassesWithAnnotation(ClassLoader classLoader, String prefixPackage, Class<? extends Annotation> annotation) {

        List<Class<?>> classes = new ArrayList<>();

        ClassPath path = ClassPath.from(classLoader);
        for (ClassPath.ClassInfo info : path.getTopLevelClassesRecursive(prefixPackage)) {
            Class<?> clazz = Class.forName(info.getName(), true, classLoader);
            if (clazz.getAnnotation(annotation) != null) {
                classes.add(clazz);
            }
        }

        return classes;
    }
}
