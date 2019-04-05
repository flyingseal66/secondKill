package org.secondKill.java_learning.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PtivateMethodsTest {

    private int a = 9;

    private void priMethod() {
        System.out.println("enter priMethod!");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        PtivateMethodsTest test = new PtivateMethodsTest();
        Method[] methods = PtivateMethodsTest.class.getDeclaredMethods();
        methods[1].invoke(test);
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
        }
    }

}
