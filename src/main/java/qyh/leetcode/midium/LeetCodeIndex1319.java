package qyh.leetcode.midium;

/**
 * 用以太网线缆将n台计算机连接成一个网络，计算机的编号从0到n-1。线缆用connections表示，其中connections[i] = [a, b]连接了计算机a和b。
 *
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 *
 * 给你这个计算机网络的初始布线connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回-1 。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
 */
public class LeetCodeIndex1319 {
    public static void main(String[] args) {
        int[][] connections = {{1,5},{1,7},{1,2},{1,4},{3,7},{4,7},{3,5},{0,6},{0,1},{0,4},{2,6},{0,3},{0,2}};
        System.out.println(makeConnected(12, connections));
    }

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length  < n - 1) {
            return -1;
        }
        UFDS ufds = new UFDS(n);
        for (int[] connection : connections) {
            ufds.union(connection[0], connection[1]);
        }
        return ufds.count - 1;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;
        int count;

        public UFDS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
            this.count = n;
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

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return;
            }
            if (rank[rootx] <= rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
            }
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
            --this.count;
        }
    }
}
