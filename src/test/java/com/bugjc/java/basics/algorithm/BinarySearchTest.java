package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;
import BeforeEach;
import Test;

@Slf4j
class BinarySearchTest {

    /**
     * 测试数据
     */
    private int[] srcArray = {1,6,3,2,8,4,6,7,9,1};

    @BeforeEach
    void setUp() {
        log.info("二分查找 测试数组数据：{}", srcArray);
    }

    @Test
    void recursiveBinSearch() {
        log.info("使用 二分查找递归实现方式 从数组中搜索 key=4 的索引位置：{}", BinarySearch.binSearch(srcArray, 0, srcArray.length - 1, 4));
    }

    @Test
    void loopBinSearch() {
        log.info("使用 二分查找普通循环实现方式 从数组中搜索 key=5 的索引位置：{}", BinarySearch.binSearch(srcArray,  6));
    }
}