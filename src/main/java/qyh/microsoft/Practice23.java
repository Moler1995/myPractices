package qyh.microsoft;

/**
 * 求一个二叉树中任意两个节点间的最大距离，
 * 两个节点的距离的定义是这两个节点间边的个数，
 * 比如某个孩子节点和父节点间的距离是1，
 * 和相邻兄弟节点间的距离是2，
 * 优化时间空间复杂度。
 */
public class Practice23 {
    public static void main(String[] args) {

    }

    public static int maxDistance(TreeNode root) {
        return 0;
    }

    private static class TreeNode {
        public int max_length = 1;
        public int l_depth = 0;
        public int r_depth = 0;
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
