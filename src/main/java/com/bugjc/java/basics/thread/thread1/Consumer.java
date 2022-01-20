package com.bugjc.java.basics.thread.thread1;

/**
 * 消费者
 * @author aoki
 * @date 2022/1/4
 * **/
public class Consumer {
    private final String lock;
    public Consumer(String lock) {
        this.lock = lock;
    }
    public void getValue() {
        synchronized (lock) {
            if ("".equals(ValueObject.value)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者 get 的值：" + ValueObject.value);
            ValueObject.value = "";
            lock.notify();
        }
    }
}
