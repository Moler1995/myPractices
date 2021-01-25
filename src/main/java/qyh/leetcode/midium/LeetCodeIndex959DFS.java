package qyh.leetcode.midium;

/**
 * 在由 1 x 1 方格组成的 N x N 网格grid 中，每个 1 x 1方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\"表示。）。
 *
 * 返回区域的数目。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 */
public class LeetCodeIndex959DFS {
    public static void main(String[] args) {
        String[] grid = {"/\\","\\/"};
        System.out.println(regionsBySlashes(grid));
    }

    public static int regionsBySlashes(String[] grid) {
        if (grid.length == 0) {
            return 1;
        }
        int len = grid.length;
        int[][] graph = new int[len * 3][len * 3];
        buildGraph(graph, grid);
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 0) {
                    count++;
                    coloring(graph, i, j);
                }
            }
        }
        return count;
    }

    private static void coloring(int[][] graph, int startLine, int startColumn) {
        if (startColumn >= graph.length || startLine >= graph.length || startLine < 0 || startColumn < 0) {
            return;
        }
        if (graph[startLine][startColumn] == 0) {
            graph[startLine][startColumn] = 1;
            coloring(graph, startLine + 1, startColumn);
            coloring(graph, startLine, startColumn + 1);
            coloring(graph, startLine - 1, startColumn);
            coloring(graph, startLine, startColumn - 1);
        }
    }

    private static void buildGraph(int[][] graph, String[] grid) {
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            String str = grid[i];
            for (int j = 0; j < str.length(); j++) {
                int startxIndex = i * 3;
                int startyIndex = j * 3;
                if (str.charAt(j) == '/') {
                    startyIndex += 2;
                    graph[startxIndex][startyIndex] = 1;
                    graph[startxIndex + 1][startyIndex - 1] = 1;
                    graph[startxIndex + 2][startyIndex - 2] = 1;
                } else if (str.charAt(j) == '\\') {
                    graph[startxIndex][startyIndex] = 1;
                    graph[startxIndex + 1][startyIndex + 1] = 1;
                    graph[startxIndex + 2][startyIndex + 2] = 1;
                }
            }
        }
    }
}
