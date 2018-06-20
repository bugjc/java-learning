package com.bugjc.java.basics.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
public class LongAdderExample {

    @Test
    private void longAdderTest(){
        LongAdder longAdder= new LongAdder();
        longAdder.increment();//+1
        longAdder.decrement();//-1
        longAdder.add(10L);//增加一个值（非原子操作）
        System.out.println(longAdder.intValue());
        //内部多个变量a1,a2,a3......
        LongAccumulator longAccumulator = new LongAccumulator(
                //提供一个函数（非原子操作）
                (a,v)->{return a+v;},
                //初始值
                0
        );
        //每次调用，其中一个变量an->(an,10);
        longAccumulator.accumulate(10);
        //get方法会统计所有的变量，输出a1 op a2 op a3 op......
        log.info(longAccumulator.get()+"");
    }
}
