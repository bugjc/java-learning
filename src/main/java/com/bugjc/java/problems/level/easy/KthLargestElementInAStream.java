package com.bugjc.java.problems.level.easy;

/**
 * 703. 数据流中的第 K 大元素
 *
 * @author aoki
 * @date 2020/12/9
 * @see <a href="https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/">https://leetcode-cn.com</a>
 **/
public class KthLargestElementInAStream {
    static class KthLargest {
        private final int[] nums;
        public KthLargest(int k, int[] nums) {
            this.nums = nums;
        }

        public int add(int val) {
            return 0;
        }
    }

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */

    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(obj.add(3));
    }
}
