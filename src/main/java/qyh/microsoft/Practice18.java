package qyh.microsoft;

import java.util.Stack;

/**
 * 输入两个整数序列。其中一个序列表示栈的push 顺序，判断另一个序列有没有可能是对应的pop 顺序
 */
public class Practice18 {
    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 3, 5, 1, 2};
        System.out.println(validPopOrder(push, pop));
    }

    public static boolean validPopOrder(int[] push, int[] pop) {
        Stack<Integer> pushStack = new Stack<>();
        pushStack.push(push[0]);
        int i = 1;
        int j = 0;
        while (j < pop.length)
        {
            if (pop[j] == pushStack.peek()) {
                pushStack.pop();
                j++;
            } else if (i == push.length) {
                return false;
            }
            if (i < push.length) {
                pushStack.push(push[i]);
                i++;
            }
        }
        return true;
    }
}
