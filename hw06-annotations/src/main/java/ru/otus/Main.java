package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(TestFramework.class);
    }

    private static void runTest(Class clazz) {
        List<ArrayList<Method>> sortedMethods = TestFrameworkHelper.getSortedMethods(clazz);
        for (ArrayList<Method> object : sortedMethods) {
            object.forEach((Method method) -> TestFrameworkHelper.callMethod(TestFramework.class, method));
        }
    }
}
