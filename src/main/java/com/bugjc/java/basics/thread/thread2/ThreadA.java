package com.bugjc.java.basics.thread.thread2;

/**
 * 备份 A 线程
 * @author aoki
 * @date 2022/1/5
 * **/
public class ThreadA extends Thread{
    private final DBTools dbTools;
    public ThreadA(DBTools dbTools) {
        this.dbTools = dbTools;
    }
    @Override
    public void run() {
        dbTools.backA();
    }
}
