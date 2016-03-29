package com.fcs.demo.thread;

/**
 * Created by Lucare.Feng on 2016/3/29.
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        Thread myThread2 = new MyThread();
        myThread.start();
        myThread2.start();
    }

    private static class MyThread extends Thread {

        private static int num;

        private static ThreadLocal<Integer> num2 = new ThreadLocal<Integer>();

        public void getNum() {
            num++;
        }

        public void getNum2() {
            if (num2.get() == null) {
                num2.set(0);
            } else {
                num2.set(num2.get() + 1);
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                getNum();
//                System.out.println("........." + Thread.currentThread().getName() + "........" + num2.get());
                System.out.println("........." + Thread.currentThread().getName() + "........" + num);
            }

        }
    }


}
