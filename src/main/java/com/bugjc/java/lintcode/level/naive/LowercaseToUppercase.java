package com.bugjc.java.lintcode.level.naive;


/**
 * @author qingyang
 * @date 2018/9/11 11:48
 */
public class LowercaseToUppercase {

    public char lowercaseToUppercase(char character) {
        // write your code here
        return Character.toUpperCase(character);
    }

    public static void main(String[] args) {
        char num = new LowercaseToUppercase().lowercaseToUppercase('c');
        System.out.println(Character.toString(num));
    }

}
