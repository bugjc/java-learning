package com.bugjc.java.problems.level.naive;

import com.bugjc.java.problems.level.naive.entity.ListNode;

/**
 * @Author: aoki
 * @Description: 删除链表中的元素
 * @Date: 16:13 2017/8/8
 */
public class LinkedList {

    /**
     *  删除链表中的元素
     *  给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
     * @param head a ListNodeD
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if (head == null) {
            return null;
        }

        ListNode current = head;

        while (head.next != null) {
            if (head.next.val == val) {
                if (head.next.next != null){
                    head.next = head.next.next;
                }else{
                    head.next=null;
                    break;
                }
            }else{
                head = head.next;
            }

        }

        if(current.val == val) return current.next;

        return current;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = new LinkedList().removeElements(node1,3);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
