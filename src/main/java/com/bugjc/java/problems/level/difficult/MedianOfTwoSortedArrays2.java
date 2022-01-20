package com.bugjc.java.problems.level.difficult;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * @author aoki
 * @date 2020/11/27
 * @see <a href="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/">https://leetcode-cn.com</a>
 **/
public class MedianOfTwoSortedArrays2 {
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

    private Unsafe unsafe = null;

    /**
     * 使用 unsafe 合并数组并排序
     *
     * @param nums1 --数组1
     * @param nums2 --数组2
     * @return
     */
    public int[] concatAllAndSorted(int[] nums1, int[] nums2) {
        //获取 unsafe
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
            //ignore
        }

        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int index = Math.max(nums1Length, nums2Length);
        int[] allNums = new int[nums1Length + nums2Length];
        int allNumsLength = 0;
        for (int i = 0; i < index; i++) {
            //分别获取数组中第 i 列的值
            int num1 = unsafe.getInt(nums1, locationAddr(i, nums1));
            int num2 = unsafe.getInt(nums2, locationAddr(i, nums2));

            //插入并排序
            if (num1 < num2 && num2 != 0) {
                allNums[allNumsLength] = num1;
                allNums[++allNumsLength] = num2;
            } else if (num1 > num2 && num2 != 0) {
                allNums[allNumsLength] = num2;
                allNums[++allNumsLength] = num1;
            } else if (num1 == num2 && num2 != 0) {
                allNums[allNumsLength] = num1;
                allNums[++allNumsLength] = num2;
            } else {
                allNums[allNumsLength] = num1;
            }

            allNumsLength++;
        }

        return allNums;
    }

    /**
     * 计算数组指定下标索引的数据存储地址
     *
     * @param index --数组下标
     * @param nums  --数组
     * @return
     */
    private long locationAddr(int index, int[] nums) {
        //计算存储位置：arrayBaseOffset = arrayBaseOffset + index * arrayIndexScale;
        //返回 location 数组中第一个元素的偏移地址
        long arrayBaseOffset = unsafe.arrayBaseOffset(nums.getClass());
        //返回数组中一个元素占用的大小
        int arrayIndexScale = unsafe.arrayIndexScale(nums.getClass());
        //31减去 arrayIndexScale 从左往右0的个数
        int tShift = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
        return ((long) index << tShift) + arrayBaseOffset;
    }


    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays2().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
