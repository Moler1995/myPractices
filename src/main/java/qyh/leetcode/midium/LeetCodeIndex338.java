package qyh.leetcode.midium;

import java.util.Arrays;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 */
public class LeetCodeIndex338 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(16)));
    }

    private static int[] countBits(int num) {
        int[] result = new int[num + 1];
        int n = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                n = i;
            }
            result[i] = result[i - n] + 1;
        }
        return result;
    }
}
