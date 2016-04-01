package com.fcs.demo.annotation;

import java.lang.reflect.Method;

/**
 * 利用反射获取注解内容
 * Created by Lucare.Feng on 2016/3/31.
 */
public class ProcessTool {

    public static void process(String clazz){
        Class targetClass = null;
        try {
            targetClass = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTag.class)) {
                MyTag myTag = method.getAnnotation(MyTag.class);
                System.out.println("被MyTag注解修饰的方法名:"+method.getName());
                System.out.println("方法" + method.getName() + "的MyTag注解内容为："+myTag.name()+","+myTag.age());
            }
        }

    }

    public static void main(String[] args) {
        process("com.fcs.demo.annotation.Apple");
    }
}
