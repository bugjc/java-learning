package com.bugjc.java.lib.bloom.filter;

import bloomfilter.CanGenerateHashFrom;
import bloomfilter.mutable.BloomFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * 布隆过滤器实现，link:https://colobu.com/2016/07/02/bloom-filter-for-scala/
 *
 * @author aoki
 * @date 2020/1/17
 **/
@Slf4j
public class BloomFilterExample {

    private static BloomFilter<byte[]> bf;

    static {
        long expectedElements = 10000000;
        double falsePositiveRate = 0.1;
        bf = BloomFilter.apply(
                expectedElements,
                falsePositiveRate,
                CanGenerateHashFrom.CanGenerateHashFromByteArray$.MODULE$);
    }

    public static void main(String[] args) {
        byte[] element = new byte[2];
        element[0] = 100;
        bf.add(element);
        log.info("将元素 {} 添加到布隆过滤器中。", element);
        log.info("元素 {} 使用布隆过滤器。", element);
        boolean flag = bf.mightContain(element);
        log.info("可能包含元素吗？{}", flag);

        element[1] = 100;
        log.info("元素 {} 使用布隆过滤器。", element);
        flag = bf.mightContain(element);
        log.info("可能包含元素吗？{}", flag);
        bf.dispose();
    }
}
