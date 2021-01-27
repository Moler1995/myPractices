package qyh.leetcode.hard;

import java.util.TreeSet;

/**
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3种类型的边：
 *
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob
 * 都可以到达所有其他节点，则认为图是可以完全遍历的。
 *
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable
 */
public class LeetCodeIndex1579 {
    public static void main(String[] args) {
        int[][] edges = {{3,2,3},{1,1,2},{2,3,4}};
        System.out.println(maxNumEdgesToRemove(4, edges));
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        UFDS ufdsa = new UFDS(n);
        UFDS ufdsb = new UFDS(n);
        TreeSet<Edge> edgeTreeSet = new TreeSet<>((e1, e2) -> {
            if (e1.type != e2.type) {
                return e2.type - e1.type;
            } else if (e1.from != e2.from){
                return e1.from - e2.from;
            } else {
                return e1.to - e2.to;
            }
        });
        for (int[] edge : edges) {
            Edge edge1 = new Edge(edge[0], edge[1], edge[2]);
            edgeTreeSet.add(edge1);
        }
        int count = 0;
        int edgeCounta = 0;
        int edgeCountb = 0;
        for (Edge edge : edgeTreeSet) {
            if (edge.type == 3) {
                boolean uniona = ufdsa.union(edge.from, edge.to);
                boolean unionb = ufdsb.union(edge.from, edge.to);
                if (!unionb && !uniona) {
                    count++;
                } else {
                    if (uniona) {
                        edgeCounta++;
                    }
                    if (unionb) {
                        edgeCountb++;
                    }
                }
            } else if (edge.type == 2) {

                if (!ufdsb.union(edge.from, edge.to)) {
                    count++;
                } else {
                    edgeCountb++;
                }
            } else {
                if (!ufdsa.union(edge.from, edge.to)) {
                    count++;
                } else {
                    edgeCounta++;
                }
            }
        }
        if (edgeCounta < n - 1 || edgeCountb < n - 1) {
            return -1;
        }
        return count;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;

        UFDS(int n) {
            this.parent = new int[n + 1];
            this.rank = new int[n + 1];
            for (int i = 0; i < n; i++) {
                parent[i + 1] = i + 1;
                rank[i + 1] = 1;
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
        int type;
        int from;
        int to;

        Edge(int type, int from, int to) {
            this.type = type;
            this.from = from;
            this.to = to;
        }
    }
}
