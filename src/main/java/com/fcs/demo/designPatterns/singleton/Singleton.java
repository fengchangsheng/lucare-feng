package com.fcs.demo.designPatterns.singleton;

/**
 * Created by Lucare.Feng on 2016/5/5.
 * 饿汉式
 */
public class Singleton {

    private static Singleton singleton = new Singleton();

    private Singleton(){
        System.out.println("Singleton is create");
    }

    public static Singleton getInstance(){
        return singleton;
    }

}
