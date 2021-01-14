package com.bugjc.java.problems.level.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 22. 括号生成
 * @see <a href="https://leetcode-cn.com/problems/generate-parentheses/">https://leetcode-cn.com</a>
 * @author aoki
 * @date 2020/12/18
 * **/
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        Map<String,String> combination = new HashMap<>();
        for (int i = 0; i < n; i++) {
            list.add("()");
        }

        for (int i = 0; i < n; i++) {
            list.add(")");
        }

        return list;
    }
}
