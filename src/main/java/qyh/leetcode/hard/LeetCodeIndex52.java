package qyh.leetcode.hard;

import java.util.Arrays;

public class LeetCodeIndex52 {
    public static void main(String[] args) {
        System.out.println(solveQueens(4));
    }

    public static int solveQueens(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            int[] column = new int[n];
            result += solve(column, 0, i);
        }
        return result;
    }

    public static int solve(int[] pointsOccupied, int row, int column) {
        pointsOccupied[row] = column;
        if (!isValid(pointsOccupied, row, column)) {
            return 0;
        }
        if (row == pointsOccupied.length - 1) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < pointsOccupied.length; i++) {
            int[] tmpChess = Arrays.copyOf(pointsOccupied, pointsOccupied.length);
            result += solve(tmpChess, row + 1, i);
        }
        return result;
    }

    private static boolean isValid(int[] pointsOccupied, int row, int column) {
        for (int i = 0; i < row; i++) {
            int column1 = pointsOccupied[i];
            if (column1 == column) {
                return false;
            }
            if (Math.abs(row - i) == Math.abs(column - column1)) {
                return false;
            }
        }
        return true;
    }
}
