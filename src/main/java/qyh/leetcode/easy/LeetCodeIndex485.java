package qyh.leetcode.easy;

import java.util.Map;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 */
public class LeetCodeIndex485 {
    public static void main(String[] args) {

    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int tmpMax = 0;
        for (int num : nums) {
            if (num == 0) {
                tmpMax = 0;
            } else {
                tmpMax += 1;
            }
            max = Math.max(tmpMax, max);
        }
        return max;
    }
}
