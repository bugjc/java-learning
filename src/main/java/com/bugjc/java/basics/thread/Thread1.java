package com.bugjc.java.basics.thread;

public class Thread1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("1111"));
        Thread thread2 = new Thread(() -> System.out.println("2222"));
        Thread thread3 = new Thread(() -> System.out.println("3333"));
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
