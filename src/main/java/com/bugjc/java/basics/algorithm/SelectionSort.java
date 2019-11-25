package com.bugjc.java.basics.algorithm;

/**
 * 选择排序
 */
public class SelectionSort {

    /**
     * 选择排序
     * @param a         --表示数组
     */
    public static void sort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n - 1; ++i) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

}
