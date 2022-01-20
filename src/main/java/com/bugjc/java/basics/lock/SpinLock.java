package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 自旋锁
 * @author aoki
 * @date 2022/1/7
 * **/
public class SpinLock {

    private final AtomicBoolean available = new AtomicBoolean(false);

    /**
     * 加锁
     * @return
     */
    public boolean tryLock(){
        // 尝试获取锁，成功返回true，失败返回false
        return available.compareAndSet(false,true);
    }

    /**
     * 释放锁
     */
    public void unLock(){
        if(!available.compareAndSet(true,false)){
            throw new RuntimeException("释放锁失败");
        }
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        ThreadPoolExecutorUtil.execute(2,new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 循环检测尝试获取锁
                while (!spinLock.tryLock()){
                    //睡眠 10 ms 继续尝试获取锁
                    Thread.sleep(10);
                }

                try {
                    System.out.println("已获取到锁");
                    System.out.println("执行业务");
                    Thread.sleep(1000);
                }finally {
                    System.out.println("释放锁");
                    spinLock.unLock();
                }
            }
        });
    }
}
