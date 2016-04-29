package com.fcs.demo.designPatterns.singleton;

/**
 * Created by Lucare.Feng on 2016/4/28.
 * 懒汉式
 */
public class SimpleSingleton {

    private static SimpleSingleton simpleSingleton = null;

    private SimpleSingleton(){
        System.out.println("Singleton is create");
    }

    public static synchronized SimpleSingleton getInstance() {
        if (simpleSingleton == null) {
            simpleSingleton = new SimpleSingleton();
        }
        return simpleSingleton;
    }

    public static void main(String[] args) {

        for (int i= 0 ; i< 5; i++) {
            new Thread(new Runnable() {
                long beginTime = System.currentTimeMillis();
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        SimpleSingleton.getInstance();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() - beginTime);
                }
            }).start();
        }

    }
}
