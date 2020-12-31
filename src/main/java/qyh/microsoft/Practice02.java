package qyh.microsoft;

import java.util.Stack;

/**
 * 定义栈的数据结构，要求添加一个min 函数，能够得到栈的最小元素。
 *
 * 要求函数min、push 以及pop 的时间复杂度都是O(1)。
 */
public class Practice02 {
    public static void main(String[] args) {
        int[] vals = {2, 3, 1, 4, 5};
        Stack[] stacks = buildStack(vals);
        Stack min = stacks[0];
        Stack full = stacks[1];
    }
    public static Stack[] buildStack(int [] vals) {
        Stack<Integer> minStack = new Stack<>();
        Stack<Integer> helpStack = new Stack<>();
        for (int val : vals) {
            helpStack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else if (val < minStack.peek()) {
                minStack.push(val);
            }
        }
        System.out.println("");
        return new Stack[]{minStack, helpStack};
    }
}
