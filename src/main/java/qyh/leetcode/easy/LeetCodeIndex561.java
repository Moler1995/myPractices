package qyh.leetcode.easy;

import java.util.Arrays;

/**
 * 给定长度为2n的整数数组 nums ，你的任务是将这些数分成n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到n 的 min(ai, bi) 总和最大。
 *
 * 返回该 最大总和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-partition-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex561 {
    public static void main(String[] args) {
        int[] nums = {6,2,6,5,1,2};
        System.out.println(arrayPairSum(nums));
    }

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length; i += 2) {
            max += nums[i];
        }
        return max;
    }
}
