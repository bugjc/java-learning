package com.bugjc.java.problems.level.easy;

import com.bugjc.java.problems.level.easy.entity.ListNode;

/**
 * 反转链表
 *
 * @author
 * @date 2020/7/4 11:45 下午
 * @see <a href="https://leetcode-cn.com/problems/reverse-linked-list/">https://leetcode-cn.com</a>
 */
public class ReverseLinkedList {

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

        rootNode = new ReverseLinkedList().reverseList(rootNode);
        while (rootNode != null){
            System.out.println(rootNode.val);
            rootNode = rootNode.next;
        }


    }

    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode currNode = head;
        while (currNode != null) {
            ListNode nextTempNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextTempNode;
        }
        return preNode;
    }
}
