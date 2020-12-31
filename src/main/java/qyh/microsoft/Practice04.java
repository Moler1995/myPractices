package qyh.microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个整数和一棵二元树，从树的根结点开始往下访问一直到叶结点所经过的所有结点形成一条路径。
 *
 * 打印出和与输入整数相等的所有路径。
 */
public class Practice04 {
    private static final List<Stack<Integer>> PATHS = new ArrayList<>();
    public static void main(String[] args) {
        int[] vals = {10, 5, 4, 7, 12};
        TreeNode node = buildTree(vals);
        findAllPaths(19, 0, node, new Stack<>());
        System.out.println("");
    }

    public static void findAllPaths(int target, int currSum, TreeNode node, Stack<Integer> path) {
        if (node == null) {
            return;
        }
        currSum += node.val;
        if (node.left == null && node.right == null) {
            if (currSum == target) {
                path.push(node.val);
                PATHS.add(path);
            }
            return;
        }
        if (currSum > target) {
            return;
        }
        path.push(node.val);
        if (node.left != null) {
            Stack<Integer> tmpPath = (Stack) path.clone();
            findAllPaths(target, currSum, node.left, tmpPath);
        }
        if (node.right != null) {
            Stack<Integer> tmpPath = (Stack) path.clone();
            findAllPaths(target, currSum, node.right, tmpPath);
        }
    }


    public static TreeNode buildTree(int[] vals) {
        TreeNode node = null;
        for (int val : vals) {
            node = insert(node, val);
        }
        return node;
    }

    public static TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val, null, null);
        } else {
            if (node.val > val) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
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
