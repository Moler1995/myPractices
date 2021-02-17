package qyh.leetcode.easy;

import java.util.Arrays;

/**
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 *
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 *
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 *
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reshape-the-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex566 {
    public static void main(String[] args) {
        int[][] nums = {{1, 2, 3, 4}};
        int[][] res = matrixReshape(nums, 2, 2);
        Arrays.stream(res).forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    private static int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums[0].length == 0) {
            return nums;
        }
        int rrow = nums.length;
        int rcolumn = nums[0].length;
        int size = rrow * rcolumn;
        if (r * c != size) {
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r; i++) {
            int baseIndex = i * c;
            for (int j = 0; j < c; j++) {
                int index = baseIndex + j;
                int tIndex = index / rcolumn;
                int tColumn = index % rcolumn;
                result[i][j] = nums[tIndex][tColumn];
            }
        }
        return result;
    }
}
