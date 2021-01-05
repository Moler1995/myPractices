package qyh.microsoft;

/**
 * 求一个矩阵中最大的二维矩阵(元素和最大).
 */
public class Practice20 {
    public static void main(String[] args) {
        int[][] vals = {{1, 2, 0, 3, 4}, {2, 3, 4, 5, 1}, {1, 1, 5, 3, 0}};
        int[][] a = findMax(vals);
        System.out.println("");
    }

    /**
     * 暴力
     *
     * @param matrix matrix
     * @return int
     */
    public static int[][] findMax(int[][] matrix) {
        int maxi = 0;
        int maxj = 0;
        int maxsum = 0;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (maxsum < matrix[i][j] + matrix[i][j+1] + matrix[i+1][j] + matrix[i+1][j+1]) {
                    maxi = i;
                    maxj = j;
                    maxsum = matrix[i][j] + matrix[i][j+1] + matrix[i+1][j] + matrix[i+1][j+1];
                }
            }
        }
        return new int[][] {{matrix[maxi][maxj], matrix[maxi][maxj+1]}, {matrix[maxi+1][maxj], matrix[maxi+1][maxj+1]}};
    }
}
