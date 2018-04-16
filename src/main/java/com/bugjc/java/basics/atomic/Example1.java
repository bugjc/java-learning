package com.bugjc.java.basics.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Example1 {

    private final AtomicLong sequenceNumber = new AtomicLong(0);
    public long next() {
        //原子增量方法,执行的是i++，所以需要在获取一次。
        sequenceNumber.getAndIncrement();
        return sequenceNumber.get();
    }

    public void radixNext(int radix){
        for (;;) {
            long i = sequenceNumber.get();
            // 该方法不一定执行成功，所以用无限循环来保证操作始终会执行成功一次。
            boolean suc = sequenceNumber.compareAndSet(i, i + radix);
            if (suc) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        Example1 sequencer = new Example1();

        //生成序列号
        for (int i = 0; i < 5; i++) {
            System.out.println(sequencer.next());
        }

        //生成自定义序列号
        for (int i = 0; i < 5; i++) {
            sequencer.radixNext(3);
            System.out.println(sequencer.sequenceNumber.get());
        }

    }

}
