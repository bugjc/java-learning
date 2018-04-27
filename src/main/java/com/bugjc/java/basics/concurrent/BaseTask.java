package com.bugjc.java.basics.concurrent;

public class BaseTask implements Runnable{

    @Override
    public void run() {
        //模拟前置任务执行
        System.out.println("执行前置任务");
        System.out.println("初始化订单");
        System.out.println("初始化成功");
    }

}
