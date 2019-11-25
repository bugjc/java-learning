package com.bugjc.java.basics.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 实现一个简单的本地自增序列
 * @author aoki
 * @date 2019/11/13
 * **/
class AtomicSequenceExample {


    /**
     * 初始化序列对象
     */
    private static AtomicLong sequenceNumber = new AtomicLong(0);

    /**
     * 序列+1并返回新值
     * @return
     */
    public static long next() {
        //原子增量方法,执行的是i++，所以需要在获取一次。
        //getAndIncrement();方法返回加1前的旧值
        //incrementAndGet;方法返回加1的新值
        return sequenceNumber.incrementAndGet();
    }

    /**
     * 自定义序列步长并返回新值
     * @param radix     --步长
     * @return
     */
    public static long radixNext(int radix){
        for (;;) {
            long i = sequenceNumber.get();
            // 该方法不一定执行成功，所以用无限循环来保证操作始终会执行成功一次。
            boolean suc = sequenceNumber.compareAndSet(i, i + radix);
            if (suc) {
                return sequenceNumber.get();
            }
        }
    }
}
