package com.bugjc.java.basics.concurrent;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class OrderTask implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public OrderTask(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            //等待所有任务
            cyclicBarrier.await();

            //模拟正常执行的任务
            int random = RandomUtil.randomInt(10,5000);
            System.out.println("任务编号："+random);
            Thread.sleep(random);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
