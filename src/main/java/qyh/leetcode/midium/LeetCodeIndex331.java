package qyh.leetcode.midium;

import java.util.Stack;

/**
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 */
public class LeetCodeIndex331 {
    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    /**
     * 不对
     * @param preorder preorder
     * @return boolean
     */
    public static boolean isValidSerialization(String preorder) {
        Stack<Integer> helpStack = new Stack<>();
        for (char c : preorder.toCharArray()) {
            if (c == ',') {
                continue;
            }
            if (c == '#') {
                helpStack.push(helpStack.pop() - 1);
            } else {
                helpStack.push(helpStack.pop() - 1);
                helpStack.push(2);
            }
            while (!helpStack.isEmpty() && helpStack.peek() == 0) {
                helpStack.pop();
            }
        }
        return helpStack.isEmpty();
    }
}
