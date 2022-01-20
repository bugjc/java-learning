package com.bugjc.java.problems.level.easy;

/**
 * 70. 爬楼梯
 *
 * @author aoki
 * @date 2021/1/14
 * @see <a href="https://leetcode-cn.com/problems/climbing-stairs/">https://leetcode-cn.com</a>
 **/
public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int v1 = 1;
        int v2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = v1 + v2;
            v1 = v2;
            v2 = temp;
        }
        return v2;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(5));
    }
}
