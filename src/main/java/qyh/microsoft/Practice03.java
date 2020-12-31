package qyh.microsoft;

/**
 * 输入一个整形数组，数组里有正数也有负数。数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。
 *
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。
 */
public class Practice03 {
    public static void main(String[] args) {
        int[] vals = {1, 1, 1, -1, 1, -2, 1, 1, 1, 1, -1};
        System.out.println(maxSum(vals));
    }

    public static int maxSum(int[] vals) {
        int maxSum = vals[0];
        int currSum = 0;
        for (int val : vals) {
            currSum = currSum + val;
            if (currSum < 0) {
                currSum = 0;
            }
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
