package com.bugjc.java.problems.level.medium;

/**
 * 24. 两两交换链表中的节点
 *
 * @author aoki
 * @date 2020/12/2
 * @see <a href="https://leetcode-cn.com/problems/swap-nodes-in-pairs/">https://leetcode-cn.com</a>
 **/
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode preNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.next == null || currentNode.next.next == null) {
                if (currentNode.next != null) {
                    currentNode.next.next = preNode;
                    preNode = currentNode;
                } else {
                    currentNode.next = preNode;
                    preNode = currentNode;
                }
                break;
            }

            ListNode nextNode = currentNode.next.next;
            currentNode.next.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        //节点翻转返回
        currentNode = preNode;
        preNode = null;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = new ListNode(1);
        ListNode result = new SwapNodesInPairs().swapPairs(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
