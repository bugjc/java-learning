package com.bugjc.java.basics.atomic;


import com.bugjc.java.basics.ThreadPoolExecutorUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全计数器示例
 * @author aoki
 * @date 2019/11/13
 * **/
public class ThreadSafeCounterExample {

    /**
     * 初始化线程安全计数器对象值
     */
    private static AtomicInteger safeCounter = new AtomicInteger(0);

    /**
     * 初始化不安全的计数器对象值
     */
    private static int unsafeCounter = 0;

    /**
     * 多线程计数值加1
     */
    public static void increment(){
        //模拟多个线程同时调用计数器，每个计数器自增 10000
        ThreadPoolExecutorUtil.execute(50, new Runnable() {
            @Override
            public void run() {
                int index = 10000;
                for (int i = 0; i < index; i++) {
                    safeCount();
                    unsafeCount();
                }
            }
        });
    }

    /**
     * 获取安全计数器值
     * @return
     */
    static int getSafeCount(){
        return safeCounter.get();
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private static void safeCount() {
        for (;;) {
            int i = safeCounter.get();
            boolean suc = safeCounter.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 获取非线程安全计数值
     * @return
     */
    static int getUnsafeCount(){
        return unsafeCounter;
    }

    /**
     * 非线程安全计数器
     */
    private static void unsafeCount() {
        unsafeCounter++;
    }

}
