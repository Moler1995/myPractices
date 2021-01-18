package qyh.publicalg;

/**
 * 并查集union-find disjoint sets
 * 经典问题：亲戚问题 5000
 */
public class UFDS {
    private static int[] parent = new int[5000];
    private static int[] rank = new int[5000];
    static {
        for (int i = 0; i < 5000; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public static void main(String[] args) {

    }

    public static int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            parent[i] = find(parent[i]);
            return parent[i];
        }
    }

    public static void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rank[rootx] <= rank[rooty]) {
            parent[rootx] = rooty;
        } else {
            parent[rooty] = rootx;
        }
        if (rank[rootx] == rank[rooty] && rootx != rooty) {
            rank[rooty]++;
        }
    }
}
