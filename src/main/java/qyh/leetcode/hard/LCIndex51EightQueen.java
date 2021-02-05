package qyh.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 八皇后问题
 */
public class LCIndex51EightQueen {
    public static void main(String[] args) {
        System.out.println(solveQueens(5));
    }

    public static List<List<String>> solveQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] column = new int[n];
            solve(column, 0, i, result);
        }
        return result;
    }

    public static void solve(int[] pointsOccupied, int row, int column, List<List<String>> result) {
        pointsOccupied[row] = column;
        if (!isValid(pointsOccupied, row, column)) {
            return;
        }
        if (row == pointsOccupied.length - 1) {
            result.add(buildResult(pointsOccupied));
            return;
        }
        for (int i = 0; i < pointsOccupied.length; i++) {
            int[] tmpChess = Arrays.copyOf(pointsOccupied, pointsOccupied.length);
            solve(tmpChess, row + 1, i, result);
        }
    }

    public static List<String> buildResult(int[] pointsOccupied) {
        List<String> result = new ArrayList<>();
        for (int column : pointsOccupied) {
            char[] chars = new char[pointsOccupied.length];
            Arrays.fill(chars, '.');
            chars[column] = 'Q';
            result.add(new String(chars));
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
