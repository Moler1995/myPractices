package qyh.microsoft;

import java.util.Arrays;

/**
 * 输入一个已经按升序排序过的数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。
 *
 * 要求时间复杂度是O(n)。如果有多对数字的和等于输入的数字，输出任意一对即可。
 */
public class Practice08 {
    public static void main(String[] args) {
        int[] vals = {1, 2, 3, 4, 5, 6, 7, 8};
        int [] res = findSum(vals, 5);
        if (res != null) {
            System.out.println(Arrays.toString(res));
        }

    }

    public static int[] findSum(int[] vals, int target) {
        int front = 0;
        int back = vals.length - 1;
        while (true) {
            if (vals[front] + vals[back] > target) {
                back--;
            } else if (vals[front] + vals[back] < target) {
                front++;
            } else if (front >= back) {
                return null;
            } else {
                return new int[]{front, back};
            }
        }
    }

    /**
     * xxxxx nlogn
     * @param vals vals
     * @param target target
     * @return int[]
     */
    public static int[] findTargetSum(int[] vals, int target) {
         for (int i = 0; i < vals.length; i++) {
             if (vals[i] > target) {
                 continue;
             }
             int t1 = target - vals[i];
             int index = binarySearch(vals, t1, i + 1, vals.length - 1);
             if (index != -1) {
                 return new int[]{i, index};
             }
         }
         return null;
    }

    public static int binarySearch(int[] vals, int target, int begin, int end) {
        if (begin >= end && vals[begin] != target && vals[end] != target) {
            return -1;
        }
        int mid = (begin + end) / 2;
        if (target == vals[mid]) {
            return mid;
        }
        if (vals[mid] > target) {
            return binarySearch(vals, target, begin, mid - 1);
        } else {
            return binarySearch(vals, target, mid + 1, end);
        }
    }
}
