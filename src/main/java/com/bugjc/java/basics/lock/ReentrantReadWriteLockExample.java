package com.bugjc.java.basics.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author aoki
 * @date 2022/1/6
 **/
public class ReentrantReadWriteLockExample {

    public static class MyService {
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " Read AAA  " + System.currentTimeMillis());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readLock().unlock();
        }

        public void write() {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " write BBB " + System.currentTimeMillis());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.writeLock().unlock();
        }
    }

}
