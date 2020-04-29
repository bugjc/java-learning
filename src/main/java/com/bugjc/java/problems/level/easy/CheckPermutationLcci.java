package com.bugjc.java.problems.level.easy;


import java.util.HashMap;
import java.util.Map;

/**
 * 判定是否互为字符重排
 * @see <a href="https://leetcode-cn.com/problems/check-permutation-lcci/">https://leetcode-cn.com</a>
 * @author qingyang
 * @date 2020/4/14 10:59 下午
 */
public class CheckPermutationLcci {
    public boolean CheckPermutation(String s1, String s2) {
        if ((s1 == null || s1.equals("")) || (s2 == null || s2.equals(""))){
            return false;
        }

        if (s1.length() != s2.length()){
            return false;
        }

        //统计 s1 字符串的每个字符出现的次数
        Map<Character,Integer> s1Map = new HashMap<>();
        for (char s : s1.toCharArray()) {
            //不管 key 存不存在都会保存到 map 中，不存在则初始化值为 1，存在则加 1.
            s1Map.compute(s,(k,v)->{
                if (v == null){
                    return 1;
                }
                return v + 1;
            });
        }

        //消减计数值
        for (char s : s2.toCharArray()){
            //存在 key 且 value - 1 等于 0 则删除自身，否则执行 value -1
            s1Map.computeIfPresent(s,(k,v)-> {
                if (v - 1 == 0){
                    s1Map.remove(k);
                    return null;
                }
                return v -1;
            });
        }

        return s1Map.isEmpty();
    }

    public boolean CheckPermutation2(String s1, String s2) {
        if ((s1 == null || s1.equals("")) || (s2 == null || s2.equals(""))){
            return false;
        }

        if (s1.length() != s2.length()){
            return false;
        }

        //统计 s1 字符串的每个字符出现的次数
        Map<Character,Integer> s1Map = new HashMap<>();
        for (char s : s1.toCharArray()) {
            //不管 key 存不存在都会保存到 map 中，不存在则初始化值为 1，存在则加 1.
            s1Map.compute(s,(k,v)->{
                if (v == null){
                    return 1;
                }
                return v + 1;
            });
        }

        //消减计数值
        for (char k : s2.toCharArray()){
            //存在 key 且 value - 1 等于 0 则删除自身，否则执行 value -1
            if (s1Map.containsKey(k)){
                int v = s1Map.get(k);
                if (v - 1 == 0){
                    s1Map.remove(k);
                    continue;
                }
                s1Map.put(k,(v - 1));
            }
        }

        return s1Map.isEmpty();
    }

    public static void main(String[] args) {
        //String s1 = "abc", s2 = "bca";
        //String s1 = "abc", s2 = "bad";
        String s1 = "aabaac", s2 = "bcaaaa";
        System.out.println(new CheckPermutationLcci().CheckPermutation2(s1, s2));
    }
}
