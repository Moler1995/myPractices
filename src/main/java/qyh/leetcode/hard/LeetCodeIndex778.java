package qyh.leetcode.hard;

import java.util.Set;
import java.util.TreeSet;

/**
 * 在一个 N x N 的坐标方格grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 *
 * 现在开始下雨了。当时间为t时，此时雨水导致水池中任意位置的水位为t。你可以从一个平台游向四周相邻的任意一个平台，
 * 但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 *
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台(N-1, N-1)？
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swim-in-rising-water
 */
public class LeetCodeIndex778 {
    public static void main(String[] args) {
        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(swimInWater(grid));
    }

    public static int swimInWater(int[][] grid) {
        if (grid.length == 0 || grid.length == 1) {
            return 0;
        }
        Set<Edge> edgeTreeSet = new TreeSet<>((e1, e2) -> {
            if (e1.len != e2.len) {
                return e1.len - e2.len;
            } else if (e1.from != e2.from) {
                return e1.from - e2.from;
            } else {
                return e1.to - e2.to;
            }
        });
        int rows = grid.length;
        int columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int from = columns * i + j;
                int to1 = columns * i + j + 1;
                int to2 = columns * (i + 1) + j;
                if (j + 1 < columns) {
                    int len = Math.max(grid[i][j + 1], grid[i][j]);
                    Edge edge = new Edge(from, to1, len);
                    edgeTreeSet.add(edge);
                }
                if (i + 1 < rows) {
                    int len = Math.max(grid[i + 1][j], grid[i][j]);
                    Edge edge = new Edge(from, to2, len);
                    edgeTreeSet.add(edge);
                }
            }
        }
        UFDS ufds = new UFDS(rows * columns);
        for (Edge edge : edgeTreeSet) {
            ufds.union(edge.from, edge.to);
            if (ufds.find(0) == ufds.find(rows * columns - 1)) {
                return edge.len;
            }
        }
        return -1;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;

        public UFDS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                int rooti = find(parent[i]);
                parent[i] = rooti;
                return rooti;
            }
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return;
            }
            if (rank[rootx] >= rank[rooty]) {
                parent[rooty] = rootx;
            } else {
                parent[rootx] = rooty;
            }
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
        }
    }

    private static class Edge {
        int from;
        int to;
        int len;

        public Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                return ((Edge) obj).from == this.from && ((Edge) obj).to == this.to && ((Edge) obj).len == this.len;
            }
            return false;
        }
    }
}
