package com.bugjc.java.basics.thread.thread2;

/**
 * 创建 20 个线程，其中 10 个线程是将数据备份到数据库 A，另外 10 个线程将数据备份到数据库 B 中去，并且
 * 备份数据库 A 和备份数据库 B 是交叉进行的
 * @author aoki
 * @date 2022/1/5
 * **/
public class DBTools {

    volatile private boolean prevIsA = false;

    synchronized public void backA() {
        while (prevIsA) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("AAAAA");
        }
        prevIsA = true;
        notifyAll();
    }

    synchronized public void backB() {
        while (!prevIsA) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("BBBBB");
        }
        prevIsA = false;
        notifyAll();
    }
}
