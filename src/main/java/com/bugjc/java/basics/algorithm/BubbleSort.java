package com.bugjc.java.basics.algorithm;

/**
 * 冒泡排序
 * @author aoki
 * @date 2019/11/13
 * **/
class BubbleSort {

    /**
     * 冒泡排序
     * @param a     --表示数组
     */
    static void sort(int[] a) {
        //获取数组大小
        int n = a.length;
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) {
                    // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    // 表示有数据交换
                    flag = true;
                }
            }
            if (!flag) {
                // 没有数据交换，提前退出
                break;
            }
        }
    }

}
