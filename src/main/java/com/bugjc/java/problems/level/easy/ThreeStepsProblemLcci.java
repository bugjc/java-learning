package com.bugjc.java.problems.level.easy;

/**
 * 三步问题
 *
 * @author aoki
 * @date 2020/4/17
 * @see <a href="https://leetcode-cn.com/problems/three-steps-problem-lcci/">https://leetcode-cn.com</a>
 **/
public class ThreeStepsProblemLcci {
    /**
     * 递归公式：f(n) = f(n-1) + f(n-2) + f(n-3);f(1) = 1; f(2)=2;f(3)=4;
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        return f(n) % 1000000007;
    }

    private int f(int n){
        if (n == 1) {
            return 1;
        }

        if (n == 2 ) {
            return 2;
        }

        if (n == 3 ) {
            return 4;
        }
        return f(n - 1) + f(n - 2) + f(n - 3);
    }


    public int waysToStep2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2 ) {
            return 2;
        }

        if (n == 3 ) {
            return 4;
        }

        return 0;
    }

    /**
     *
     * @param items     --物品重量
     * @param n         --物品个数
     * @param w         --背包可承受重量
     * @return
     */
    public static int knapsack2(int[] items, int n, int w){
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        if (items[0] <= w){
            states[items[0]] = true;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = w-items[i]; j >= 0 ; --j) {
                if (states[j] == true){
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0 ; --i) {
            if (states[i] == true){
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeStepsProblemLcci().waysToStep(40));
    }

}
