package qyh.leetcode.midium;

import java.util.*;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 *
 */
public class LeetCodeIndex131 {
    private static boolean[][] dp;
    private static List<List<String>> ans = new ArrayList<>();
    private static Stack<String> curr = new Stack<>();
    private static int n;

    public static void main(String[] args) {
        System.out.println(partition("a"));
    }

    public static List<List<String>> partition(String s) {
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
        dfs(s, 0);
        return ans;
    }

    private static void dfs(String s, int start) {
        if (start == n) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < n; i++) {
            if (dp[start][i]) {
                curr.push(s.substring(start, i + 1));
                dfs(s, i + 1);
                curr.pop();
            }
        }
    }
}
