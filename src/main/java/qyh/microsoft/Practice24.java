package qyh.microsoft;

import java.util.List;

/**
 * 求一个有向连通图的割点，割点的定义是，如果除去此节点和与其相关的边，有向图不再连通，描述算法。
 */
public class Practice24 {
    public static void main(String[] args) {

    }

    public static int[] findCurPoint(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {

        }
        return null;
    }

    private static TreeNode buildTree(int[][] graph) {
        TreeNode root = null;
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0) {
                    visited[j] = true;
                    root = new TreeNode(i);
                }
            }
            visited[i] = true;
        }
        return root;
    }

    private static class TreeNode {
        public int node;
        public List<TreeNode> children;
        public TreeNode(int node) {
            this.node = node;
        }
    }
}
