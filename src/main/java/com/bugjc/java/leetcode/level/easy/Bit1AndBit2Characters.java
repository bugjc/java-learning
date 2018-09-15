package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 1比特与2比特字符
 * @see <a href="https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/description/">https://leetcode-cn.com</a>
 * @author qingyang
 * @date 2018/9/12 18:03
 */
@Slf4j
public class Bit1AndBit2Characters {

    public boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 1){
                if (i == bits.length - 2){
                    return false;
                }else {
                    bits[i] = 0;
                    bits[i+1] = 0;
                    ++i;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bits = new int[]{0, 0, 1, 0};
        boolean flag = new Bit1AndBit2Characters().isOneBitCharacter(bits);
        log.info("result {}",flag);
    }

}
