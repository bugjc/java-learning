package com.bugjc.java.problems.level.easy;

/**
 * 密钥格式化
 * @see <a href="https://leetcode-cn.com/problems/license-key-formatting/">https://leetcode-cn.com</a>
 * @author qingyang
 * @date 2020/4/12 18:03
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        String s = S.replaceAll("-","");
        if (s.equals("")){
            return "";
        }

        if (s.length() < K){
            return s;
        }

        int length = s.length();
        int group = (int) Math.ceil(length / (double) K);
        int size = length + group - 1;
        char[] newS = new char[size];
        int counter = K;
        for (int i = s.length() - 1; i >= 0; i--) {
            newS[--size] = s.charAt(i);
            counter--;
            if (i != 0 && counter == 0){
                counter = K;
                newS[--size] = '-';
            }
        }
        return String.valueOf(newS).toUpperCase();
    }

    public String licenseKeyFormatting2(String S, int K) {
        StringBuilder s = new StringBuilder(S.replaceAll("-",""));
        if (s.toString().equals("")){
            return "";
        }

        if (s.length() < K){
            return s.toString();
        }

        StringBuilder newS = new StringBuilder();
        int counter = K;
        for (int i = s.length() - 1; i >= 0; i--) {
            newS.append(s.charAt(i));
            counter--;
            if (i != 0 && counter == 0){
                counter = K;
                newS.append('-');
            }
        }
        return newS.reverse().toString().toUpperCase();
    }

    public static void main(String[] args) {
        String S = "5F3Z-2e-9-w";
        S = "5F3Z-2e-9-w-1";
        int K = 4;
        System.out.println(new LicenseKeyFormatting().licenseKeyFormatting(S, K));
        System.out.println(new LicenseKeyFormatting().licenseKeyFormatting2(S, K));
    }
}
