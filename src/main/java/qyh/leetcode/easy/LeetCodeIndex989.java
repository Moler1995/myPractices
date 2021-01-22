package qyh.leetcode.easy;

import java.util.*;

/**
 * 对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 */
public class LeetCodeIndex989 {
    public static void main(String[] args) {
        int[] A = {1, 2, 0, 0};
        int K = 9400;
        System.out.println(addToArrayForm(A, K));
    }

    public static List<Integer> addToArrayForm(int[] A, int K) {
        Stack<Integer> stack = new Stack<>();
        int toAdd = 0;
        int index = A.length - 1;
        while (K > 0 || index >= 0 || toAdd > 0) {
            int a = 0;
            int k = 0;
            if (index >= 0) {
                a = A[index];
            }
            if (K > 0) {
                k = K % 10;
                K = K / 10;
            }
            stack.push((a + k + toAdd) % 10);
            toAdd = (a + k + toAdd) / 10;
            index--;
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
}
