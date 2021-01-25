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
public class LeetCodeIndex959UFDS {
    public static void main(String[] args) {
        String[] grid = {"/\\","\\/"};
        System.out.println(regionsBySlashes(grid));
    }

    public static int regionsBySlashes(String[] grid) {
        if (grid.length <= 0) {
            return 1;
        }
        UFDS ufds = new UFDS(grid.length);
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            String str = grid[i];
            for (int j = 0; j < str.length(); j++) {
                int z = 4 * (i * len + j);
                int f = z + 1;
                int s = z + 2;
                int t = z + 3;
                if (str.charAt(j) == '/') {
                    ufds.union(z, f);
                    ufds.union(s, t);

                } else if (str.charAt(j) == '\\') {
                    ufds.union(z, t);
                    ufds.union(f, s);
                } else {
                    ufds.union(z, f);
                    ufds.union(f, s);
                    ufds.union(s, t);
                }
                if (j != str.length() - 1) {
                    ufds.union(s, s + 2);
                }
                if (i != len - 1) {
                    ufds.union(t, 4 * ((i + 1) * len + j) + 1);
                }
            }
        }
        return ufds.count;
    }

    private static class UFDS {
        private int[] parent;
        private int[] rank;
        int count;

        public UFDS(int n) {
            this.count = 4 * n * n;
            this.parent = new int[this.count];
            this.rank = new int[this.count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
                rank[i] = 1;
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
            count--;
        }
    }
}
