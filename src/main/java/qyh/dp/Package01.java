package qyh.dp;

import java.util.Arrays;

public class Package01 {
    public static void main(String[] args) {
        int capacity = 4;
        int[] size = {4, 3, 1};
        int[] value = {300, 200, 150};
        System.out.println(solve(capacity, size, value));
    }

    public static int solve(int capacity, int[] size, int[] value) {
        int[][] dp = new int[capacity + 1][size.length + 1];
        for (int i = 1; i < capacity + 1; i++) {
            for (int j = 1; j < size.length + 1; j++) {
                if (i - size[j - 1] >=0) {
                    // 一件物品只有一份
                    if (i - size[j - 1] != size[j - 1]) {
                        dp[i][j] = Math.max(dp[i - size[j - 1]][size.length] + value[j - 1], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[capacity][size.length];
    }
}
