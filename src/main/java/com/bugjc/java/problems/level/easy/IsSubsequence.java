package com.bugjc.java.problems.level.easy;

/**
 * 392. 判断子序列
 *
 * @author aoki
 * @date 2021/1/14
 * @see <a href="https://leetcode-cn.com/problems/is-subsequence/">https://leetcode-cn.com</a>
 **/
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        if (t.length() == 0) {
            return false;
        }

        boolean[] flag = new boolean[s.length()];
        int i, j;
        for (i = s.length() - 1; i >= 0; i--) {
            for (j = t.length() - 1; j >= 0; j--) {
                if (t.charAt(j) == s.charAt(i)) {
                    flag[i] = true;
                    --i;
                    if (i < 0){
                        break;
                    }
                }
            }
        }

        for (boolean b : flag) {
            if (!b) {
                return false;
            }
        }
        return true;

    }


    public static void main(String[] args) {
        String s = "aaaaaa";
        String t = "bbaaaa";
        System.out.println(new IsSubsequence().isSubsequence(s, t));
    }
}
