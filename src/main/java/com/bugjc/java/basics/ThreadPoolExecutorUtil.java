package com.bugjc.java.basics;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * @author aoki
 * @date 2019/11/13
 * **/
public class ThreadPoolExecutorUtil {

    /**
     * 初始化线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 200,
            60, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2048), new ThreadFactoryBuilder().setNamePrefix("demo-pool-").build(), new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }

    /**
     * 从线程池中获取一个可用线程执行指令
     * @param command
     */
    public static void execute(Runnable command){
        threadPoolExecutor.execute(command);
    }

    /**
     * 多个线程同时执行指令
     * @param threadNum
     * @param command
     */
    public static void execute(int threadNum,Runnable command){
        //模拟多个线程同时执行指令
        for (int i = 0; i < threadNum; i++) {
            execute(command);
        }
    }
}
