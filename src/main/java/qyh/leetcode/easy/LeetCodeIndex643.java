package qyh.leetcode.easy;

/**
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数
 */
public class LeetCodeIndex643 {
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(nums, 4));
    }

    public static double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        int tmpSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                maxSum += nums[i];
                tmpSum += nums[i];
            } else {
                tmpSum = tmpSum - nums[i - k] + nums[i];
                maxSum = Math.max(tmpSum, maxSum);
            }
        }
        return (double) maxSum / k;
    }


}
