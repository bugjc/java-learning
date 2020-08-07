package com.bugjc.java.libs.bloom.filter;

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
public class BloomFilterMain {


    private static BloomFilter<String> bf;

    static {
        long expectedElements = 10000000;
        double falsePositiveRate = 0.1;
        bf = BloomFilter.apply(
                expectedElements,
                falsePositiveRate,
                CanGenerateHashFrom.CanGenerateHashFromString$.MODULE$);
    }

    /**
     * 添加元素
     * @param element
     */
    public static void addElement(String element){
        bf.add(element);
    }

    /**
     * 判断元素是否存在
     *
     * @param element
     * @return
     */
    public static boolean mightContain(String element) {
        return bf.mightContain(element);
    }

}
