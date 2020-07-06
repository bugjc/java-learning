package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * 计数排序
 * @author aoki
 * @date 2019/11/13
 * **/
@Slf4j
class CountingSort {

    /**
     * 计数排序，假设数组中存储的都是非负整数。
     * @param a       --表示数组
     */
     static void sort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }

        // 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        // 申请一个计数数组 c，下标大小 [0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入 c 中
        for (int value : a) {
            c[value]++;
        }

        // 依次累加
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i-1] + c[i];
        }

        // 临时数组 r，存储排序之后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤，有点难理解
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }

        // 将结果 r 数组拷贝到 a 数组
        System.arraycopy(r, 0, a, 0, n);
    }
}
