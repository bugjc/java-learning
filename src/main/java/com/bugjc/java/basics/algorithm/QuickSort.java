package com.bugjc.java.basics.algorithm;

/**
 * 快速排序
 * @author aoki
 * @date 2019/11/13
 * **/
public class QuickSort {

  /**
   * 快速排序
   * @param a     --表示数组
   */
  public static void sort(int[] a) {
    quickSortInternally(a, 0, a.length - 1);
  }

  /**
   * 快速排序递归函数，p,r为下标
   * @param a
   * @param p
   * @param r
   */
  private static void quickSortInternally(int[] a, int p, int r) {
    if (p >= r) {
      return;
    }
    // 获取分区点
    int q = partition(a, p, r);
    quickSortInternally(a, p, q-1);
    quickSortInternally(a, q+1, r);
  }

  private static int partition(int[] a, int p, int r) {
    int pivot = a[r];
    int i = p;
    for(int j = p; j < r; ++j) {
      if (a[j] < pivot) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        ++i;
      }
    }

    int tmp = a[i];
    a[i] = a[r];
    a[r] = tmp;
    return i;
  }
}
