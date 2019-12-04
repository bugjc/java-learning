package com.bugjc.java.basics.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAdder、LongAccumulator示例 http://moguhu.com/article/detail?articleId=45
 * @author aoki
 * @date 2019/11/14
 * **/
@Slf4j
public class LongAdderExample {


    /**
     * 初始化 LongAccumulator,内部多个变量a1,a2,a3......
     */
    private static LongAccumulator longAccumulator = new LongAccumulator(
            //提供一个函数（非原子操作）
            Long::sum,
            //初始值
            100
    );

    public static long get(){
        return longAccumulator.get();
    }

    public static void accumulate(){
        //每次调用，其中一个变量an->(an,10);
        longAccumulator.accumulate(10);
    }
}
