package qyh.leetcode.midium;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 */
public class LeetCodeIndex395 {
    public static void main(String[] args) {
        System.out.println(longestSubstring("bbaaacbd", 3));
    }

    private static int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            if (counts[c - 'a'] < k) {
                String[] strs = s.split(String.valueOf(c));
                for (String str : strs) {
                    res = Math.max(res, longestSubstring(str, k));
                }
                return res;
            }
        }
        return s.length();
    }
}
