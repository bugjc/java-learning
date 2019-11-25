package com.bugjc.java.basics.atomic;


import com.bugjc.java.basics.ThreadPoolExecutorUtil;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray 示例
 * @author aoki
 * @date 2019/11/13
 * **/
public class AtomicIntegerArrayExample {

    /**
     * 初始化长度为 10 的原子数组
     */
    private static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    /**
     * 指定数组索引位置进行原子加1
     * @param index
     */
    public static void increment(int index){
        //模拟多个线程同时对指定索引位置的数值加1操作
        ThreadPoolExecutorUtil.execute(1000, () -> {
            // 以原子方式将索引 i 的元素加 1。
            arr.getAndIncrement(index);
        });
    }

    /**
     * 获取指定索引位置的数值
     * @param index
     * @return
     */
    public static int get(int index){
        return arr.get(index);
    }
}
