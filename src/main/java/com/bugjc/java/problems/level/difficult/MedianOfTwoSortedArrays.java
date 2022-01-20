package com.bugjc.java.problems.level.difficult;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * @author aoki
 * @date 2020/11/27
 * @see <a href="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/">https://leetcode-cn.com</a>
 **/
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] allNums = concatAllAndSorted(nums1, nums2);
        int length = allNums.length;
        int middle = length / 2;
        int value = allNums[middle];
        if (length % 2 == 0) {
            value += allNums[middle - 1];
            return (double) value / 2;
        } else {
            return value;
        }
    }

    public int[] concatAllAndSorted(int[] nums1, int[] nums2) {
        int[] result = Arrays.copyOf(nums1, (nums1.length + nums2.length));
        System.arraycopy(nums2, 0, result, nums1.length, nums2.length);
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}
