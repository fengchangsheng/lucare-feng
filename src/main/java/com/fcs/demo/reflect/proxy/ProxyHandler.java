package com.fcs.demo.reflect.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Lucare.Feng on 2016/4/1.
 * http://www.cnblogs.com/flyoung2008/archive/2013/08/11/3251148.html
 * http://blog.csdn.net/zhangerqing/article/details/42504281
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxied;//被代理对象

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("前置增强");
            Object object = method.invoke(proxied,args);
            System.out.println("后置增强");
            return object;
        } catch (Exception e) {
            System.out.println("事物回滚");
            return null;
        }
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),new Class[]{Subject.class},new ProxyHandler(realSubject));
        proxySubject.doSomething();

        //自己生成代理类
        String path = "D:/$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",
                RealSubject.class.getInterfaces());
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
