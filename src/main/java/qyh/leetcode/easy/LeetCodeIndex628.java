package qyh.leetcode.easy;

import java.util.*;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积
 * 输入: [1,2,3]
 * 输出: 6
 */
public class LeetCodeIndex628 {
    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -4};
        System.out.println(maxProduct(nums));
    }

    public static int maximumProduct(int[] nums) {
        List<Integer> maxPos = new ArrayList<>();
        List<Integer> minNeg = new ArrayList<>();
        List<Integer> maxNeg = new ArrayList<>();
        boolean containsZ = false;
        for (int num : nums) {
            if (num > 0) {
                replaceMin(maxPos, num);
            } else if (num < 0) {
                replaceMin(maxNeg, num);
                replaceMax(minNeg, num);
            } else {
                containsZ = true;
            }
        }
        maxPos.sort(Comparator.comparing(Integer::intValue));
        minNeg.sort(Comparator.comparing(Integer::intValue));
        maxNeg.sort(Comparator.comparing(Integer::intValue));
        if (maxPos.isEmpty()) {
            if (containsZ) {
                return 0;
            } else {
                return maxNeg.get(0) * maxNeg.get(1) * maxNeg.get(2);
            }
        } else if (maxPos.size() == 1) {
            if (minNeg.size() >= 2) {
                return maxPos.get(0) * minNeg.get(0) * minNeg.get(1);
            } else {
                return 0;
            }
        } else if (maxPos.size() == 2) {
            if (minNeg.size() >= 2) {
                return Math.max(maxPos.get(1) * minNeg.get(0) * minNeg.get(1),
                        maxPos.get(0) * minNeg.get(0) * minNeg.get(1));
            } else if (containsZ) {
                return 0;
            } else {
                return maxPos.get(0) * maxPos.get(1) * maxNeg.get(0);
            }
        } else {
            int max = maxPos.get(0) * maxPos.get(1) * maxPos.get(2);
            if (maxNeg.size() >= 2) {
                max = Math.max(maxPos.get(2) * minNeg.get(0) * minNeg.get(1), max);
            } else if (maxNeg.size() >= 1) {
                max = Math.max(maxPos.get(1) * maxPos.get(2) * minNeg.get(0), max);
            }
            return max;
        }
    }
    private static void replaceMin(List<Integer> nums, int val) {
        if (nums.size() < 3) {
            nums.add(val);
            return;
        }
        int minVal = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < nums.size(); i++) {
            if (minVal > nums.get(i)) {
                minVal = nums.get(i);
                minIndex = i;
            }
        }
        if (minIndex != -1 && nums.get(minIndex) < val) {
            nums.set(minIndex, val);
        }
    }

    private static void replaceMax(List<Integer> nums, int val) {
        if (nums.size() < 3) {
            nums.add(val);
            return;
        }
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.size(); i++) {
            if (maxVal < nums.get(i)) {
                maxVal = nums.get(i);
                maxIndex = i;
            }
        }
        if (maxIndex != -1 && nums.get(maxIndex) > val) {
            nums.add(maxIndex, val);
        }
    }

    public static int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[len - 3] * nums[len - 2] * nums[len - 1], nums[0] * nums[1] * nums[len - 1]);
    }
}
