package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、两数只和
 * 描述：
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * @author qingyang
 * @date 2018/9/11 16:49
 */
@Slf4j
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement) && hashMap.get(complement) != i){
                return new int[]{i,hashMap.get(complement)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum3(int[] nums, int target) {
        Map<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement)) {
                return new int[] { hashMap.get(complement), i };
            }
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();
        int[] num = new TwoSum().twoSum(nums, 12345);
        long endTime = System.currentTimeMillis();
        log.info("index: {}, {}",num[0],num[1]);
        log.info("{} ms",endTime - startTime);

        long startTime2 = System.currentTimeMillis();
        int[] num2 = new TwoSum().twoSum2(nums, 12345);
        log.info("index: {}, {}",num2[0],num2[1]);
        long endTime2 = System.currentTimeMillis();
        log.info("{} ms",endTime2 - startTime2);

        long startTime3 = System.currentTimeMillis();
        int[] num3 = new TwoSum().twoSum3(nums, 12345);
        log.info("index: {}, {}",num3[0],num3[1]);
        long endTime3 = System.currentTimeMillis();
        log.info("{} ms",endTime2 - startTime2);

    }
}