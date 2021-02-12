package qyh.leetcode.midium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串排列
 */
public class LeetCodeIndex567 {
    public static void main(String[] args) {
        System.out.println(checkInclusion("adc", "dcda"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] cCount = new int[26];
        for (char c : s1.toCharArray()) {
            cCount[c - 'a']++;
        }
        int[] tmpCharCount = new int[26];
        int matchedCount = 0;
        int left = 0;
        int right = 0;
        while (right < s2.length()) {
            int charIndex = s2.charAt(right) - 'a';
            if (cCount[charIndex] == 0) {
                tmpCharCount = new int[26];
                matchedCount = 0;
                right++;
                left = right;
            } else {
                int count = tmpCharCount[charIndex];
                if (count == cCount[charIndex]) {
                    while (s2.charAt(left) != s2.charAt(right)) {
                        tmpCharCount[s2.charAt(left) - 'a']--;
                        matchedCount--;
                        left++;
                    }
                    left++;
                } else {
                    matchedCount++;
                    tmpCharCount[charIndex]++;
                }
                right++;
            }
            if (matchedCount == s1.length()) {
                return true;
            }
        }
        return false;
    }
}
