package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串相加
 * @author qingyang
 * @date 2018/9/13 15:18
 */
@Slf4j
public class AddStrings {

    public String addStrings(String num1, String num2) {

        //1、补齐0
        StringBuilder aBuilder = new StringBuilder(num1);
        StringBuilder bBuilder = new StringBuilder(num2);
        if (aBuilder.length() > bBuilder.length()){
            int dValue = aBuilder.length() - bBuilder.length();
            while (dValue > 0){
                bBuilder.insert(0, "0");
                dValue--;
            }
        }else if (bBuilder.length() > aBuilder.length()){
            int dValue = bBuilder.length() - aBuilder.length();
            while (dValue > 0){
                aBuilder.insert(0, "0");
                dValue--;
            }
        }

        aBuilder = aBuilder.reverse();
        bBuilder = bBuilder.reverse();
        StringBuilder cBuilder = new StringBuilder();
        int cf = 0;
        for (int i = 0; i < aBuilder.length(); i++) {
            int sum = (aBuilder.charAt(i) - '0') + (bBuilder.charAt(i) - '0');
            if (cf == 1){
                sum += 1;
            }
            if (sum >= 10){
                cf = 1;
                cBuilder.insert(i,sum - 10);
                if (aBuilder.length() == cBuilder.length()){
                    cBuilder.insert(i+1,cf);
                }
            } else {
                cf = 0;
                cBuilder.insert(i,sum);
            }
        }
        return cBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "9999";
        String num2 = "9999";
        String sum = new AddStrings().addStrings(num1,num2);
        log.info(sum);
    }
}
