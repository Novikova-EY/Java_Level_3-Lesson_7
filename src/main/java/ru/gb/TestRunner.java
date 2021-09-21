package ru.gb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    public static void main(String[] args) {
        start(Tester.class);
    }

    public static void start(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        final Map<Integer, ArrayList<Method>> sortedTestMethods = new TreeMap<>();
        boolean runBeforeSuite = false;
        boolean runAfterSuite = false;

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (!runBeforeSuite) {
                    ArrayList<Method> arrayListForBeforeSuit = new ArrayList<>();
                    arrayListForBeforeSuit.add(method);
                    sortedTestMethods.put(0, arrayListForBeforeSuit);
                    runBeforeSuite = true;
                } else {
                    throw new RuntimeException("Method with BeforeSuite annotation has been run");
                }
            }

            if (method.isAnnotationPresent(Test.class)) {
                if ((method.getAnnotation(Test.class).priority() >= 1) && (method.getAnnotation(Test.class).priority() <= 10)) {
                    if (sortedTestMethods.containsKey(method.getAnnotation(Test.class).priority())) {
                        ArrayList<Method> arrayForDoublePriority = sortedTestMethods.get(method.getAnnotation(Test.class).priority());
                        arrayForDoublePriority.add(method);
                        sortedTestMethods.put(method.getAnnotation(Test.class).priority(), arrayForDoublePriority);
                    } else {
                        ArrayList<Method> arrayListForPriority = new ArrayList<>();
                        arrayListForPriority.add(method);
                        sortedTestMethods.put(method.getAnnotation(Test.class).priority(), arrayListForPriority);
                    }
                } else {
                    throw new RuntimeException("Test class priority need to be from 1 to 10");
                }
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (!runAfterSuite) {
                    ArrayList<Method> arrayListForAfterSuit = new ArrayList<>();
                    arrayListForAfterSuit.add(method);
                    sortedTestMethods.put(methods.length + 1, arrayListForAfterSuit);
                    runAfterSuite = true;
                } else {
                    throw new RuntimeException("Method with AfterSuite annotation has been run");
                }
            }
        }

        try {
            Object o = clazz.getDeclaredConstructor().newInstance();
            for (Integer key : sortedTestMethods.keySet()) {
                for (Method m : sortedTestMethods.get(key)) {
                    m.invoke(o);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
