package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件等待 Condition
 * @author aoki
 * @date 2022/1/5
 * **/
@Slf4j
public class ConditionExample {
    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final Object[] items = new Object[2];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            //已满等待
            while (count == items.length) {
                System.out.println("等待线程消费数据");
                notFull.await();
            }
            System.out.println(Thread.currentThread().getName() +" - 生产数据：" + x + ",通知消费线程数据已准备");
            //放入数据唤醒不为空条件等待事件
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            //为空等待
            while (count == 0) {
                System.out.println("等待线程生产数据");
                notEmpty.await();
            }

            //取出数据通知未满条件等待事件
            Object x = items[takeptr];
            System.out.println(Thread.currentThread().getName() +" - 消费数据：" + x + ",通知生产线程数据已消费");
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample conditionExample = new ConditionExample();
        ThreadPoolExecutorUtil.execute(5, () -> {
            for (int i = 0; i < 2; i++) {
                try {
                    conditionExample.put(i);
                } catch (InterruptedException ignore) {
                }
            }
        });

        ThreadPoolExecutorUtil.execute(1, () -> {
            try {
                for (;;){
                    conditionExample.take();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignore) {
            }
        });
    }
}
