package qyh.leetcode.midium;

import java.util.Set;
import java.util.TreeSet;

/**
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，
 * 其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，
 * 且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 *
 * 请你返回从左上角走到右下角的最小体力消耗值。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 */
public class LeetCodeIndex1631 {
    public static void main(String[] args) {

    }

    public static int minimumEffortPath(int[][] heights) {
        if (heights.length == 0) {
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
        return 0;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;

        public UFDS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
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

        public boolean union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return false;
            }
            if (rank[rootx] >= rank[rooty]) {
                parent[rooty] = rootx;
            } else {
                parent[rootx] = rooty;
            }
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
            return true;
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
    }
}
