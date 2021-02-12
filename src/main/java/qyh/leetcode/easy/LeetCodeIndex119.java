package qyh.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 */
public class LeetCodeIndex119 {
    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    private static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int[][] arr = new int[rowIndex + 1][rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= i; j++) {

                if (i == 0 || j == 0 || j == i) {
                    if (i == rowIndex) {
                        result.add(1);
                    }
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i][i - j] = arr[i - 1][j - 1] + arr[i - 1][j];
                    if (i == rowIndex) {
                        result.add(arr[i][j]);
                    }
                }
            }
        }
        return result;
    }
}