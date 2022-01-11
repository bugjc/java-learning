package com.bugjc.java.basics.thread.thread2;

/**
 * 交叉备份
 * @author aoki
 * @date 2022/1/5
 * **/
public class Test {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            ThreadB tb = new ThreadB(dbTools);
            tb.start();
            ThreadA ta = new ThreadA(dbTools);
            ta.start();
        }
    }
}
