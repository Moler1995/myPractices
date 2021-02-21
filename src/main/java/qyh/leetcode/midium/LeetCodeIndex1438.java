package qyh.leetcode.midium;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1438 {
    public static void main(String[] args) {
        int[] nums = {1,5,6,7,8,10,6,5,6};
        System.out.println(longestSubarray(nums, 4));
    }

    private static int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int res = 0;
        return 0;
    }
}
