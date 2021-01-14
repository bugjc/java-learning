package com.bugjc.java.problems.level.medium;

/**
 * 64. 最小路径和
 *
 * @author aoki
 * @date 2021/1/14
 * @see <a href="https://leetcode-cn.com/problems/minimum-path-sum/">https://leetcode-cn.com</a>
 **/
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int rows = grid.length ;
        int columns = grid[0].length ;

        int[][] dp = new int[rows][columns];

        int sum = 0;
        for (int j = 0; j < columns; j++) {
            sum += grid[0][j];
            dp[0][j] = sum;
        }

        sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[rows - 1][columns - 1];
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(new MinimumPathSum().minPathSum(matrix));
    }
}
