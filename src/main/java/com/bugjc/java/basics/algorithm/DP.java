package com.bugjc.java.basics.algorithm;

/**
 * 动态规划
 *
 * @author aoki
 * @date 2020/4/27
 **/
public class DP {

    //假设有9个阶梯
    //问题拆解：1 | 2 | 3
    //状态定义：1+1 | 1+2
    //递推方程：dp[i] = dp[i -1] + (1 | 2)

    /**
     * 如何快速计算 n个1相加。
     * 第一步：问题拆解。 1 + 7个1 --> 1 + 6个1 --> ... --> 1 + 0;
     * 第二步：状态定义。后一个问题的答案等于前一个答案加 1；
     * 第三步：递推方程推导。dp[i] = dp[i-1] + 1;
     * 第四步：实现
     *
     * @param n --
     * @return int
     */
    public int dpExample(int n) {
        //多开一个用来存放 0 个 1相加的结果
        int[] dp = new int[n + 1];
        // 0 个 1相加
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
        }
        return dp[n];
    }

    /**
     * 三步问题
     * 第一步：问题拆解。 n-1 和 n-2
     * 第二步：状态定义。从起点到达第 i 个阶梯的路径总和
     * 第三步：递推方程推导。dp[i] = dp[i-1] + dp[i-2];
     * 第四步：实现
     *
     * @param n --
     * @return int
     */
    public int dpExample2(int n) {
        //多开一个用来存放 0 个 1相加的结果
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            if (i - 1 >= 0){
                dp[i] = dp[i - 1];
            }

            if (i - 2 >= 0){
                dp[i] = dp[i] + dp[i - 2];
            }

            if (i - 3 >= 0){
                dp[i] = dp[i] + dp[i - 3];
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new DP().dpExample(5));
        System.out.println(new DP().dpExample2(20));
    }

}
