package com.bugjc.java.problems.level.easy;

/**
 * 7. 整数反转
 * @see <a href="https://leetcode-cn.com/problems/reverse-integer/">https://leetcode-cn.com</a>
 * @author aoki
 * @date 2020/11/26
 * **/
public class ReverseInteger {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0){
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(-1230));
    }
}
