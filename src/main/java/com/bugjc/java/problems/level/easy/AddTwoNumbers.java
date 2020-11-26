package com.bugjc.java.problems.level.easy;

/**
 * 2. 两数相加
 *
 * @author aoki
 * @date 2020/11/26
 * @see <a href="https://leetcode-cn.com/problems/add-two-numbers/">https://leetcode-cn.com</a>
 **/
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode rootNode = new ListNode();
        ListNode resultNode = rootNode;
        int c = 0;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                l1 = new ListNode();
            }

            if (l2 == null) {
                l2 = new ListNode();
            }

            int sum = l1.val + l2.val + c;
            if (sum >= 10) {
                c = 1;
                sum = sum - 10;
            } else {
                c = 0;
            }

            rootNode.next = new ListNode(sum);
            rootNode = rootNode.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        if (c != 0) {
            rootNode.next = new ListNode(c);
        }

        return resultNode.next;
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
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));

        ListNode result = new AddTwoNumbers().addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
