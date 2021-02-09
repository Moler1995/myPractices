package qyh.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个正整数数组 A，如果 A的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 *
 * （例如，[1,2,3,1,2] 中有3个不同的整数：1，2，以及3。）
 *
 * 返回A中好子数组的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex992 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 3};
        System.out.println(subarraysWithKDistinct(arr, 2));
    }

    public static int subarraysWithKDistinct(int[] A, int K) {
        int count = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int tmpK = 0;
        for (int i = 0; i < A.length; i++) {
            if (!numCountMap.containsKey(A[i])) {
                numCountMap.put(A[i], 1);
                tmpK++;
            }
        }
        return count;
    }
}
