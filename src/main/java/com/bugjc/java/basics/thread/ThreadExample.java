package com.bugjc.java.basics.thread;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = ThreadPoolExecutorUtil.getThreadPoolExecutor().getThreadFactory().newThread(new BlockAndWake());
        Thread thread2 = ThreadPoolExecutorUtil.getThreadPoolExecutor().getThreadFactory().newThread(new BlockAndWake());
        Thread thread3 = ThreadPoolExecutorUtil.getThreadPoolExecutor().getThreadFactory().newThread(new BlockAndWake());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(2000);
        log.info("唤醒线程名称：{}", thread2.getName());
        //唤醒处于阻塞状态的线程
        LockSupport.unpark(thread2);
        ThreadPoolExecutorUtil.getThreadPoolExecutor().shutdown();
    }


    /**
     * 阻塞与唤醒示例
     */
    @Slf4j
    public static class BlockAndWake implements Runnable{
        @Override
        public void run() {
            log.info("当前正在执行的线程名称：{}", Thread.currentThread().getName());
            //阻塞当前线程，如果掉用unpark(Thread)方法或被中断，才能从park()返回
            LockSupport.park();
        }
    }

}
