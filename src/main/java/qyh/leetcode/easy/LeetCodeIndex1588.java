package qyh.leetcode.easy;

/**
 * 所有奇数长度子数组的和
 *
 * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 *
 * 子数组 定义为原数组中的一个连续子序列。
 *
 * 请你返回 arr 中 所有奇数长度子数组的和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1588 {
    public static int sumOddLengthSubarrays(int[] arr) {
        int[] prefixSum = new int[arr.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum[i + 1] = arr[i] + prefixSum[i];
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; i + j <= arr.length; j += 2) {
                int end = i + j - 1;
                sum += prefixSum[end + 1] - prefixSum[i];
            }
        }
        return sum;
    }
}
