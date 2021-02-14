package qyh.leetcode.hard;

/**
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用0到2N-1的整数表示，情侣们按顺序编号，第一对是(0, 1)，第二对是(2, 3)，以此类推，最后一对是(2N-2, 2N-1)。
 *
 * 这些情侣的初始座位row[i]是由最初始坐在第 i 个座位上的人决定的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex765 {
    public static void main(String[] args) {
        int[] row = {0, 2, 1, 3};
        System.out.println(minSwapsCouples(row));
    }

    public static int minSwapsCouples(int[] row) {
        UFDS ufds = new UFDS(row.length / 2);
        for (int i = 0; i < row.length - 1; i += 2) {
            ufds.union(row[i] / 2, row[i + 1] / 2);
        }
        return (row.length / 2) - ufds.times;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;
        int times;

        public UFDS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
            this.times = n;
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
            if (rooty == rootx) {
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
            times--;
        }
    }
}
