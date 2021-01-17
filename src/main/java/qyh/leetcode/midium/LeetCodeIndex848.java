package qyh.leetcode.midium;

import java.util.Arrays;

/**
 * 第148题
 *
 * @since 2020-12-13
 *
 * 字母移位
 * 输入：S = "abc", shifts = [3,5,9]
 * 输出："rpl"
 */
public class LeetCodeIndex848 {
    public static void main(String[] args) {
        String s = "abc";
        int[] shifts = {3,5,9};
        Solution solution = new Solution();
        System.out.println(solution.shiftingLetters(s, shifts));
    }

    private static class Solution {
        public String shiftingLetters(String S, int[] shifts) {
            char[] sChars = S.toCharArray();
            for(int i = shifts.length - 1; i >= 0; i--) {
                if (i == shifts.length - 1) {
                    shifts[i] = shifts[i] % 26;
                } else {
                    shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
                }
            }
            for (int i = 0; i < sChars.length; i++) {
                sChars[i] = (char)((((sChars[i] + shifts[i]) - 97) % 26) + 97);
            }
            return new String(sChars);
        }
    }
}
