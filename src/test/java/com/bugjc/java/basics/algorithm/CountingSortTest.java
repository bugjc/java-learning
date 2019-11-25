package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CountingSortTest {

    /**
     * 测试数据
     */
    private int[] srcArray = {1,6,3,2,8,4,6,7,9,1};

    @BeforeEach
    void setUp() {
        log.info("计数排序 测试数组数据：{}", srcArray);
    }

    @Test
    void sort() {
        CountingSort.sort(srcArray);
        log.info("使用 计数排序 对数组排序结果：{}", srcArray);
    }
}