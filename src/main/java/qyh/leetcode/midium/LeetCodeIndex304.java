package qyh.leetcode.midium;

/**
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 */
public class LeetCodeIndex304 {
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }

    private static class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0) {
                return;
            }
            sums = new int[matrix.length][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length + 1; j++) {
                    sums[i][j] = sums[i][j - 1] + matrix[i][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += (sums[i][col2 + 1] - sums[i][col1]);
            }
            return sum;
        }
    }

}
