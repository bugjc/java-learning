package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TicketLock（排队自旋锁）
 * @author aoki
 * @date 2022/1/7
 * **/
public class TicketSpinLock {

    /**
     * 注册排队号
     */
    private final AtomicInteger register = new AtomicInteger(){{
        set(0);
    }};

    /**
     * 通知排队号
     */
    private final AtomicInteger notice = new AtomicInteger(){{
        set(0);
    }};

    /**
     * 获取排队号
     * @return
     */
    public int getNum(){
        return register.getAndIncrement();
    }

    /**
     * 尝试加锁
     * @return
     */
    public boolean tryLock(int num){
        return num == notice.get();
    }

    /**
     * 释放锁：从排队缓冲池中取
     */
    public void unLock(){
        notice.incrementAndGet();
    }

    public static void main(String[] args) {
        TicketSpinLock ticketSpinLock = new TicketSpinLock();
        ThreadPoolExecutorUtil.execute(5,new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                //获取票据
                int num = ticketSpinLock.getNum();
                // 循环检测尝试获取锁
                while (!ticketSpinLock.tryLock(num)){
                    //睡眠 10 ms 继续尝试获取锁
                    Thread.sleep(10);
                }

                try {
                    System.out.println("已获取到锁");
                    System.out.println("执行业务");
                    Thread.sleep(1000);
                }finally {
                    System.out.println("释放锁");
                    ticketSpinLock.unLock();
                }
            }
        });
    }

}
