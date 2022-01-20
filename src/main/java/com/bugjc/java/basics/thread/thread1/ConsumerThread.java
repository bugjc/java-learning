package com.bugjc.java.basics.thread.thread1;

/**
 * 消费者线程
 * @author aoki
 * @date 2022/1/4
 * **/
public class ConsumerThread extends Thread{
    private final Consumer c;
    public ConsumerThread(Consumer c) {
        this.c = c;
    }
    @Override
    public void run() {
        while (true) {
            c.getValue();
        }
    }
}
