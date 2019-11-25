package com.bugjc.java.basics.container.linkedlist;

/**
 * 1）单链表的插入、删除、查找操作；
 * 2）链表中存储的是int类型的数据；
 *
 * @author Zheng
 */
public class SinglyLinkedListTest {

    private Node head = null;

    public void insertToHead(int val){
        Node newNode = createNode(val);
        if (head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAfter(Node p,int val){
        if (p == null){
            return;
        }
        Node newNode = createNode(val);
        newNode.next = p.next;
        p.next = newNode;
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedListTest singlyLinkedListTest = new SinglyLinkedListTest();
        singlyLinkedListTest.insertToHead(1);
        singlyLinkedListTest.insertToHead(2);
        singlyLinkedListTest.insertToHead(3);
        singlyLinkedListTest.insertAfter(singlyLinkedListTest.head,4);

    }
}
