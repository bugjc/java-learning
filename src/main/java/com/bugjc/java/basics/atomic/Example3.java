package com.bugjc.java.basics.atomic;


import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Example3 {
    private int val;
    private volatile Example3 left, right;

    private static final AtomicReferenceFieldUpdater leftUpdater = AtomicReferenceFieldUpdater.newUpdater(Example3.class, Example3.class, "left");
    private static AtomicReferenceFieldUpdater rightUpdater = AtomicReferenceFieldUpdater.newUpdater(Example3.class, Example3.class, "right");

    boolean compareAndSetLeft(Example3 expect, Example3 update) {
        return leftUpdater.compareAndSet(this, expect, update);
    }

    public Example3() {
        this.left = this.right = null;
    }

    public Example3(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    public Example3(Example3 left, Example3 right) {
        this.left = left;
        this.right = right;
    }


    public static void main(String[] args) {
        Example3 node = new Example3(1);
        node.left = new Example3(new Example3(2),new Example3(3));
        node.right = new Example3(new Example3(4),new Example3(5));
        System.out.println(JSON.toJSON(node));
        node.compareAndSetLeft(node.left,node.right);
        System.out.println(JSON.toJSON(node));

    }






    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Example3 getLeft() {
        return left;
    }

    public void setLeft(Example3 left) {
        this.left = left;
    }

    public Example3 getRight() {
        return right;
    }

    public void setRight(Example3 right) {
        this.right = right;
    }
}
