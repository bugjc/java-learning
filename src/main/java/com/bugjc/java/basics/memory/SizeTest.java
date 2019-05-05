package com.bugjc.java.basics.memory;

import cn.hutool.core.util.RandomUtil;
import org.apache.lucene.util.RamUsageEstimator;


public class SizeTest {
  public static void main(String[] args) {
      StringBuilder obj = new StringBuilder();
      for(int i = 0; i < 20000000; i++) {
          obj.append(RandomUtil.randomString(20));
      }

      //计算指定对象及其引用树上的所有对象的综合大小，单位字节
      long size = RamUsageEstimator.sizeOf(obj);
      System.out.println(size);

      //计算指定对象本身在堆空间的大小，单位字节
      size = RamUsageEstimator.shallowSizeOf(obj.toString());
      System.out.println(size);

      //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
      String sizeStr = RamUsageEstimator.humanSizeOf(obj.toString());
      System.out.println(sizeStr);
  }
}
