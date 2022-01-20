package com.bugjc.java.problems.level.easy;

import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * @author aoki
 * @date 2020/12/3
 * @see <a href="https://leetcode-cn.com/problems/valid-parentheses/">https://leetcode-cn.com</a>
 **/
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push('&');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' || c == ']' || c == '}') {
                char c1 = stack.pop();
                if (c1 == '(' && c == ')') {
                    continue;
                } else if (c1 == '[' && c == ']') {
                    continue;
                } else if (c1 == '{' && c == '}') {
                    continue;
                } else {
                    return false;
                }
            }
            stack.push(c);
        }

        stack.pop();
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "]";
        System.out.println(new ValidParentheses().isValid(s));
    }
}
