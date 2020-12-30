package qyh.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * from https://www.jianshu.com/p/62fc0bdeb71d
 *      https://www.jianshu.com/p/6db5e1ae81d1
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 */
public class Practice01 {
    public static void main(String[] args) {
        int[] vals = {56, 45, 47, 67, 35, 76, 22, 89, 91, 27, 11, 21, 19, 87};
        TreeNode root = buildTree(vals);
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        TreeNode node = convert(nodes);
        while (node.right != null) {
            System.out.println(node.value);
            node = node.right;
        }
    }

    public static TreeNode convert(List<TreeNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (i == 0) {
                nodes.get(i).left = null;
                nodes.get((i)).right = nodes.get(i + 1);
            } else if (i == nodes.size() - 1) {
                nodes.get(i).left = nodes.get(i - 1);
                nodes.get(i).right = null;
            } else {
                nodes.get(i).left = nodes.get(i - 1);
                nodes.get(i).right = nodes.get(i + 1);
            }
        }
        return nodes.get(0);
    }

    public static void inorder(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder(root.left, nodes);
        }
        // System.out.println(root.value);
        nodes.add(root);
        if (root.right != null) {
            inorder(root.right, nodes);
        }
    }

    public static TreeNode buildTree(int[] vals) {
        TreeNode root = null;
        for (int val : vals) {
            root = insert(root, val);
        }
        return root;
    }

    public static TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val, null, null);
        }
        if (val <= node.value) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    private static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
