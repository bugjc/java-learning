package com.bugjc.java.basics.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lesson1 {


    public static void main(String[] args) {
        String[] arr = new String[]{"A", "D", "a", "B", "c", "b", "2", "8"};
        for (String s : arr) {
            if (Character.isLowerCase(s.charAt(0))) {
                //小写桶
                log.info("小写桶：{}", s);
            } else if (Character.isUpperCase(s.charAt(0))) {
                //大写桶
                log.info("大写桶：{}", s);
            } else if (Character.isDigit(s.charAt(0))) {
                //数字桶
                log.info("数字桶：{}", s);
            }
        }
    }

}
