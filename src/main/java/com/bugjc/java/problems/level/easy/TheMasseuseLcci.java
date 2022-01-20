package com.bugjc.java.problems.level.easy;

/**
 * 面试题 17.16. 按摩师
 *
 * @author aoki
 * @date 2021/1/14
 * @see <a href="https://leetcode-cn.com/problems/the-masseuse-lcci/">https://leetcode-cn.com</a>
 **/
public class TheMasseuseLcci {
    public int massage(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max((dp[i - 2] + nums[i]), dp[i - 1]);
        }

        return dp[length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 5, 3, 1, 1, 3};
        System.out.println(new TheMasseuseLcci().massage(nums));
    }
}
