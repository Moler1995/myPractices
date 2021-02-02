package qyh.leetcode.midium;

import java.util.*;

/**
 * udfs
 */
public class LeetCodeIndex1584UFDS {
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(minCostConnectPoints(points));
    }

    public static int minCostConnectPoints(int[][] points) {
        Set<Edge> edges = new TreeSet<>((o1, o2) -> {
            if (o1.len != o2.len) {
                return o1.len  - o2.len;
            } else if (o1.x != o2.x) {
                return o1.x - o2.x;
            } else {
                return o1.y - o2.y;
            }
        });
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int len = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                Edge edge = new Edge(i, j, len);
                edges.add(edge);
            }
        }
        DisjointSetUnion dsu = new DisjointSetUnion(points.length);
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == points.length) {
                    break;
                }
            }
        }
        return ret;
    }

    private static class DisjointSetUnion {
        int[] parent;
        int[] rank;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                int root = find(parent[i]);
                parent[i] = root;
                return root;
            }
        }

        public boolean unionSet(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (rank[fx] >= rank[fy]) {
                parent[fy] = fx;
            } else {
                parent[fx] = fy;
            }
            if (rank[fx] == rank[fy]) {
                rank[fx]++;
            }
            return true;
        }
    }

    private static class Edge {
        int len;
        int x;
        int y;

        public Edge(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
}
