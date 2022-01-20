package com.bugjc.java.basics.thread.thread1;
/**
 * 生产者-消费者模式测试
 * @author aoki
 * @date 2022/1/4
 * **/
public class Test {
    public static void main(String[] args) {
        String str = new String("");
        Producer p = new Producer(str);
        Consumer c = new Consumer(str);;
        ProducerThread pt = new ProducerThread(p);
        ConsumerThread ct = new ConsumerThread(c);
        pt.start();
        ct.start();
    }
}
