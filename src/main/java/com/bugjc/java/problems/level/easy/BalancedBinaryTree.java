package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 平衡二叉树
 * @author qingyang
 * @date 2018/9/15 14:38
 */
@Slf4j
public class BalancedBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {

//        if (root == null){
//            return true;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        TreeNode p;
//        int tier = 0;
//        Map<Integer,Integer> tierAVgMap = new HashMap<>();
//        while (!queue.isEmpty()){
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                p = queue.poll();
//                assert p != null;
//                if (p.left != null){
//                    queue.add(p.left);
//                }
//                if (p.right != null){
//                    queue.add(p.right);
//                }
//            }
//
//            //计算当前层最大节点数量
//            double num = Math.pow(2,tier++);
//            //当前层节点是否已满，1已满，0未满
//            int n = (int)num == size ? 1 : 0;
//            if (tierAVgMap.containsKey(tier - 1)){
//                if (n == 0 && tierAVgMap.get(tier - 1) == 0){
//                    return false;
//                }
//            }
//
//            tierAVgMap.put(tier,n);
//
//        }
        getDepth(root);
        return isBalanced;
    }

    public int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);

        if(Math.abs(left - right) > 1){
            isBalanced = false;
        }
        return right > left ? right + 1 : left + 1;

    }


    public static void main(String[] args) {
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        TreeNode rootNode = balancedBinaryTree.new TreeNode(3);
        TreeNode leftNode1 = balancedBinaryTree.new TreeNode(9);
        TreeNode rightNode1 = balancedBinaryTree.new TreeNode(20);
        rootNode.left = leftNode1;
        rootNode.right = rightNode1;

        TreeNode leftNode21 = balancedBinaryTree.new TreeNode(15);
        TreeNode rightNode22 = balancedBinaryTree.new TreeNode(7);
        rightNode1.left = leftNode21;
        //rightNode1.right = rightNode22;

        boolean flag = balancedBinaryTree.isBalanced(rootNode);
        log.info(flag + "");
    }
}
