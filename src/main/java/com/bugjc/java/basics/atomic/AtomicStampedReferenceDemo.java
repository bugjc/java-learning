package com.bugjc.java.basics.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        //模拟多个线程同时更新后台数据库,为用户充值
        for (int i = 0; i < 3; i++) {
            //获得当前时间戳
            final int timestamp = money.getStamp();
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            //获得当前对象引用
                            Integer m = money.getReference();
                            if (m < 20) {
                                //比较设置参数依次为:期望值写入新值期望时间戳新时间戳
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                                    log.info("余额小于20元,充值成功,余额:" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                //log.info("余额大于20元,无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        //用户消费线程,模拟消费行为
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            log.info("大于10元");
                            if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                                log.info("成功消费10元,余额:" + money.getReference());
                                break;
                            }
                        } else {
                            log.info("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }
}
