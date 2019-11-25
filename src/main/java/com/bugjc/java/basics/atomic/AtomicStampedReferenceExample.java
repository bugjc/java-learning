package com.bugjc.java.basics.atomic;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用示例
 * @author aoki
 * @date 2019/11/13
 * **/
@Slf4j
class AtomicStampedReferenceExample {

    /**
     * 设置账户初始值小于20,显然这是一个需要被充值的账户
     */
    private static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(10, 0);

    /**
     * 获取余额
     * @return
     */
    static int balance(){
        return money.getReference();
    }

    /**
     * 余额充值
     * @param amt   --充值金额
     */
    static void recharge(int amt){
        //模拟多个线程同时充值
        ThreadPoolExecutorUtil.execute(5 ,() -> {
            //获得当前时间戳
            final int timestamp = money.getStamp();

            while (true) {
                //获取用户余额
                Integer balance = money.getReference();
                if (balance < 20) {
                    //通过 CAS 设置 余额。如果 balance 的值为 old balance 的话，则将其设置为 balance + amt。
                    if (money.compareAndSet(balance, balance + amt, timestamp, timestamp + 1)) {
                        log.info("余额小于20元,充值 {} 元成功,余额:{} 元", amt, money.getReference());
                        break;
                    }
                } else {
                    log.info("余额大于20元,无需充值");
                    break;
                }
            }
        });
    }

    /**
     * 余额消费
     * @param amt   --消费金额
     */
    static void consume(int amt){
        //模拟多个线程同时消费
        ThreadPoolExecutorUtil.execute(10,() -> {

            //获得当前时间戳
            final int timestamp = money.getStamp();

            while (true) {
                //获取用户余额
                Integer balance = money.getReference();
                if (balance >= amt) {
                    if (money.compareAndSet(balance, balance - amt, timestamp, timestamp + 1)) {
                        log.info("当前余额：{},消费 {} 元成功,余额:{} 元", balance, amt, money.getReference());
                        break;
                    }
                } else {
                    log.info("没有足够的金额");
                    break;
                }
            }
        });
    }
}
