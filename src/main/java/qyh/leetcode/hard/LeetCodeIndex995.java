package qyh.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，
 * 同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 *
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex995 {
    public static void main(String[] args) {
        int[] nums = {0,0,0,1,0,1,1,0};
        System.out.println(minKBitFlips1(nums, 3));
    }

    /**
     * 超时
     * @param A A
     * @param K K
     * @return int
     */
    private static int minKBitFlips(int[] A, int K) {
        int count = 0;
        int left = 0;
        int right = K - 1;
        while (right < A.length) {
            boolean flag = false;
            if (A[left] == 0) {
                flag = true;
                int tmpR = right;
                int tmpL = left;
                boolean flag1 = true;
                while (tmpL <= tmpR) {
                    if (A[tmpL] == 1) {
                        flag1 = false;
                    } else if (flag1) {
                        left++;
                        right++;
                    }
                    A[tmpL] = A[tmpL] == 1 ? 0 : 1;
                    tmpL++;
                }
            } else {
                left++;
                right++;
            }
            if (flag) {
                count++;
            }
        }
        return A[A.length - 1] == 0 || (left < A.length && A[left] == 0) ? -1 : count;
    }

    private static int minKBitFlips1(int[] A, int K) {
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if (!queue.isEmpty() && queue.peek() + K <= i) {
                queue.poll();
            }
            if (queue.size() % 2 == A[i]) {
                if (i + K > A.length) {
                    return -1;
                }
                queue.add(i);
                count++;
            }
        }
        return count;
    }

}
