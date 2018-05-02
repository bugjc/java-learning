package com.bugjc.java.basics.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 乐观锁测试
 * https://leanote.com/api/file/getImage?fileId=5a7824faab64415d1800175f
 * 执行线程2的时候取走的是i==1，算完回来要更新的时候，发现i==2了，那刚才算的不作数，从新取值再算一遍。
 * 这种“比较并交换（Compare-and-Swap,CAS）”的指令是原子的，“现场检查现场更新”，不会给其他线程以可乘之机。
 */
public class MyAtomicInteger extends AtomicInteger {
    private AtomicLong failureCount = new AtomicLong(0);

    public long getFailureCount() {
        return failureCount.get();
    }

    /**
     * 从以下两个方法 inc 和 dec 可以看出 Atomic* 的原子性的实现原理：
     * 这是一种乐观锁，每次修改值都会【先比较再赋值】，这个操作在CPU层面是原子的，从而保证了其原子性。
     * 如果比较发现值已经被其他线程变了，那么就返回 false，然后重新尝试。
     */
    public void inc() {
        Integer value;
        do {
            value = get();
            failureCount.getAndIncrement();
            //try {
            //    TimeUnit.MILLISECONDS.sleep(2);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        } while (!compareAndSet(value, value + 1));
    }

    public void dec() {
        Integer value;
        do {
            value = get();
            failureCount.getAndIncrement();
            //try {
            //    TimeUnit.MILLISECONDS.sleep(2);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        } while (!compareAndSet(value, value - 1));
    }


    public static void main(String[] args) throws InterruptedException {
        final MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        // 执行自增和自减操作的线程各10个，每个线程操作10000次
        Thread[] incs = new Thread[10];
        Thread[] decs = new Thread[10];
        for (int i = 0; i < incs.length; i++) {
            incs[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myAtomicInteger.inc();
                }
            });
            incs[i].start();
            decs[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myAtomicInteger.dec();
                }
            });
            decs[i].start();
        }

        for (int i = 0; i < 10; i++) {
            incs[i].join();
            decs[i].join();
        }

        System.out.println(myAtomicInteger.get() + " with " + myAtomicInteger.getFailureCount() + " failed tries.");
    }

}

