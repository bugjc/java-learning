package com.bugjc.java.basics.atomic;


import java.util.concurrent.atomic.AtomicIntegerArray;

public class Example2 {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{
        public void run(){
            for(int k=0;k<10000;k++){
                // 以原子方式将索引 i 的元素加 1。
                arr.getAndIncrement(k % arr.length());
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[]ts = new Thread[10];

        //创建10个现场
        for(int k=0;k<10;k++){
            ts[k] = new Thread(new AddThread());
        }

        //开启10个线程
        for(int k=0;k<10;k++){
            ts[k].start();
        }

        //同步执行
        for(int k=0;k<10;k++){
            ts[k].join();
        }

        //打印最终执行结果
        System.out.println(arr);
    }
}
