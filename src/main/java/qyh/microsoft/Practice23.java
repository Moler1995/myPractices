package qyh.microsoft;

/**
 * 求一个二叉树中任意两个节点间的最大距离，
 * 两个节点的距离的定义是这两个节点间边的个数，
 * 比如某个孩子节点和父节点间的距离是1，
 * 和相邻兄弟节点间的距离是2，
 * 优化时间空间复杂度。
 *         1
 *        /\
 *       2 7
 *      /\
 *     3 6
 *    /\
 *   4  5
 */
public class Practice23 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4),
                new TreeNode(5)), new TreeNode(6)), new TreeNode(7));
        maxDistance(root);
        System.out.println(root.max_length);
    }

    public static int maxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            // 更新左子树高度
            root.l_depth = maxDistance(root.left);
            // 更新当前最大值
            root.max_length = Math.max(root.max_length, root.left.max_length);
        }
        if (root.right != null) {
            root.r_depth = maxDistance(root.right);
            root.max_length = Math.max(root.max_length, root.right.max_length);
        }
        root.max_length = Math.max(root.max_length, root.l_depth + root.r_depth);
        return Math.max(root.r_depth, root.l_depth) + 1;
    }

    private static class TreeNode {
        public int max_length = 0;
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
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
