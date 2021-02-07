package qyh.leetcode.easy;

/**
 * 给你一个长度为n的整数数组，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的：对于数组中所有的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex665 {
    public static void main(String[] args) {
        int[] nums = {5,7,1,8};
        System.out.println(checkPossibility(nums));

    }

    private static boolean checkPossibility(int[] nums) {
        boolean changed = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (changed) {
                    return false;
                } else if ((i > 0 && nums[i + 1] < nums[i - 1])
                        && (i + 2 < nums.length - 1 && nums[i + 2] < nums[i])) {
                    return false;
                }
                changed = true;
            }
        }
        return true;
    }
}
