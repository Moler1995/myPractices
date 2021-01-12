package qyh.microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 递归和非递归俩种方法实现二叉树的前序遍历
 */
public class Practice26 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4),
                new TreeNode(5)), new TreeNode(6)), new TreeNode(7));
        List<Integer> res = new ArrayList<>();
        cyclic(root, res);
        System.out.println(res);
    }

    public static void recursive(TreeNode root, List<Integer> preOrder) {
        if (root == null) {
            return;
        }
        preOrder.add(root.val);
        if (root.left != null) {
            recursive(root.left, preOrder);
        }
        if (root.right != null) {
            recursive(root.right, preOrder);
        }
    }

    public static void cyclic(TreeNode root, List<Integer> preOrder) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        while (true) {
            if (root == null) {
                break;
            }
            preOrder.add(root.val);
            if (root.left == null && root.right == null) {
                if (treeNodeStack.isEmpty()) {
                    break;
                }
                root = treeNodeStack.pop();
            }
            if (root.left != null) {
                treeNodeStack.push(root.right);
                root = root.left;
            }
        }
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

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
