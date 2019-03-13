package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 二进制求和
 * @author qingyang
 * @date 2018/9/12 23:05
 */
@Slf4j
public class AddBinary {

    public String addBinary(String a, String b) {

        //1、补齐0
        StringBuilder aBuilder = new StringBuilder(a);
        StringBuilder bBuilder = new StringBuilder(b);
        StringBuilder cBuilder = new StringBuilder();
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

        //2、从最后一位开始计算，逢2进1
        char[] aChars = aBuilder.reverse().toString().toCharArray();
        char[] bChars = bBuilder.reverse().toString().toCharArray();
        //进位标志,1进位
        int cf = 0;
        for (int i = 0; i < aChars.length; i++) {
            int sum = (aChars[i] - '0') + (bChars[i] - '0');
            if (cf == 1){
                sum += 1;
            }
            if (sum >= 2){
                //进二位
                cf = 1;
                cBuilder.insert(i,sum - 2);
                //最后一位进位
                if (aChars.length == cBuilder.length()){
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
        String value = new AddBinary().addBinary("0","0");
        System.out.println(value);
    }

}
