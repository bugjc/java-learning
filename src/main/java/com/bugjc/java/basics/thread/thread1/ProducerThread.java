package com.bugjc.java.basics.thread.thread1;

/**
 * 生产者线程
 * @author aoki
 * @date 2022/1/4
 * **/
public class ProducerThread extends Thread{
    private final Producer p;
    public ProducerThread(Producer p) {
        this.p = p;
    }
    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}
