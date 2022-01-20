package com.bugjc.java.basics.thread.thread2;

/**
 * 备份 B 线程
 * @author aoki
 * @date 2022/1/5
 * **/
public class ThreadB extends Thread{
    private final DBTools dbTools;
    public ThreadB(DBTools dbTools) {
        this.dbTools = dbTools;
    }
    @Override
    public void run() {
        dbTools.backB();
    }
}
