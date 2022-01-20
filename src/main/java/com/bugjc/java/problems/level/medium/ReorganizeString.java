package com.bugjc.java.problems.level.medium;

/**
 * 767. 重构字符串
 *
 * @author aoki
 * @date 2020/11/30
 * @see <a href="https://leetcode-cn.com/problems/reorganize-string/">https://leetcode-cn.com</a>
 **/
public class ReorganizeString {
    public String reorganizeString(String s) {
        int length = s.length();
        if (length < 2) {
            return "";
        }

        int[] countHashTable = new int[26];
        int maxCount = 0;
        for (int i = 0; i < length; i++) {
            int currentCount = ++countHashTable[s.charAt(i) - 'a'];
            maxCount = Math.max(maxCount, currentCount);
        }

        if (maxCount > (length + 1) / 2) {
            return "";
        }

        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char currentChar = (char) (i + 'a');
            while (countHashTable[i] > 0 && countHashTable[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = currentChar;
                countHashTable[i]--;
                oddIndex += 2;
            }

            while (countHashTable[i] > 0) {
                reorganizeArray[evenIndex] = currentChar;
                countHashTable[i]--;
                evenIndex += 2;
            }
        }

        return new String(reorganizeArray);
    }

    public static void main(String[] args) {
        String s = "aab";
        s = "aaab";
        System.out.println(new ReorganizeString().reorganizeString(s));
    }
}
