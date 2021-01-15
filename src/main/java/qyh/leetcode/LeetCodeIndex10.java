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
        System.out.println(isMatched("aaa", "ab*a*c*a"));
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

    private static boolean isMatched(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        //"" 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串，其他的是都是false。
        //  奇数位不管什么字符都是false，偶数位为* 时则: dp[0][i] = dp[0][i - 2]
        for (int i = 2; i <= n; i+= 2) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];

    }
}
