package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
class IterativeMethodTest {

    @Test
    void test() {
        //1. 计算米粒
        long total = IterativeMethod.getNumberOfWheat(63);
        log.info("麦粒总数:{}",total);


        //2. 计算给定数值的平方根
        int number = 25;
        double squareRoot = IterativeMethod.getSqureRoot(number, 0.000001, 10000);
        BigDecimal x = new BigDecimal(squareRoot);
        BigDecimal y = new BigDecimal(-1.0);
        BigDecimal z = new BigDecimal(-2.0);
        if (x.compareTo(y) == 0) {
            log.error(" 请输入大于 1 的整数 ");
        } else if (x.compareTo(z) == 0) {
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