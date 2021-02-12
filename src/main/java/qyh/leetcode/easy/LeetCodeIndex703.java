package qyh.leetcode.easy;

import java.util.*;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 */
public class LeetCodeIndex703 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    private static class KthLargest {
        PriorityQueue<Integer> smallQueue;
        PriorityQueue<Integer> bigQueue;
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            smallQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            bigQueue = new PriorityQueue<>();
            for (int num : nums) {
                if (bigQueue.size() < k) {
                    bigQueue.offer(num);
                } else if (bigQueue.peek() != null && bigQueue.peek() < num) {
                    bigQueue.offer(num);
                    smallQueue.offer(bigQueue.poll());
                } else {
                    smallQueue.offer(num);
                }
            }
        }

        public int add(int val) {
            if (bigQueue.size() < k) {
                bigQueue.offer(val);
            } else if (bigQueue.peek() != null && bigQueue.peek() < val) {
                bigQueue.offer(val);
                smallQueue.offer(bigQueue.poll());
            } else {
                smallQueue.offer(val);
            }

            return bigQueue.peek();
        }
    }
}
