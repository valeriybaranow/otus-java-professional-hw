package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        runTest(TestFramework.class);
    }

    private static void runTest(Class<TestFramework> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // allocates memory for 10 integers
        Map<Class, Method> annotationMethods = new HashMap<>();
        for (final Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Before.class)) {
                Annotation annotInstance = declaredMethod.getAnnotation(Before.class);
                System.out.println(annotInstance);
                Constructor<TestFramework> constructor = clazz.getConstructor();
                TestFramework object = constructor.newInstance();
                Method method = object.getClass().getDeclaredMethod(declaredMethod.getName());
                annotationMethods.put(Before.class, method);
                method.setAccessible(true);
                method.invoke(object);
            }
        }

//        for (HashMap: annotationMethods.entrySet()) {
        //                if (declaredMethod.isAnnotationPresent(Before.class)) {
        //                Annotation annotInstance = declaredMethod.getAnnotation(Before.class);
        //                System.out.println(annotInstance);
        //                Constructor<TestFramework> constructor = clazz.getConstructor();
        //                TestFramework object = constructor.newInstance();
        //                Method method = object.getClass().getDeclaredMethod(declaredMethod.getName());
        //                annotationMethod.put(Before.class, method);
        //                method.setAccessible(true);
        //                method.invoke(object);
        annotationMethods.entrySet().forEach(Map.Entry<Class, Method>::getClass);
    }
}
