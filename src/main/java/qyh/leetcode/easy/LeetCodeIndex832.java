package qyh.leetcode.easy;

import java.util.Arrays;

public class LeetCodeIndex832 {
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1,0,1}, {0,0,0}};
        flipAndInvertImage(arr);
        Arrays.stream(arr).forEach(a -> System.out.println(Arrays.toString(a)));
    }

    private static int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int len = A[i].length;
            for (int j = 0; j <= len / 2; j++) {
                if (len % 2 == 1 && j == len / 2) {
                    A[i][j] = - A[i][j] + 1;
                } else if (j != len / 2){
                    int tmp = A[i][j];
                    A[i][j] =  - A[i][len - 1 - j] + 1;
                    A[i][len - 1 - j] = - tmp + 1;
                }
            }
        }
        return A;
    }
}
