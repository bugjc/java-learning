package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 饼干分发
 * @author qingyang
 * @date 2018/9/13 17:51
 */
@Slf4j
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        //1、先按升序排列
        Arrays.sort(g);
        Arrays.sort(s);
        int sum = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (s[j] == -1){
                    continue;
                }
                if (g[i] <= s[j]){
                    sum += 1;
                    //排除已分配的小饼干
                    s[j] = -1;
                    break;
                }
                //匹配不到直接退出
                if (j == s.length -1){
                    return sum;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1,2,3,4};
        int[] s = new int[]{3};
        int sum = new AssignCookies().findContentChildren(g, s);
        log.info(sum + "");
    }

}
