package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 七进制数
 * @author qingyang
 * @date 2018/9/17 17:05
 */
@Slf4j
public class Base7 {


    public String convertToBase7(int num) {
        //1:正整数、0:0、-1:负整数
        int type = 0;
        if (num < 0){
            type = -1;
            num = Math.abs(num);
        }else if (num > 0){
            type = 1;
        }else {
            return String.valueOf(type);
        }
        int value = num;
        StringBuilder result = new StringBuilder();
        while (value != 0){
            //取余数
            num = num % 7;
            result.append(num);
            //获取底数
            value = value / 7;
            num = value;
        }
        if (type == -1){
            result.append("-");
        }
        return result.reverse().toString();
    }

    /**
     * 就是除以7取馀数.
     * 例如
     * 100 = 14*7 +2 个位
     * 14 = 2*7 +0 百位
     * 2 = 0*7 + 2 千位
     * (2753) | 10 = ( 11012) | 7
     * @param args
     */
    public static void main(String[] args) {
        String result = new Base7().convertToBase7(-7);
        log.info(result);
    }

}
