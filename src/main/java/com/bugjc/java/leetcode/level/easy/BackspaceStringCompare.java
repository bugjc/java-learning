package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 比较含退格的字符串
 * @author qingyang
 * @date 2018/9/15 13:33
 */
@Slf4j
public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {
        StringBuilder sBuilder = new StringBuilder(S);
        StringBuilder tBuilder = new StringBuilder(T);
        String backspace = "#";
        for (int i = 0; i < sBuilder.length(); i++) {
            String val = String.valueOf(sBuilder.charAt(i));
            if (val.equals(backspace)){
                if (i > 0){
                    sBuilder.deleteCharAt(i - 1);
                    --i;
                }
                sBuilder.deleteCharAt(i);
                --i;
            }
        }

        for (int i = 0; i < tBuilder.length(); i++) {
            String val = String.valueOf(tBuilder.charAt(i));
            if (val.equals(backspace)){
                if (i > 0){
                    tBuilder.deleteCharAt(i - 1);
                    --i;
                }
                tBuilder.deleteCharAt(i);
                --i;
            }
        }

        return sBuilder.toString().equals(tBuilder.toString());
    }

    public static void main(String[] args) {
        String S = "##a#b";
        String T = "#c#b";
        boolean flag = new BackspaceStringCompare().backspaceCompare(S,T);
        log.info(flag + "");
    }

}
