package com.bugjc.java.basics.lock;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义 AQS 实现互斥锁
 * AQS: 用来构建锁或者其他同步组件的框架。它使用了一个 int 变量表示同步状态(更改状态使用 CAS 方式),通过内置的 FIFO 队列来完成资源获取线程的排队工作。
 * 通过AQS实现自定义的同步组件一般步骤：
 * 1.继承 AQS，然后根据实现同步的模式，覆写对应的方法。这些方法可以是独占的方式、共享的方式
 * 2.实现 AQS 的类需要调用 compareAndSetState(), getState(), setState() 来进行状态的控制
 * 3.外部的同步组件类，实现 Lock 接口，然后通过 AQS 对应的模板方法区实现 lock 方法。
 * @author aoki
 * @date 2022/1/6
 * **/
public class MyMutexLock implements Lock {

    private static final int LOCK = 1;
    private static final int UNLOCK = 0;

    /**
     * 这是内部类，内部类继承ASQ，重写ASQ中获取同步状态的几个方法(因为是实现排它锁，重写阻塞模式的方法即可)
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * state 表示获取到锁 state=1 获取到了锁，state=0，表示这个锁当前没有线程拿到
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == LOCK;
        }

        /**
         * 尝试获取锁，将状态从 0 修改为 1，修改成功则将自身线程设置为当前拥有锁的线程
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(UNLOCK, LOCK)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁，将状态修改为 0，因为只有一个线程持有锁，因此不需要 CAS，是线程安全的
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == UNLOCK) {
                throw new UnsupportedOperationException();
            }
            setExclusiveOwnerThread(null);
            setState(UNLOCK);
            return true;
        }


        //共享模式
//        @Override
//        protected int tryAcquireShared(int arg) {
//            return super.tryAcquireShared(arg);
//        }
//
//        @Override
//        protected boolean tryReleaseShared(int arg) {
//            return super.tryReleaseShared(arg);
//        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    private static final Sync SYNC = new Sync();

    @Override
    public void lock() {
        //加锁
        SYNC.acquire(LOCK);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //加锁,可响应中断
        SYNC.acquireInterruptibly(LOCK);
    }

    @Override
    public boolean tryLock() {
        //尝试加锁
        return SYNC.tryAcquire(LOCK);
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        //加锁，可超时
        return SYNC.tryAcquireNanos(LOCK, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        //解锁
        SYNC.release(LOCK);
    }

    @NotNull
    @Override
    public Condition newCondition() {
        //获取Condition对象
        return SYNC.newCondition();
    }
}
