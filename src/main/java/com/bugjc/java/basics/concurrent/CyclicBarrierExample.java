package com.bugjc.java.basics.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        //example(count);

        example1(count,new BaseTask());
    }

    public static void example(int count) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(new OrderTask(cyclicBarrier));
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){
            Thread.sleep(10);
            System.out.println("任务暂停10毫秒");
        }
    }

    public static void example1(int count,Runnable runnable) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,runnable);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(new OrderTask(cyclicBarrier));
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){
            Thread.sleep(10);
            System.out.println("任务暂停10毫秒");
        }
    }


}
