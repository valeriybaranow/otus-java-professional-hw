package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestFrameworkHelper {

    /**
     * Выполняет метод класса
     */
    public static void callMethod(Class<TestFramework> testFrameworkClass, Method method, Object... args) {
        try {
            Constructor<TestFramework> constructor = testFrameworkClass.getConstructor();
            TestFramework object = constructor.newInstance();
            method.setAccessible(true);
            method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает методы клавва отсортированные в зависимости от аннотиции метода в следующем порядке </pre>@Before @Test @After
     */
    public static List<ArrayList<Method>> getSortedMethods(Class testFrameworkClass) {
        try {
            String packageName = testFrameworkClass.getPackage().getName();
            List<ArrayList<Method>> sortedMethods = new ArrayList<>();
            var listBefore = new ArrayList<Method>();
            var listTest = new ArrayList<Method>();
            var listAfter = new ArrayList<Method>();
            for (final Method declaredMethod : testFrameworkClass.getDeclaredMethods()) {
                Annotation[] annotations = declaredMethod.getAnnotations();
                for(Annotation annotation : annotations) {
                    if(annotation.toString().equals("@" + packageName + ".Before()")) {
                        listBefore.add(declaredMethod);
                    }
                    if(annotation.toString().equals("@" + packageName + ".Test()")) {
                        listTest.add(declaredMethod);
                    }
                    if(annotation.toString().equals("@" + packageName + ".After()")) {
                        listAfter.add(declaredMethod);
                    }
                }
            }
            sortedMethods.add(listBefore);
            sortedMethods.add(listTest);
            sortedMethods.add(listAfter);
            return sortedMethods;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
