package qyh.leetcode.midium;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex503 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = (i + 1) % n;; j++) {
                int index = j % n;
                if (index == i) {
                    ans[i] = -1;
                    break;
                } else if (nums[i] < nums[index]){
                    ans[i] = nums[index];
                    break;
                }
            }
        }
        return ans;
    }

    public static int[] next(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ans;
    }
}
