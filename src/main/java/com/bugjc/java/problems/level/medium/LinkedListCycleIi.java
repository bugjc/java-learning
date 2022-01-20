package com.bugjc.java.problems.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 142. 环形链表 II
 *
 * @author aoki
 * @date 2020/12/3
 * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/">https://leetcode-cn.com</a>
 **/
public class LinkedListCycleIi {
    public ListNode detectCycle(ListNode head) {

        Map<Integer, ListNode> element = new HashMap<>();
        ListNode current = head;
        while (current != null) {
            int hashcode = current.hashCode();
            if (element.containsKey(hashcode)){
                return element.get(hashcode);
            }

            element.put(hashcode, current);
            current = current.next;
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
