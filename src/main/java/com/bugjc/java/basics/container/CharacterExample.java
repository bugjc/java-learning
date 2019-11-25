package com.bugjc.java.basics.container;

import lombok.extern.slf4j.Slf4j;

/**
 * character
 * @author aoki
 * @date 2019/11/13
 * **/
@Slf4j
public class CharacterExample {



    /**
     * 判断是否小写
     * @param ch
     * @return
     */
    static boolean isLowerCase(char ch){
        return Character.isLowerCase(ch);
    }

    /**
     * 判断是否大写
     * @param ch
     * @return
     */
    static boolean isUpperCase(char ch){
        return Character.isUpperCase(ch);
    }

    /**
     * 判断是否数字
     * @param ch
     * @return
     */
    static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

}
