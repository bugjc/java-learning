package com.bugjc.java.basics.iterative;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 迭代法
 * 问题:古印度国王舍罕酷爱下棋，他打算重赏国际象棋的发明人宰相西萨·班·达依尔。
 * 这位聪明的大臣指着象棋盘对国王说：“陛下，我不要别的赏赐，请您在这张棋
 * 盘的第一个小格内放入一粒麦子，在第二个小格内放入两粒，第三小格内放入给
 * 四粒，以此类推，每一小格内都比前一小格加一倍的麦子，直至放满 64 个格子
 * ，然后将棋盘上所有的麦粒都赏给您的仆人我吧！
 * @author qingyang
 * @date 2018/12/16 16:48
 */
@Slf4j
public class IterativeMethod {

    /**
     * 公式: f(Xn) = f(Xn-1) * 2;f(1) = 1
     * @Description: 算算舍罕王给了多少粒麦子
     * @param grid- 放到第几格
     * @return long- 麦粒的总数
     */

    public static long getNumberOfWheat(int grid) {

        long sum = 0;      // 麦粒总数
        long numberOfWheatInGrid = 0;  // 当前格子里麦粒的数量

        numberOfWheatInGrid = 1;  // 第一个格子里麦粒的数量
        sum += numberOfWheatInGrid;

        for (int i = 2; i <= grid; i ++) {
            numberOfWheatInGrid *= 2;   // 当前格子里麦粒的数量是前一格的 2 倍
            sum += numberOfWheatInGrid;   // 累计麦粒总数
        }

        return sum;

    }

    /**
     * @Description: 计算大于 1 的正整数之平方根
     * @param n- 待求的数, deltaThreshold- 误差的阈值, maxTry- 二分查找的最大次数
     * @return double- 平方根的解
     */
    public static double getSqureRoot(int n, double deltaThreshold, int maxTry) {

        if (n <= 1) {
            return -1.0;
        }

        double min = 1.0, max = (double)n;
        for (int i = 0; i < maxTry; i++) {
            double middle = (min + max) / 2;
            double square = middle * middle;
            //计算相对误差
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold) {
                return middle;
            } else {
                if (square > n) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
        }

        return -2.0;

    }

    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary- 排序后的字典, wordToFind- 待查的单词
     * @return boolean- 是否发现待查的单词
     */
    public static boolean search(String[] dictionary, String wordToFind) {

        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            //使用left + (right - left) 而不是 (right + left) 是为了解决溢出,当出现left 和 right 相近十,使用(right + left) 会溢出.
            int middle = left + (right - left) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return false;

    }






    public static void main(String[] args) {
        //1. 计算米粒
        long total = IterativeMethod.getNumberOfWheat(63);
        log.info("麦粒总数:{}",total);


        //2. 计算给定数值的平方根
        int number = 25;
        double squareRoot = IterativeMethod.getSqureRoot(number, 0.000001, 10000);
        if (squareRoot == -1.0) {
            log.error(" 请输入大于 1 的整数 ");
        } else if (squareRoot == -2.0) {
            log.info(" 未能找到解 ");
        } else {
            log.info(String.format("%d 的平方根是 %f", number, squareRoot));
        }


        //3. 查找单词
        String[] dictionary = {"i", "am", "one", "of", "the", "authors", "in", "geekbang"};
        Arrays.sort(dictionary);
        String wordToFind = "i";
        boolean found = IterativeMethod.search(dictionary, wordToFind);
        if (found) {
            log.info(String.format(" 找到了单词 %s", wordToFind));
        } else {
            log.info(String.format(" 未能找到单词 %s", wordToFind));
        }


    }

}
