package com.bugjc.java.basics.algorithm;

/**
 * 插入排序
 * @author aoki
 * @date 2019/11/13
 * **/
public class InsertionSort {

    /**
     * 插入排序
     * @param a     --表示数组
     */
    static void sort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置,从尾到头查找插入文职
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    // 数据移动
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            // 插入数据
            a[j+1] = value;
        }
    }
}
