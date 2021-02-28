package qyh.leetcode.easy;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A是单调数组时返回 true，否则返回 false。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex896 {
    public static void main(String[] args) {
        int[] A = {1,1,2};
        System.out.println(isMonotonic(A));

    }

    private static boolean isMonotonic(int[] A) {
        if (A.length == 2) {
            return true;
        }
        boolean isMonoInc = true;
        boolean determined = false;
        int left = 0;
        int right = 1;
        while (right < A.length) {
            if (A[right] - A[left] != 0) {
                if (!determined) {
                    isMonoInc = A[right] - A[left] > 0;
                    determined = true;
                } else {
                    if (isMonoInc && A[right] - A[left] < 0 || !isMonoInc && A[right] - A[left] > 0) {
                        return false;
                    }
                }
            }
            left++;
            right++;
        }
        return true;
    }
}
