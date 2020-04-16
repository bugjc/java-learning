package com.bugjc.java.problems.level.easy;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * @see <a href="https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/">https://leetcode-cn.com</a>
 * @author aoki
 * @date 2020/4/16
 * **/
public class StackPush {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //1,2,3   1,2,3 2,3,1 3,2,1
        Stack<Integer> stackPush = new Stack<Integer>();
        int index = 0;
        int len = pushed.length;
        for (int value : pushed) {
            stackPush.push(value);
            while (!stackPush.isEmpty() && index < len && stackPush.peek() == popped[index]) {
                stackPush.pop();
                index++;
            }
        }
        return stackPush.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = {1,2,3};
        //int[] popped = {3,2,1};
        //int[] popped = {1,2,3};
        //int[] popped = {2,1,3};
        int[] popped = {2,3,1};
        System.out.println(new StackPush().validateStackSequences(pushed, popped));
    }
}
