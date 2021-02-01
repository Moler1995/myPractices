package qyh.leetcode.easy;

import java.util.Arrays;

/**
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 *
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 *
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1]是 Bob 必须交换的糖果棒的大小。
 *
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fair-candy-swap
 */
public class LeetCodeIndex888 {
    public static void main(String[] args) {
        int[] A = {2};
        int[] B = {1, 3};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));
    }

    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int diff = (sumA - sumB) / 2;
        Arrays.sort(A);
        for (int target : B) {
            int searchedIndex = Arrays.binarySearch(A, target + diff);
            if (searchedIndex >= 0) {
                return new int[]{A[searchedIndex], target};
            }
        }
        return null;
    }
}
