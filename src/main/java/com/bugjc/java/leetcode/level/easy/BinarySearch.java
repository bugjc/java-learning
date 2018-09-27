package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 二分查找
 * @author qingyang
 * @date 2018/9/26 10:18
 */
@Slf4j
public class BinarySearch {

    /**
     * 算法实现核心思想：
     * 1、缩小查找范围
     * 2、将边界值排除掉
     * 3、找出查找不到的规律
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int size = nums.length / 2;
        int round = 0;
        int min = 0;
        int max = nums.length - 1;

        //无效的值
        if (target < nums[min]){
            return -1;
        }
        //无效的值
        if (target > nums[max]){
            return -1;
        }
        //排除首位
        if (target == nums[min]){
            return min;
        }
        //排除末位
        if (target == nums[max]){
            return max;
        }

        do {
            log.info("总共查找次数：{}", ++round);
            //设置当前可查找的范围(饥饿查找)
            int temp = nums[size];
            if (temp < target) {
                min = size;
            } else if (temp > target){
                max = size;
            }else {
                return size;
            }
            size = (max - min) / 2 + min;
            //范围差1则是查找不到值
        } while ((max - min) != 1);

        return -1;
    }

    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        int round = 0;
        while (low <= high) {
            log.info("总共查找次数：{}", ++round);
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key){
                low = mid + 1;
            }else if (midVal > key){
                high = mid - 1;
            }else{
                // key found
                return mid;
            }

        }
        // key not found.
        return -(low + 1);
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 3;
        int val = new BinarySearch().search(nums,target);
        log.info("执行结果：{}",val);
        log.info("----------------------");
        val = binarySearch0(nums,0,nums.length,target);
        log.info("执行结果：{}",val);
    }
}
