package com.bugjc.java.problems.level.easy;


import java.util.Arrays;

/**
 * 亲密字符串
 * @see <a href="https://leetcode-cn.com/problems/buddy-strings/">https://leetcode-cn.com</a>
 * @author qingyang
 * @date 2020/4/12 18:03
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c: count)
                if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first));
        }
    }


    public static void main(String[] args) {
        String[][] arr = {
                {"ab",  "ba"},
                {"ab",  "ab"},
                {"aa",  "aa"},
                {"aaaaaaabc",  "aaaaaaacb"},
                {"",  "aa"},
                {"abab",  "abab"},
                {"abcb",  "abcb"},
        };
        for (String[] strings : arr) {
            System.out.println(Arrays.toString(strings) +" 运行结果:" + new BuddyStrings().buddyStrings(strings[0],strings[1]));
        }

    }
}
