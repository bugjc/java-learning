package com.bugjc.java.basics.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件等待 Condition
 * @author aoki
 * @date 2022/1/5
 * **/
@Slf4j
public class ConditionExample2 {

    /**
     * 实现生产者与消费者  一对一·交替打印
     * @author aoki
     * @date 2022/1/5
     * **/
    public static class MyService {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private boolean flag = false;

        public void setValue() {
            lock.lock();
            try {
                if (flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("SetValue AAAAAA");
                flag = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void getValue() {
            lock.lock();
            try {
                if (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("GetValue BBBB");
                flag = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadA extends Thread {
        private final MyService service;
        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                service.setValue();
            }
        }
    }

    public static class ThreadB extends Thread {
        private final MyService service;
        public ThreadB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                service.getValue();
            }
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA ta = new ThreadA(service);
        ThreadB tb = new ThreadB(service);
        ta.start();
        tb.start();
    }
}
