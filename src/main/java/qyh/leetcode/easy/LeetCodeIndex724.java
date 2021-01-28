package qyh.leetcode.easy;

/**
 * 给定一个整数类型的数组nums，请编写一个能够返回数组 “中心索引” 的方法。
 *
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 */
public class LeetCodeIndex724 {
    public static void main(String[] args) {
        int[] nums = {-1,-1,-1,0,-1,-1};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int leftSum = 0;
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == rightSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
            rightSum -= nums[i];
        }
        return -1;
    }
}
