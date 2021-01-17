package qyh.leetcode.midium;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 执行用时：
 * 88 ms
 * , 在所有 Java 提交中击败了
 * 5.40%
 * 的用户
 * 内存消耗：
 * 38.1 MB
 * , 在所有 Java 提交中击败了
 * 25.20%
 * 的用户
 */
public class LeetCodeIndex300 {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,13,101,6,8,11};
        System.out.println(LIS(nums));
    }

    /**
     * 执行用时：
     *  * 88 ms
     *  * , 在所有 Java 提交中击败了
     *  * 5.40%
     *  * 的用户
     *  * 内存消耗：
     *  * 38.1 MB
     *  * , 在所有 Java 提交中击败了
     *  * 25.20%
     *  * 的用户
     * @param nums nums
     * @return int
     */
    public static int longestIncSeq(int[] nums) {
        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * NlogN
     * @param nums nums
     * @return int
     */
    public static int LIS(int[] nums) {
        int[] res = new int[nums.length];
        int resEnd = 0;
        for (int num : nums) {
            if (resEnd == 0) {
                res[resEnd] = num;
                resEnd++;
                continue;
            }
            if (num > res[resEnd - 1]) {
                res[resEnd] = num;
                resEnd++;
            } else {
                int index2Replace = binarySearch(res, num, 0, resEnd - 1);
                res[index2Replace] = num;
            }
        }
        return resEnd;
    }

    public static int binarySearch(int[] vals, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (vals[mid] == target) {
            return mid;
        }
        if (vals[mid] > target) {
            if (mid == 0 || vals[mid - 1] < target) {
                return mid;
            }
            return binarySearch(vals, target, start, mid - 1);
        }
        if (vals[mid] < target) {
            if (mid == end || vals[mid + 1] > target) {
                return mid + 1;
            }
            return binarySearch(vals, target, mid + 1, end);
        }
        return -1;
    }
}
