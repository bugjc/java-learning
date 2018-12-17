package com.bugjc.java.algorithm;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.bugjc.java.basics.algorithm.BubbleSort;
import com.bugjc.java.basics.algorithm.InsertionSort;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BubbleSoleTest {

    /**
     * 测试冒泡排序
     */
    @Test
    public void testBubbleSole(){
        long start = System.currentTimeMillis();
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = RandomUtil.randomInt(0,1000000);
        }
        new BubbleSort().bubbleSort(arr,arr.length);
        log.info("排序后的值：{}", JSON.toJSONString(arr));
        log.info("耗时：{}ms",System.currentTimeMillis() - start);
    }

    /**
     * 测试插入排序
     */
    @Test
    public void testInsertionSort(){
        long start = System.currentTimeMillis();
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = RandomUtil.randomInt(0,1000000);
        }
        new InsertionSort().insertionSort(arr,arr.length);
        log.info("排序后的值：{}", JSON.toJSONString(arr));
        log.info("耗时：{}ms",System.currentTimeMillis() - start);
    }
}
