package com.bugjc.java.basics.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    /**
     * Common Thread Pool
     */
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());




    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        example(count);

        //example1(count,new BaseTask());
    }

    public static void example(int count) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            pool.execute(new OrderTask(cyclicBarrier));
        }
        pool.shutdown();

        while (!pool.isTerminated()){
            Thread.sleep(10);
            System.out.println("任务暂停10毫秒");
        }
    }

    private static void example1(int count, Runnable runnable) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,runnable);
        for (int i = 0; i < count; i++) {
            pool.execute(new OrderTask(cyclicBarrier));
        }
        pool.shutdown();

        while (!pool.isTerminated()){
            Thread.sleep(10);
            System.out.println("任务暂停10毫秒");
        }
    }


}
