package qyh.leetcode.midium;

import java.util.Arrays;
import java.util.Stack;

/**
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 *             0
 *            /\
 *        100/  \500
 *          /    \
 *         1------2
 *            100
 */
public class LeetCodeIndex787 {
    public static void main(String[] args) {
        int[][] flights = {{}};
        System.out.println(findCheapestPrice(94, flights, 17, 33, 39));
    }

    public static int findCheapestPrice(int n,  int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 1][n];
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][0] = 0;
        }
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[0][flight[1]] = flight[2];
            }
        }
        for (int i = 1; i < k + 1; i++) {
            for (int[] flight : flights) {
                if (dp[k - 1][flight[0]] < Integer.MAX_VALUE) {
                    dp[k][flight[1]] = Math.min(dp[k - 1][flight[1]], dp[k - 1][flight[0]] + flight[2]);
                }
            }
        }
        return dp[k][dst] == Integer.MAX_VALUE ? -1 : dp[k][dst];
    }
}
