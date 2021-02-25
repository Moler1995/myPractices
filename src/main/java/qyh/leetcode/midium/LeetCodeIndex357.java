package qyh.leetcode.midium;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 */
public class LeetCodeIndex357 {
    public static void main(String[] args) {

    }

    private static int countNumbersWithUniqueDigits(int n) {
        if (n > 9) {
            n = 9;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j > 1; j--) {
                nums[i] += 10 * nums[j];
            }

        }
        return 0;
    }
}
