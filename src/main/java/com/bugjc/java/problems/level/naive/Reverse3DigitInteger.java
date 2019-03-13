package com.bugjc.java.problems.level.naive;

/**
 * @author qingyang
 * @date 2018/9/11 11:48
 */
public class Reverse3DigitInteger {

    /**
     * @param number: A 3-digit number.
     * @return: Reversed number.
     */
    private int reverseInteger(int number) {
        // write your code here
        String numberStr = number + "";
        StringBuilder result = new StringBuilder();
        for (int i = numberStr.length()-1; i >= 0; i--) {
            result.append(String.valueOf(numberStr.charAt(i)));

        }
        return Integer.parseInt(result.toString());
    }

    public static void main(String[] args) {
        int num = new Reverse3DigitInteger().reverseInteger(900);
        System.out.println(num);
    }

}
