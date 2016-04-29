package com.fcs.demo.collection.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/4/6.
 * http://www.ticmy.com/?p=97
 */
public class TestHashMap {

    private static Map<Integer, String> map = new HashMap<Integer, String>();

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<String, String>();
//        int capacity = 1;
//        capacity <<= 1;
//        System.out.println(capacity);
        Thread thread1 = new Thread(new MyThread(map));
        thread1.start();
        Thread thread2 = new Thread(new MyThreadRead(map));
        thread2.start();
        Thread thread3 = new Thread(new MyThread(map));
        thread3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("last map is "+map);
    }
}

class MyThread implements Runnable{

    private Map map ;

    public MyThread(Map map){
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i< 10 ;i++){
            map.put(i, i+" oak from "+Thread.currentThread().getName());
        }
    }
}

class MyThreadRead implements Runnable{

    private Map map ;

    public MyThreadRead(Map map){
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i< 10 ;i++){
            System.out.println(map.get(i));
        }
    }
}


