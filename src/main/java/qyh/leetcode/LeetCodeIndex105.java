package qyh.leetcode;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *  preorder = [3,9,20,15,7]
 *  inorder = [9,3,15,20,7]
 *      3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class LeetCodeIndex105 {
    public static void main(String[] args) {
        int[] pre = {1, 2};
        int[] in = {1, 2};
        TreeNode root = buildTree(pre, in, 0, pre.length - 1);
        System.out.println(root);
    }

    private static TreeNode buildTree(int[] preOrder, int[] inOrder, int start, int end) {
        if (start >= preOrder.length || end >=  preOrder.length) {
            return null;
        }
        TreeNode node = new TreeNode(preOrder[start]);
        if (start == end) {
            return node;
        }
        int index = findIndex(inOrder, preOrder[start]);
        if (index > 0) {
            int leftEnd = inOrder[index - 1];
            int left = findIndex(preOrder, leftEnd);
            node.left = buildTree(preOrder, inOrder, start + 1, left);
        }
        if (index < preOrder.length - 1) {
            int rightStart = inOrder[index + 1];
            int right = findIndex(preOrder, rightStart);
            node.right = buildTree(preOrder, inOrder, right, end);
        }
        return node;
    }

    private static int findIndex(int[] input, int target) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
