package com.bugjc.java.problems.level.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 *
 * @author aoki
 * @date `2020/12/4`
 * @see <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">https://leetcode-cn.com</a>
 **/
public class ImplementStackUsingQueues {
    static class MyStack {

        private Queue<Integer> inQueue = null;
        private Queue<Integer> outQueue = null;
        private boolean flag = true;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            inQueue = new LinkedList<>();
            outQueue = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            if (!outQueue.isEmpty()) {
                flag = true;
                inQueue.offer(x);
                swap(outQueue, inQueue);
            } else {
                flag = false;
                outQueue.offer(x);
                swap(inQueue, outQueue);
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (flag){
                if (inQueue.isEmpty()){
                    return 0;
                }
                return inQueue.poll();
            }

            if (outQueue.isEmpty()){
                return 0;
            }
            return outQueue.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (flag){
                if (inQueue.isEmpty()){
                    return 0;
                }
                return inQueue.peek();
            }

            if (outQueue.isEmpty()){
                return 0;
            }
            return outQueue.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            if (flag){
                return inQueue.isEmpty();
            }

            return outQueue.isEmpty();
        }

        public void swap(Queue<Integer> inQueue, Queue<Integer> outQueue) {
            while (!inQueue.isEmpty()) {
                outQueue.offer(inQueue.poll());
            }
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.pop());
        System.out.println(obj.top());
        obj.push(3);
        System.out.println(obj.empty());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        obj.push(4);
        System.out.println(obj.empty());
    }
}
