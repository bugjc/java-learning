package com.bugjc.java.problems.level.easy;

import java.util.Arrays;

/**
 * 976. 三角形的最大周长
 *
 * @author aoki
 * @date 2020/12/1
 * @see <a href="https://leetcode-cn.com/problems/largest-perimeter-triangle/">https://leetcode-cn.com</a>
 **/
public class LargestPerimeterTriangle {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new LargestPerimeterTriangle().largestPerimeter(new int[]{1,2,1}));
    }
}
