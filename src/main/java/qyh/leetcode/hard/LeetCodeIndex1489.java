package qyh.leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个 n个点的带权无向连通图，节点编号为 0到 n-1，同时还有一个数组 edges，其中 edges[i] = [fromi, toi, weighti]
 * 表示在fromi和toi节点之间有一条带权无向边。最小生成树(MST) 是给定图中边的一个子集，它连接了所有节点且没有环，
 * 而且这些边的权值和最小。
 *
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，
 * 会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在
 * 某些最小生成树中但不会出现在所有最小生成树中的边。
 *
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1489 {
    public static void main(String[] args) {
        int[][] edges = {{0,1,13},{0,2,6},{2,3,13},{3,4,4},{0,5,11},{4,6,14},{4,7,8},{2,8,6},{4,9,6},
                {7,10,4},{5,11,3},{6,12,7},{12,13,9},{7,13,2},{5,13,10},{0,6,4},{2,7,3},{0,7,8},{1,12,9},
                {10,12,11},{1,2,7},{1,3,10},{3,10,6},{6,10,4}, {4,8,5},{1,13,4},{11,13,8},{2,12,10},
                {5,8,1},{3,7,6},{7,12,12},{1,7,9},{5,9,1}, {2,13,10},{10,11,4},{3,5,10},{6,11,14},
                {5,12,3},{0,8,13},{8,9,1},{3,6,8},{0,3,4},{2,9,6},{0,11,4},{2,5,14},{4,11,2},{7,11,11},
                {1,11,6},{2,10,12},{0,13,4},{3,9,9},{4,12,3},{6,7,10},{6,8,13},{9,11,3},{1,6,2},
                {2,4,12},{0,10,3},{3,12,1},{3,8,12},{1,8,6},{8,13,2},{10,13,12},{9,13,11},{2,11,14},{5,10,9},
                {5,6,10},{2,6,9},{4,10,7},{3,13,10},{4,13,3},{3,11,9},{7,9,14},{6,9,5},{1,5,12},
                {4,5,3},{11,12,3},{0,4,8},{5,7,8},{9,12,13},{8,12,12},{1,10,6},{1,9,9},{7,8,9},
                {9,10,13},{8,11,3},{6,13,7},{0,12,10},{1,4,8},{8,10,2}};
        System.out.println(findCriticalAndPseudoCriticalEdges(14, edges));
    }

    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 1. 按权值排序
        Set<Edge> edgeSet = new TreeSet<>((e1, e2) -> {
            if (e1.weight != e2.weight) {
                return e1.weight - e2.weight;
            } else {
                return e1.edgeIndex - e2.edgeIndex;
            }
        });
        for (int i = 0; i < edges.length; i++) {
            Edge e = new Edge(edges[i][0], edges[i][1], edges[i][2], i);
            edgeSet.add(e);
        }
        UDFS udfs = new UDFS(n);
        int minTotalWeight = 0;
        // 存储一个生成树的边的解集
        Set<Integer> oneSolution = new HashSet<>();
        // 这里存储所有非关键边
        Set<Integer> pseudoEdgeIndexList = new HashSet<>();
        int nodeConnected = 1;
        // 先找出一个最小生成树
        for (Edge edge : edgeSet) {
            if (nodeConnected == n) {
                pseudoEdgeIndexList.add(edge.edgeIndex);
            }
            if (udfs.union(edge.from, edge.to)) {
                minTotalWeight += edge.weight;
                oneSolution.add(edge.edgeIndex);
                nodeConnected++;
            } else {
                pseudoEdgeIndexList.add(edge.edgeIndex);
            }
        }
        Set<Integer> pseudoEdgeResult = new HashSet<>();
        // 然后一条边一条边地删...
        for (int index1 : oneSolution) {
            udfs = new UDFS(n);
            int totalWeight = 0;
            // 除了待删除的一条边，把其他边连起来，算权值和，和构建并查集
            for (int index2 : oneSolution) {
                if (index2 == index1) {
                    continue;
                }
                int[] oneEdge = edges[index2];
                udfs.union(oneEdge[0], oneEdge[1]);
                totalWeight += oneEdge[2];
            }
            // 存储当前并查集状态
            int[] tmpParent = Arrays.copyOf(udfs.parent, udfs.parent.length);
            int[] tmpRank  = Arrays.copyOf(udfs.rank, udfs.rank.length);
            // 在剩下的边里找一条能构成最小生成树的边，如果找到了就说明待删除的边不是关键边
            for (Integer integer : pseudoEdgeIndexList) {
                udfs.parent = Arrays.copyOf(tmpParent, udfs.parent.length);
                udfs.rank = Arrays.copyOf(tmpRank, udfs.rank.length);
                int[] oneEdge = edges[integer];
                if (udfs.union(oneEdge[0], oneEdge[1])) {
                    if (minTotalWeight >= totalWeight + oneEdge[2]) {
                        pseudoEdgeResult.add(integer);
                        pseudoEdgeResult.add(index1);
                    }
                }
            }
        }
        pseudoEdgeResult.forEach(oneSolution::remove);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(oneSolution).stream().sorted().collect(Collectors.toList()));
        result.add(new ArrayList<>(pseudoEdgeResult).stream().sorted().collect(Collectors.toList()));
        return result;
    }

    private static class Edge {
        int weight;
        int from;
        int to;
        int edgeIndex;

        public Edge(int from, int to, int weight, int edgeIndex) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            this.edgeIndex = edgeIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                return ((Edge) obj).weight == this.weight && ((Edge) obj).from == this.from
                        && ((Edge) obj).to == this.to && ((Edge) obj).edgeIndex == this.edgeIndex;
            }
            return false;
        }
    }

    private static class UDFS {
        private int[] parent;
        private int[] rank;

        public UDFS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                rank[i] = 1;
                parent[i] = i;
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

        public boolean union(int from, int to) {
            int rootx = find(from);
            int rooty = find(to);
            if (rooty == rootx) {
                return false;
            }
            if (rank[rootx] <= rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
            }
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
            return true;
        }
    }
}
