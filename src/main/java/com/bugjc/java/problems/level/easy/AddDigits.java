package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 各位相加
 * @author qingyang
 * @date 2018/9/13 14:38
 */
@Slf4j
public class AddDigits {


    public int addDigits(int num) {
        StringBuilder numBuilder = new StringBuilder(num+"");
        int sum = 0;
        for (int i = 0; i < numBuilder.length(); i++) {
            sum += (numBuilder.charAt(i) - '0');
        }

        if (new StringBuilder(num+"").length() > 1){
            return this.addDigits(sum);
        }

        return sum;
    }

    public static void main(String[] args) {
        int num = 1234;
        int sum = new AddDigits().addDigits(num);
        log.info(sum + "");
    }
}
