package com.bugjc.java.basics.lock;

import cn.hutool.core.thread.ThreadUtil;
import com.bugjc.java.basics.ThreadPoolExecutorUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author aoki
 * @date 2022/1/5
 * **/
public class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 场景 1：如果发现该操作已经在执行中则不再执行（有状态执行）
     */
    public void lock1() {
        System.out.println("尝试获取锁...");
        //如果已经被 lock ,则立刻返回 false 不会等待，达到忽略操作的效果
        if (lock.tryLock()){
            try {
                System.out.println("操作...");
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
            } finally {
              lock.unlock();
            }
        }
    }

    /**
     * 场景 2：如果发现该操作已经在执行，等待一个一个执行
     */
    public void lock2() {
        System.out.println("尝试获取锁...");
        lock.lock();
        try {
            System.out.println("操作...");
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }

    /**
     * 场景 3：如果发现该操作已经在执行，则尝试等待一段时间，等待超时则不执行
     */
    public void lock3() {
        System.out.println("尝试获取锁...");
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                try {
                    System.out.println("操作...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException ignore) {
            //线程中断抛出
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        ThreadPoolExecutorUtil.execute(5, new Runnable() {
            @Override
            public void run() {
                reentrantLockExample.lock1();
                //reentrantLockExample.lock2();
                //reentrantLockExample.lock3();
            }
        });
    }
}
