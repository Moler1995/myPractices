package qyh.microsoft;

import java.util.Stack;

/**
 * 输入一颗二元查找树，将该树转换为它的镜像，即在转换后的二元查找树中，左子树的结点都大于右子树的结点。
 *
 * 用递归和循环两种方法完成树的镜像转换
 */
public class Practice09 {
    public static void main(String[] args) {
        int[] vals = {8, 6, 5, 7, 10, 9, 11};
        TreeNode root1 = buildTree(vals);
        TreeNode root = new TreeNode(8,
                new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null)),
                new TreeNode(10, new TreeNode(9, null, null), new TreeNode(11, null, null)));
        transByLoop(root);
        System.out.println("");
    }

    public static void transByRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        transByRecursion(root.left);
        transByRecursion(root.right);
    }

    public static void transByLoop(TreeNode root) {
        Stack<TreeNode> rootNodes = new Stack<>();
        if (root != null) {
            rootNodes.push(root);
        }
        while (!rootNodes.isEmpty()) {
            TreeNode node = rootNodes.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                rootNodes.push(node.left);
            }
            if (node.right != null) {
                rootNodes.push(node.right);
            }
        }
    }

    public static TreeNode buildTree(int[] vals) {
        TreeNode root = null;
        for (int val : vals) {
            root = insertNode(root, val);
        }
        return root;
    }

    public static TreeNode insertNode(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val, null, null);
        }
        if (val < node.val) {
            node.left = insertNode(node.left, val);
        } else {
            node.right = insertNode(node.right, val);
        }
        return node;
    }

    private static class TreeNode {
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
