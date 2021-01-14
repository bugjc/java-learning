package com.bugjc.java.problems.level.easy;

/**
 * 746. 使用最小花费爬楼梯
 *
 * @author aoki
 * @date 2021/1/13
 * @see <a href="https://leetcode-cn.com/problems/min-cost-climbing-stairs/">https://leetcode-cn.com</a>
 **/
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums = {10, 15, 20};
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(nums));
    }
}
