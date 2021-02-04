package qyh.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 自己写一遍
 */
public class LeetCodeIndex480Heap {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(medianSlidingWindow(nums, 3)));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        DualHeap dualHeap = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dualHeap.put(nums[i]);
        }
        res[0] = dualHeap.getMid();
        for (int i = k; i < nums.length; i++) {
            dualHeap.put(nums[i]);
            dualHeap.remove(nums[i - k]);
            res[i - k + 1] = dualHeap.getMid();
        }
        return res;
    }

    private static class DualHeap {
        PriorityQueue<Long> big = new PriorityQueue<>();
        PriorityQueue<Long> small = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        Map<Long, Integer> debt = new HashMap<>();
        int smallSize = 0;
        int bigSize = 0;
        int k;

        DualHeap(int k) {
            this.k = k;
        }

        double getMid() {
            return (k & 1) == 1 ? (double) (small.peek()) : (double) (small.peek() + big.peek()) / 2;
        }

        void put(long num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                big.offer(num);
                bigSize++;
            }
            makeBalance();
        }

        void remove(long num) {
            debt.put(num, debt.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                smallSize--;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                bigSize--;
                if (num == big.peek()) {
                    prune(big);
                }
            }
            makeBalance();
        }

        void makeBalance() {
            if (smallSize - bigSize > 1) {
                big.offer(small.poll());
                smallSize--;
                bigSize++;
                prune(small);
            } else if (bigSize > smallSize) {
                small.offer(big.poll());
                smallSize++;
                bigSize--;
                prune(big);
            }
        }

        void prune(PriorityQueue<Long> priorityQueue) {
            while (!priorityQueue.isEmpty() && debt.containsKey(priorityQueue.peek())) {
                long num = priorityQueue.poll();
                debt.put(num, debt.get(num) - 1);
                if (debt.get(num) == 0) {
                    debt.remove(num);
                }
            }
        }
    }
}
