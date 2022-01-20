package com.bugjc.java.problems.level.medium;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Queue;

/**
 * 3. 无重复字符的最长子串
 *
 * @author aoki
 * @date 2020/11/26
 * @see <a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">https://leetcode-cn.com</a>
 **/
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }

        int max = 0;
        MyHash myHash = new MyHash(length);
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (myHash.contains(key)) {
                if (myHash.count() > max) {
                    max = myHash.count();
                }
                myHash.clear(key);
            }

            myHash.add(key);
        }

        if (myHash.count() > max) {
            max = myHash.count();
        }

        return max;
    }

    /**
     * 仅适用于本题的简单的 hash 表
     */
    public static class MyHash {
        private AsciiHash hash = new AsciiHash(128);
        private BitSet bits = new BitSet(128);
        //保存的最长子串。
        private Queue<Character> queue = null;

        MyHash(int length) {
            queue = new ArrayDeque<>(length);
        }

        public void add(char key) {
            queue.add(key);
            bits.set(hash.hash(key), true);
        }

        boolean contains(char key) {
            return bits.get(hash.hash(key));
        }

        void clear(char key) {
            Character badKey = queue.poll();
            while (badKey != null && badKey != key) {
                bits.clear(hash.hash(badKey));
                badKey = queue.poll();
            }
            bits.clear(hash.hash(key));
        }

        //返回最长子串数
        int count() {
            return queue.size();
        }

        static class AsciiHash {
            private int prime;

            AsciiHash(int prime) {
                this.prime = prime;
            }

            int hash(int key) {
                return (key % prime);
            }
        }
    }
}
