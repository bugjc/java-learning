package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;
import BeforeEach;
import Test;

@Slf4j
class InsertionSortTest {

    /**
     * 测试数据
     */
    private int[] srcArray = {1,6,3,2,8,4,6,7,9,1};

    @BeforeEach
    void setUp() {
        log.info("插入排序 测试数组数据：{}", srcArray);
    }

    @Test
    void sort() {
        InsertionSort.sort(srcArray);
        log.info("使用 插入排序 对数组排序结果：{}", srcArray);
    }
}