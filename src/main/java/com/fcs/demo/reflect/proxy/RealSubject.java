package com.fcs.demo.reflect.proxy;

/**
 * Created by Lucare.Feng on 2016/4/1.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call do something()");
        System.out.println(1/0);
    }
}
