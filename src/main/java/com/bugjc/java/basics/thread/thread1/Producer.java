package com.bugjc.java.basics.thread.thread1;

/**
 * 生产者
 * @author aoki
 * @date 2022/1/4
 * **/
public class Producer {
    private final String lock;
    public Producer(String lock) {
        this.lock = lock;
    }
    public void setValue() {
        synchronized (lock) {
            if (!"".equals(ValueObject.value)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = System.currentTimeMillis() + "_" + System.nanoTime();
            System.out.println("生产者 set 的值是：" + value);
            ValueObject.value = value;
            lock.notify();
        }
    }
}
