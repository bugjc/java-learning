package com.bugjc.java.problems.level.naive;

/**
 * @Author aoki
 * @Description 整数排序
 * @Date Create in 15:46 2017/8/7
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4};
        new Sort().sortIntegers(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    /**
     * 给一组整数，按照升序排序，使用选择排序，冒泡排序，插入排序或者任何 O(n2) 的排序算法。
     * @param A an integer array
     * @return void
     */
    public void sortIntegers(int[] A) {
        // Write your code here
        quickSort(A, 0, A.length - 1);
    }

    /**
     * 从left到right排序数组array
     *
     * @param array
     * @param left
     * @param right
     */
    private void quickSort(int[] array, int left, int right) {
        int index = partition(array, left, right);
        if (left < index - 1) {
            quickSort(array, left, index - 1);
        }
        if (index + 1 < right) {
            quickSort(array, index, right);
        }
    }

    /**
     * 找出一个基准点，排列数组array左边的都小于它，右边的都大于它
     *
     * @param array
     * @param left
     * @param right
     * @return 基准值数组索引
     */
    private int partition(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];
        int temp;

        while (left < right) {
            while (array[left] < pivot) left++;
            while (array[right] > pivot) right--;

            if (left < right) {
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }

        return left;
    }
}
