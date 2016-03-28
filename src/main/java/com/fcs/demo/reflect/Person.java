package com.fcs.demo.reflect;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class Person {
    private String name;
    private int age;

    static {
        System.out.println("静态初始化块...");
        doSomething();
    }

    {
        System.out.println("初始化块...");
    }

    public static void doSomething(){
        System.out.println("just do something for static");
    }


    public void sayPerson(){
        System.out.println("I am  a person");
    }

    public void sayPerson(String param){
        System.out.println("I am  a "+ param +" person");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
