package com.fcs.demo.collection.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * http://blog.csdn.net/xuefeng0707/article/details/40834595
 * Created by Lucare.Feng on 2016/3/31.
 */
public class ConcurrentHashMapTest {
    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

    public static void main(String[] args) {
        System.out.println(1 << 4);
        new Thread("Thread1"){
            @Override
            public void run() {
                map.put(3, 33);
            }
        }.start();

        new Thread("Thread2"){
            @Override
            public void run() {
                map.put(4, 44);
            }
        }.start();

        new Thread("Thread3"){
            @Override
            public void run() {
                map.put(7, 77);
            }
        }.start();
        System.out.println(map);
    }


}
