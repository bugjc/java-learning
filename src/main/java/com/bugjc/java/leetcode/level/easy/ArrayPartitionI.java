package com.bugjc.java.leetcode.level.easy;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author qingyang
 * @date 2018/9/13 16:37
 */
@Slf4j
public class ArrayPartitionI {

    public int arrayPairSum(int[] nums) {

        //将数组按升序排序
        Arrays.sort(nums);

        //将两两相邻的最小元素相加
        int sum = 0;
        for(int i = 0;i < nums.length;i += 2) {
            sum+=nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        int sum = new ArrayPartitionI().arrayPairSum(nums);
        log.info(sum + "");
    }

}
