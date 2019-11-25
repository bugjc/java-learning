package com.bugjc.java.basics.algorithm;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序
 * @author aoki
 * @date 2019/11/13
 * **/
@Slf4j
public class MergeSort {

  /**
   * 归并排序算法
   * @param a     --表示数组
   */
  static void sort(int[] a) {
    int n = a.length;
    mergeSortInternally(a, 0, n - 1);
  }

  /**
   * 递归调用函数
   * @param a     --表示数组
   * @param p
   * @param r
   */
  private static void mergeSortInternally(int[] a, int p, int r) {
    // 递归终止条件
    if (p >= r) {
      return;
    }
    log.info("p={},r={}", p, r);
    // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
    int q = p + (r - p) / 2;
    log.info("q={},r={}", p+1, r);
    // 分治递归
    mergeSortInternally(a, p, q);
    mergeSortInternally(a, q+1, r);

    // 将A[p...q]和A[q+1...r]合并为A[p...r]
    merge(a, p, q, r);
  }

  private static void merge(int[] a, int p, int q, int r) {
    log.info("合并，p={},q={},r={}",p,q,r);
    // 初始化变量i, j, k
    int i = p;
    int j = q + 1;
    int k = 0;
    // 申请一个大小跟a[p...r]一样的临时数组
    int[] tmp = new int[r - p + 1];
    while (i <= q && j <= r) {
      if (a[i] <= a[j]) {
        // i++等于i:=i+1
        tmp[k++] = a[i++];
      } else {
        tmp[k++] = a[j++];
      }
      log.info("tmp:{}",tmp);
    }

    // 判断哪个子数组中有剩余的数据
    int start = i;
    int end = q;
    if (j <= r) {
      start = j;
      end = r;
    }

    // 将剩余的数据拷贝到临时数组tmp
    while (start <= end) {
      tmp[k++] = a[start++];
    }

    // 将tmp中的数组拷贝回a[p...r]
    for (i = 0; i <= r-p; ++i) {
      a[p+i] = tmp[i];
    }
    log.info("合并后的值：{}",JSON.toJSONString(a));
  }
}
