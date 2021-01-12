package qyh.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有n 个长为m+1 的字符串，如果某个字符串的最后m 个字符与某个字符串的前m 个字符匹配，
 * 则两个字符串可以联接，问这n 个字符串最多可以连成一个多长的字符串，如果出现循环，则返回错误。
 */
public class Practice22 {
    public static void main(String[] args) {
        String[] strings = {"aaa", "abb", "bba", "bab"};
        int[][] matrix = linkStrings(strings, 2);
        Arrays.stream(matrix).forEach(ma -> System.out.println(Arrays.toString(ma)));
        System.out.println(solve(matrix));
    }

    /**
     * 思路0：构建邻接矩阵
     * @param strings 1
     * @param m x
     */
    public static int[][] linkStrings(String[] strings, int m) {
        int[][] matrix = new int[strings.length][strings.length];
        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (i == j) {
                    continue;
                }
                if (strings[i].substring(1).equals(strings[j].substring(0, m))) {
                    matrix[i][j] = 1;
                }
                if (strings[j].substring(1).equals(strings[i].substring(0, m))) {
                    matrix[j][i] = 1;
                }
            }
        }
        return matrix;
    }

    public static int solve(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            boolean[] visited = new boolean[matrix.length];
            visited[i] = true;
            int tmpMax = bfs(matrix, i, 1, visited);
            if (tmpMax == -1) {
                return -1;
            }
            max = Math.max(tmpMax, max);
        }
        return max;
    }

    private static int bfs(int[][] matrix, int from, int max, boolean[] visited) {
        List<Integer> nextHops = findNextHops(matrix, from);
        if (nextHops.isEmpty()) {
            return max;
        }
        int currMax = 0;
        max += 1;

        for (int nextHop : nextHops) {
            if (visited[nextHop]) {
                return -1;
            }
            boolean[] visit = visited.clone();
            visit[nextHop] = true;
            System.out.println(Arrays.toString(visit));
            int tmpMax = bfs(matrix, nextHop, max, visit);
            if (tmpMax == -1) {
                return -1;
            }
            currMax = Math.max(tmpMax, currMax);
        }
        return Math.max(currMax, max);
    }

    private static List<Integer> findNextHops(int[][] matrix, int from) {
        List<Integer> nextHops = new ArrayList<>();
        for (int j = 0; j < matrix.length; j++) {
            if (j == from) {
                continue;
            }
            if (matrix[from][j] > 0) {
                nextHops.add(j);
            }
        }
        return nextHops;
    }
}
