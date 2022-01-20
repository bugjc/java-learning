package com.bugjc.java.problems.level.easy;

/**
 * 53. 最大子序和
 *
 * @author aoki
 * @date 2021/1/13
 * @see <a href="https://leetcode-cn.com/problems/maximum-subarray/">https://leetcode-cn.com</a>
 **/
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4, 10};
        System.out.println(new MaximumSubarray().maxSubArray(nums));
    }
}
