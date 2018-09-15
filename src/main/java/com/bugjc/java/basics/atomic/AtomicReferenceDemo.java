package com.bugjc.java.basics.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceDemo {
    // 设置账户初始值小于20,显然这是一个需要被充值的账户
    static AtomicReference<Integer> money = new AtomicReference<Integer>(19);

    public static void main(String args[]) {
        //模拟多个线程同时更新后台数据库,为用户充值
        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (;;){
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    log.info("余额小于20元,充值成功,余额:" + money.get() + "元");
                                    break;
                                }
                            } else {
                                //System.out.println("余额大于20元,无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        //有一个线程一直在消费
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            log.info("大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                log.info("成功消费10元,余额:" + money.get());
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
