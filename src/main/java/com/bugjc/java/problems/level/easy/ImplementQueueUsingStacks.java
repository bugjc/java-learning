package com.bugjc.java.problems.level.easy;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 *
 * @author aoki
 * @date 2020/12/4
 * @see <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">https://leetcode-cn.com</a>
 **/
public class ImplementQueueUsingStacks {
    static class MyQueue {

        private Stack<Integer> inStack = null;
        private Stack<Integer> outStack = null;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            swap(outStack, inStack);
            inStack.push(x);
            swap(inStack, outStack);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return outStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (outStack.isEmpty()) {
                return 0;
            }
            return outStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        public void swap(Stack<Integer> inStack, Stack<Integer> outStack) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */


    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.pop());
        System.out.println(obj.peek());
        obj.push(3);
        System.out.println(obj.empty());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        obj.push(4);
        System.out.println(obj.empty());
    }
}
