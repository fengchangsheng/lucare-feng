package com.fcs.demo.thread;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class ThreadLocalTest {

    private  int num = 0;

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };

    // ②获取下一个序列值
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public int getNextNums() {
        num ++;
        return num;
    }

    public static void main(String[] args) {
        ThreadLocalTest sn = new ThreadLocalTest();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }


    private static class TestClient extends Thread{
        private ThreadLocalTest localTest;
        private int num;

        public TestClient(ThreadLocalTest localTest){
            this.localTest = localTest;
        }

        public TestClient(int num){
            this.num = num;
        }

        public void run(){
            for (int i = 0; i < 3; i++){
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> localTest ["
                + localTest.getNextNums() + "]");
            }
        }
    }
}
