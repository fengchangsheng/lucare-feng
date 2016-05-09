package com.fcs.demo.designPatterns.singleton;

/**
 * Created by Lucare.Feng on 2016/4/28.
 * 优点:
 * 延迟初始化(其它静态方法或者静态域在初始化时不会创建这个单例)
 * 解决并发带来的问题(以前的延迟创建还要判空)
 */
public class GreatSingleton {

    {
        System.out.println("init");
    }

    static {
        System.out.println("static init");
    }

    private GreatSingleton() {
        System.out.println("StaticSingleton is create");
    }

    public static void otherMethod(){
        System.out.println("other static method");
    }

    public static GreatSingleton getInstance(){
        return GreateHolder.greatSingleton;
    }

    private static class GreateHolder{

        private static GreatSingleton greatSingleton = new GreatSingleton();

    }

    public static void main(String[] args) {
        GreatSingleton.otherMethod();
    }

}
