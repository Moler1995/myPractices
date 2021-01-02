package qyh.microsoft;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入一颗二元树，从上往下按层打印树的每个结点，同一层中按照从左往右的顺序打印。
 */
public class Practice10 {
    public static void main(String[] args) {
        int[] vals = {8, 6, 5, 7, 10, 9, 11};
        TreeNode root = buildTree(vals);
        getByLayer(root);
    }

    public static void getByLayer(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                nodeQueue.add(node.left);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
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
