package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件等待 Condition
 * @author aoki
 * @date 2022/1/5
 * **/
public class ConditionExample1 {
    public static class MyService {
        private final ReentrantLock lock = new ReentrantLock();
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

    public static class ThreadA extends Thread {
        private final MyService service;
        public ThreadA(MyService service) {
            this.service = service;
        }
        @Override
        public void run() {
            service.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA ta = new ThreadA(service);
        ta.start();
        Thread.sleep(2000);
        service.signal();
    }
}
