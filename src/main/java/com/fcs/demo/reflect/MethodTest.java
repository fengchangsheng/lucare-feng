package com.fcs.demo.reflect;

import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.lang.reflect.Method;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class MethodTest {
    public static void main(String[] args) {
        try {
            Class personClass = Class.forName("com.fcs.demo.reflect.Person");
            Method method = personClass.getMethod("sayPerson",new Class[]{String.class});
            String arg = "beautiful";
            method.invoke(personClass.newInstance(),arg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
