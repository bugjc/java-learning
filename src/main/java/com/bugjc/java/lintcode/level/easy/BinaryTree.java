package com.bugjc.java.lintcode.level.easy;


import com.bugjc.java.lintcode.level.easy.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Description 二叉树的路径和
 * @Date Create in 16:19 2017/8/8
 */
public class BinaryTree {

    private List<List<Integer>> lists = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();
    private int sum = 0;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        List<List<Integer>> lists = new BinaryTree().findPath(node1, 5);
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j));
            }
            System.out.println();
        }


    }

    /**
     * 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
     * 一个有效的路径，指的是从根节点到叶节点的路径。
     *
     * @param root   the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        if (root == null)
            return lists;

        integers.add(root.val);
        target -= root.val;

        if (target == 0) {
            lists.add(new ArrayList<Integer>(integers));
        }


        binaryTreePathSum(root.left, target);
        binaryTreePathSum(root.right, target);
        integers.remove(integers.size() - 1);
        return lists;
    }

    public List<List<Integer>> findPath(TreeNode root, int target) {
        List<List<Integer>> pathlist = new ArrayList<>();
        if (root == null || root.val > target)
            return pathlist;
        List<Integer> path = new ArrayList<>();
        findPath(root, target, pathlist, path);
        return pathlist;
    }

    private void findPath(TreeNode root, int target, List<List<Integer>> pathlist, List<Integer> path) {
        //如果节点为空，或者值小于target此条路径清空
        if (root == null || root.val > target) {
            path.clear();
        } else if (root.val == target)//如果当前节点等于target且为叶子节点则直接将它添加到path中，否则这条路径清空
        {
            if (root.left == null && root.right == null) {
                path.add(root.val);
                pathlist.add(path);
            } else {
                path.clear();
            }
        } else  //当根节点的值小于target，则递归去寻找它的左右子树
        {
            path.add(root.val);
            ArrayList<Integer> path2 = new ArrayList<Integer>();
            path2.addAll(path);
            target -= root.val;
            findPath(root.left, target, pathlist, path);
            findPath(root.right, target, pathlist, path2);
        }
    }


}
