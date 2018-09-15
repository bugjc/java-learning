package com.bugjc.java.leetcode.level.easy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 二叉树的层平均值
 * @author qingyang
 * @date 2018/9/13 21:56
 */
@Slf4j
public class AverageOfLevelsInBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        double sum = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();
                assert node != null;
                sum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }

            result.add(sum / size);
            sum = 0;
        }

        return result;

    }


    public static void main(String[] args) {
        AverageOfLevelsInBinaryTree averageOfLevelsInBinaryTree = new AverageOfLevelsInBinaryTree();
        TreeNode rootNode = averageOfLevelsInBinaryTree.new TreeNode(1);
        TreeNode leftNode1 = averageOfLevelsInBinaryTree.new TreeNode(2);
        TreeNode leftNode11 = averageOfLevelsInBinaryTree.new TreeNode(4);
        TreeNode rightNode12 = averageOfLevelsInBinaryTree.new TreeNode(4);
        leftNode1.left = leftNode11;
        leftNode1.right = rightNode12;
        TreeNode rightNode1 = averageOfLevelsInBinaryTree.new TreeNode(2);
        TreeNode leftNode21 = averageOfLevelsInBinaryTree.new TreeNode(3);
        TreeNode rightNode22 = averageOfLevelsInBinaryTree.new TreeNode(3);
        rightNode1.left = leftNode21;
        rightNode1.right = rightNode22;
        rootNode.left = leftNode1;
        rootNode.right = rightNode1;
        List<Double> list = averageOfLevelsInBinaryTree.averageOfLevels(rootNode);
        log.info(JSON.toJSONString(list));
    }


}
