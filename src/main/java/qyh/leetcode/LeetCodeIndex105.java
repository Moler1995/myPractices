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
        TreeNode root = buildTree(pre, in, 0, pre.length - 1, 0, pre.length - 1);
        System.out.println(root);
    }

    private static TreeNode buildTree(int[] preOrder, int[] inOrder, int startPre, int endPre,
                                      int startIn, int endIn) {
        if (startPre > endPre) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[startPre]);
        if (startPre == endPre) {
            return root;
        }
        int rootIndexIn = findIndex(inOrder, preOrder[startPre], startIn, endIn);
        int leftLen = rootIndexIn - startIn;
        int rightLen = endIn - rootIndexIn;
        if (leftLen > 0) {
            root.left = buildTree(preOrder, inOrder, startPre + 1, startPre + leftLen, startIn, rootIndexIn - 1);
        }
        if (rightLen > 0) {
            root.right = buildTree(preOrder, inOrder, startPre + leftLen + 1, endPre, rootIndexIn + 1, endIn);
        }
        return root;
    }

    private static int findIndex(int[] input, int target, int start, int end) {
        for (int i = start; i <= end; i++) {
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
