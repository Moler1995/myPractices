package qyh.leetcode.midium;

/**
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 */
public class LeetCodeIndex1004 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println(longestOnes(nums, 3));
    }

    private static int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int len = A.length;
        int k = 0;
        int res = 0;
        int tmp = res;
        while (right < len) {
            if (A[right] == 0) {
                if (k == K) {
                    while (A[left] != 0 && left <= right) {
                        left++;
                        tmp--;
                    }
                    left++;
                    tmp--;
                } else {
                    k++;
                }
            }
            right++;
            tmp++;
            res = Math.max(res, tmp);
        }
        return res;
    }
}
