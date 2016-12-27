package com.fcs.demo.thread.executor;

import java.util.concurrent.*;

/**
 * Created by Lucare.Feng on 2016/12/27.
 */
public class ExecutorServiceTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("i am a dog");
//            }
//        });

        try {
            Future futureRunnable = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        System.out.println("i am awake");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("no result return");
            System.out.println("i am do after runnable " + futureRunnable.get());
            Future<Integer> future = executorService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(5000);
                    return 8;
                }

            });
            System.out.println("i am do after callable");
            System.out.println("result is " + future.get());
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
