package com.bugjc.java.problems.level.medium;

import com.alibaba.fastjson.JSON;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author aoki
 * @date 2020/12/1
 * @see <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/">https://leetcode-cn.com</a>
 **/
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) {
            return result;
        }

        int mid = nums.length / 2;
        if (nums.length == 1 && target == nums[mid]) {
            result[0] = 0;
            result[1] = 0;
            return result;
        }

        int maxLength = nums.length - 1;
        int left = 0, right = maxLength;

        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                result[0] = mid;
                result[1] = mid;

                int leftIndex = mid;
                while (leftIndex > 0 && nums[--leftIndex] == target) {
                    result[0] = leftIndex;
                }

                int rightIndex = mid;
                while (rightIndex < maxLength && nums[++rightIndex] == target) {
                    result[1] = rightIndex;
                }
                return result;
            }


        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(JSON.toJSONString(new FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, target)));
    }
}
