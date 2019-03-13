package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 交替位二进制数
 * @author qingyang
 * @date 2018/9/26 09:49
 */
@Slf4j
public class BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        String a = Integer.toBinaryString(n);
        for (int i = 0; i < a.length(); i++) {

            if (i + 1 == a.length()){
                break;
            }

            if (a.charAt(i) == a.charAt(i + 1)){
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        boolean flag = new BinaryNumberWithAlternatingBits().hasAlternatingBits(n);
        log.info("执行结果：{}",flag);
    }
}
