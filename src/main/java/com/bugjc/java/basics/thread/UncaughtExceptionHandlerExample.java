package com.bugjc.java.basics.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * UncaughtExceptionHandler 示例
 * @author aoki
 * @date 2020/1/15
 * **/
public class UncaughtExceptionHandlerExample {

    /**
     * 在虚拟机中，当一个线程如果没有显式处理（即 try catch）异常而抛出时会将该异常事件报告给该线程对象的 java.lang.Thread.UncaughtExceptionHandler 进行处理，
     * 如果线程没有设置 UncaughtExceptionHandler，则默认会把异常栈信息输出到终端而使程序直接崩溃。所以如果我们想在线程意外崩溃时做一些处理就可以通过实现 UncaughtExceptionHandler 来满足需求。
     */
    @Slf4j
    public static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.info("线程信息：{}", t.toString());
            log.info("异常信息：{}",e.getMessage());
        }
    }

    public static void main(String[] args) {
        Thread customThread = new Thread(()->{
           int i = 10 / 0;
        });
        customThread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        customThread.start();
    }
}
