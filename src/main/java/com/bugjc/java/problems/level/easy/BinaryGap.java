package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 二进制间距
 * @author qingyang
 * @date 2018/9/19 15:17
 */
@Slf4j
public class BinaryGap {

    public int binaryGap(int N) {
        int[] a = new int[32];
        int t = 0;
        for (int i = 0; i < 32; ++i) {
            //移动i位并位于最后一位比较，为1则最后一位坐标值为1
            if (((N >> i) & 1) != 0) {
                //记录下标
                a[t++] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < t - 1; ++i) {
            //找出相邻最大的差值，即为最近两个1之间的间距
            ans = Math.max(ans, a[i + 1] - a[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 22;
        int count = new BinaryGap().binaryGap(n);
        log.info("count:{}",count);
    }


}
