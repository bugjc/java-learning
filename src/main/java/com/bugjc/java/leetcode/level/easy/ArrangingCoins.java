package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 排列硬币
 * @author qingyang
 * @date 2018/9/13 16:17
 */
@Slf4j
public class ArrangingCoins {

    public int arrangeCoins(int n) {
        int line = 0;
        for (int i = 1; i <= n; i++) {
            line = i;
            n = n - line;
        }
        return line;
    }

    public static void main(String[] args) {
        int n = 15;
        int line = new ArrangingCoins().arrangeCoins(n);
        log.info(line+"");
    }
}
