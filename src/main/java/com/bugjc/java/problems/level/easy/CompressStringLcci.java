package com.bugjc.java.problems.level.easy;

/**
 * 字符串压缩
 * @see <a href="https://leetcode-cn.com/problems/compress-string-lcci/">https://leetcode-cn.com</a>
 * @author 
 * @date 2020/4/15 9:49 下午
 */
public class CompressStringLcci {
    public String compressString(String S) {
        if (S == null || S.equals("")){
            return S;
        }

        int sLength = S.length();
        Character s = null;
        int count = 0;
        StringBuilder compressStr = new StringBuilder();
        for (int i = 0; i < sLength; i++) {
            int tempLength = i;
            Character temp = S.charAt(i);
            if (s == null){
                s = temp;
            }
            compressStr.append(temp);
            //饥饿查找压缩
            while (temp.equals(s)){
                count++;
                tempLength++;
                if (tempLength >= sLength){
                    break;
                }
                s = S.charAt(tempLength);
            }

            if (count == 0){
                count = 1;
            }
            compressStr.append(count);
            count = 0;
            i = --tempLength;
        }

        if (compressStr.length() < sLength){
            return compressStr.toString();
        }
        return S;
    }

    public String compressString2(String S) {
        int sLength = S.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < sLength) {
            int j = i;
            while (j < sLength && S.charAt(j) == S.charAt(i)) {
                j++;
            }
            sb.append(S.charAt(i));
            sb.append(j - i);
            i = j;
        }

        String res = sb.toString();
        if (res.length() < S.length()) {
            return res;
        } else {
            return S;
        }
    }

    public static void main(String[] args) {
        String S = "aabcccccaaa";
        S = "abbccd";
        System.out.println(new CompressStringLcci().compressString(S));
    }
}
