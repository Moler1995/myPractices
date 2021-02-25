package qyh.leetcode.easy;

import java.util.Arrays;

/**
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 *
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 */
public class LeetCodeIndex867 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3} , {4, 5, 6}};
        int[][] res = transpose(matrix);
        Arrays.stream(res).forEach(m -> System.out.println(Arrays.toString(m)));
    }

    private static int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] result = new int[column][row];
        int bound = Math.min(row, column);
        for (int i = 0; i < bound; i++) {
            for (int j = i; j < bound; j++) {
                result[i][j] = matrix[j][i];
                result[j][i] = matrix[i][j];
            }
        }
        if (bound < row) {
            for (int i = bound; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    result[j][i] = matrix[i][j];
                }
            }
        } else if (bound < column) {
            for (int i = bound; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    result[i][j] = matrix[j][i];
                }
            }
        }
        return result;
    }
}
