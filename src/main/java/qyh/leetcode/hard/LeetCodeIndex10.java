package qyh.leetcode.hard;

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
        System.out.println(isMatched("aab", "c*a*b"));
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
        /*
         *        a b * a * c * a
         *      0 1 2 3 4 5 6 7 8
         *   0  t f f f f f f f f
         * a 1  f t f t f t f t f
         * a 2  f f f f t t f t t
         * a 3  f f f f f t f t t
         */
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
