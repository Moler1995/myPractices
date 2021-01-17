package qyh.leetcode.midium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 */
public class LeetCodeIndex474 {
    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxForm(strs, 5, 3));
//        System.out.println(Arrays.toString(count("101001")));
    }

    /**
     * 错
     * @param strs str
     * @param m m
     * @param n n
     * @return int
     */
    public static int findMaxForm(String[] strs, int m, int n, boolean flag) {
        Set<Integer>[][] dp = new Set[(m+1) * (n+1) + 1][strs.length + 1];
        int[][] counts = new int[strs.length][2];
        int index = 0;
        for (String s : strs) {
            counts[index++] = count(s);
        }
        for (int i = 1; i < dp.length; i++) {
            int mCount = (i - 1) / (n + 1);
            int nCount = (i - 1) % (n + 1);
            for (int j = 1; j < strs.length + 1; j++) {
                int[] count = counts[j - 1];
                if (mCount < count[0] || nCount < count[1]) {
                    if (dp[i][j - 1] != null) {
                        dp[i][j] = new HashSet<>(dp[i][j - 1]);
                    }
                    continue;
                }
                int subProblemIndex = (mCount - count[0]) * (n + 1) + (nCount - count[1]) + 1;
                Set subRes = dp[subProblemIndex][strs.length];
                if (subRes == null) {
                    if (dp[i][j - 1] == null) {
                        dp[i][j] = new HashSet();
                        dp[i][j].add(j);
                    } else {
                        dp[i][j] = new HashSet(dp[i][j - 1]);
                    }
                } else {
                    if (dp[i][j - 1] != null) {
                        if (subRes.size() + 1 > dp[i][j - 1].size() && !subRes.contains(j)) {
                            dp[i][j] = new HashSet(subRes);
                            dp[i][j].add(j);
                        } else {
                            dp[i][j] = new HashSet(dp[i][j - 1]);
                        }
                    } else {
                        dp[i][j] = new HashSet(subRes);
                        dp[i][j].add(j);
                    }
                }
            }
        }
        // Arrays.stream(dp).forEach(arr -> System.out.println(Arrays.toString(arr)));
        return dp[dp.length - 1][strs.length] == null ? 0 : dp[dp.length - 1][strs.length].size();
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] counts = count(s);
            for (int i = m; i >= counts[0]; i--) {
                for (int j = n; j >= counts[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - counts[0]][j - counts[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private static int[] count(String str) {
        int[] counts = new int[2];
        for (char s : str.toCharArray()) {
            if (s == '0') {
                counts[0] += 1;
            }
            if (s == '1') {
                counts[1] += 1;
            }
        }
        return counts;
    }
}
