package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 AQS 的自定义互斥锁
 * @author aoki
 * @date 2022/1/6
 * **/
public class MyMutexLockExample {

    private final MyMutexLock lock = new MyMutexLock();

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
            if (lock.tryLock(4, TimeUnit.SECONDS)){
                try {
                    System.out.println("操作...");
                    Thread.sleep(1100);
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

    public static class MyService {
        private final MyMutexLock lock = new MyMutexLock();
        private final Condition condition = lock.newCondition();

        public void await() {
            lock.lock();
            try {
                System.out.println("await A");
                //使当前执行的线程处于等待状态 waiting
                condition.await();
                System.out.println("await B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("释放锁");
            }
        }

        public void signal() {
            lock.lock();
            try {
                System.out.println("signal A");
                condition.signal();
                System.out.println("signal B");
            }finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyMutexLockExample myMutexLockExample = new MyMutexLockExample();
        MyService myService = new MyService();
        ThreadPoolExecutorUtil.execute(1, new Runnable() {
            @Override
            public void run() {
                //myMutexLockExample.lock1();
                //myMutexLockExample.lock2();
                //myMutexLockExample.lock3();
                myService.await();
            }
        });
        Thread.sleep(2000);
        myService.signal();
    }
}
