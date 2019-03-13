package com.bugjc.java.problems.level.naive;


import com.bugjc.java.problems.level.naive.entity.TreeNode;

/**
 * @Author: aoki
 * @Description: 二叉树的最大节点
 * @Date: 14:46 2017/8/7
 */
public class Solution {

    private static int maxNum = -9999;
    private static TreeNode node = null;

    public static void main(String[] args) {
        String str = "[1,-5,3,1,2,-4,-5]";
        TreeNode root = TreeNode.mkTree(str);
        System.out.println(root);
        TreeNode maxNode = new Solution().maxNode(root);
        System.out.println(maxNode.val);
    }

    /**
     * @param root the root of binary tree
     * @return the max ndoe
     */
    public TreeNode maxNode(TreeNode root) {
        // Write your code here
        if (root == null)
            return null;

        max(root);
        return node;
    }

    void max(TreeNode root) {
        if (root == null){
            return;
        }

        if (root.val > maxNum) {
            maxNum = root.val;
            node = root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        max(left);
        max(right);
    }

}
