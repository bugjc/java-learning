package com.bugjc.java.problems.level.easy;

import com.bugjc.java.problems.level.easy.entity.ListNode;

public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode rootNode = new ListNode(1);
        ListNode childOneNode = new ListNode(2);
        ListNode childTwoNode = new ListNode(3);
        ListNode childThreeNode = new ListNode(4);
        ListNode childFourNode = new ListNode(5);
        rootNode.next = childOneNode;
        childOneNode.next = childTwoNode;
        childTwoNode.next = childThreeNode;
        childThreeNode.next = childFourNode;
        childFourNode.next = childOneNode;

        boolean mark = new LinkedListCycle().hasCycle(rootNode);
        System.out.println(mark);


    }

    public boolean hasCycle(ListNode head) {
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (head != null) {
            if (slowNode.next == null) {
                return false;
            }
            slowNode = slowNode.next;

            if (fastNode.next == null) {
                return false;
            }

            if (fastNode.next.next == null) {
                return false;
            }

            fastNode = fastNode.next.next;

            if (slowNode == fastNode) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
