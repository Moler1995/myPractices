package qyh.leetcode.midium;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * 锯齿形层次遍历二叉树
 *
 * @since 2021-01-06
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class LeetCodeIndex103 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4, null, null), null), new TreeNode(3, null, new TreeNode(5, null, null)));
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        List<List<Integer>> vals = new ArrayList<>();
        mapTreeNodes(nodes, 0, vals);
        System.out.println(vals);
    }

    public static void mapTreeNodes(Stack<TreeNode> roots, int lay, List<List<Integer>> vals) {
        if (roots.isEmpty()) {
            return;
        }
        Stack<TreeNode> nextNodes = new Stack<>();
        List<Integer> thisLay = new ArrayList<>();
        while (!roots.isEmpty()) {
            TreeNode node = roots.pop();
            thisLay.add(node.val);
            if (lay % 2 == 0) {
                if (node.left != null) {
                    nextNodes.push(node.left);
                }
                if (node.right != null) {
                    nextNodes.push(node.right);
                }
            } else {
                if (node.right != null) {
                    nextNodes.push(node.right);
                }
                if (node.left != null) {
                    nextNodes.push(node.left);
                }
            }
        }
        vals.add(thisLay);
        mapTreeNodes(nextNodes, lay + 1, vals);
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
