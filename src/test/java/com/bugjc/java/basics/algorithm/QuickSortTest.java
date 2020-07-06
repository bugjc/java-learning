package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;
import BeforeEach;
import Test;

@Slf4j
class QuickSortTest {

    /**
     * 测试数据
     */
    private int[] srcArray = {1,6,3,2,8,4,6,7,9,1};

    @BeforeEach
    void setUp() {
        log.info("快速排序 测试数组数据：{}", srcArray);
    }

    @Test
    void sort() {
        QuickSort.sort(srcArray);
        log.info("使用 快速排序 对数组排序结果：{}", srcArray);
    }
}