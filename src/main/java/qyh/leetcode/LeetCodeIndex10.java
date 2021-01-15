package qyh.leetcode;

import java.util.Stack;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * s = "aa" p = "a*"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class LeetCodeIndex10 {
    public static void main(String[] args) {
        System.out.println(isMatch("aaa", "ab*a*c*a"));
    }

    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() <= 0;
        }
        int sIndex = s.length() - 1;
        for (int i = p.length() - 1; i >= 0;) {
            if (p.charAt(i) == '*') {
                if (sIndex < 0) {
                    i -= 2;
                    continue;
                }
                while (sIndex >= 0) {
                    if (s.charAt(sIndex) == p.charAt(i - 1) || p.charAt(i - 1) == '.') {
                        sIndex--;
                    } else {
                        break;
                    }
                }
                i -= 2;
            } else {
                if (sIndex >= 0 && (s.charAt(sIndex) == p.charAt(i) || p.charAt(i) == '.')) {
                    sIndex--;
                } else {
                    return false;
                }
                i--;
            }
        }
        return sIndex == -1;
    }
}
