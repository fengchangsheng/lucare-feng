package com.fcs.demo.collection.concurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lucare.Feng on 2016/4/19.
 */
public class TestConcurrentHashMap {

    private static Map<Integer, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Thread("thread1"){
            @Override
            public void run() {
                for (int i = 0; i< 10 ;i++){
                    map.put(i, i+" oak from1 "+Thread.currentThread().getName());
                }
            }
        }.start();

        new Thread("thread2"){
            @Override
            public void run() {
                for (int i = 0; i< 10 ;i++){
                    System.out.println(map.get(i));
                }
            }
        }.start();

        new Thread("thread3"){
            @Override
            public void run() {
                for (int i = 0; i< 10 ;i++){
                    map.put(i, i+" oak from3 "+Thread.currentThread().getName());
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("last map is "+map);
    }
}
