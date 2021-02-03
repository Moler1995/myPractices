package qyh.leetcode.hard;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex480 {
    public static void main(String[] args) {
        int[] nums = {2147483647,2147483647};
        System.out.println(Arrays.toString(medianSlidingWindow(nums, 2)));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] middles = new double[nums.length - k + 1];
        Cache cache = new Cache(k);
        // 初始化长度
        for (int i = 0; i < k; i++) {
            cache.put(i, nums[i]);
        }
        middles[0] = getMid(cache.head, k);
        for (int i = k; i < nums.length; i++) {
            cache.put(i, nums[i]);
            middles[i - k + 1] = getMid(cache.head, k);
        }
        return middles;
    }

    private static double getMid(Node head, int k) {
        if (k == 1) {
            return head.val;
        }
        for (int i = 0; i < k; i++) {
            if (i != k / 2 - 1) {
                head = head.next;
            } else if (k % 2 == 0) {
                return (double) ((head.val + head.next.val)) / 2;
            } else {
                return head.next.val;
            }
        }
        return -1;
    }

    private static class Cache {
        Map<Integer, Node> map;
        Node head;
        int size;
        int capacity;
        Cache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            this.size = 0;
        }

        void put(int key, int value) {
            Node newNode = new Node(value, null, null);
            if (head == null) {
                head = newNode;
                map.put(key, head);
                size++;
            } else if (size < capacity) {
                insertOneNode(head, newNode, value, key);
                size++;
            } else {
                remove(key - capacity);
                insertOneNode(head, newNode, value, key);
            }
        }

        void insertOneNode(Node tmp, Node newNode, int value, int key) {
            while (tmp != null) {
                if (tmp.val > value) {
                    newNode.next = tmp;
                    newNode.prev = tmp.prev;
                    if (tmp.prev != null) {
                        tmp.prev.next = newNode;
                    }
                    tmp.prev = newNode;
                    if (tmp == head) {
                        head = newNode;
                    }
                    break;
                } else if (tmp.next == null) {
                    tmp.next = newNode;
                    newNode.prev = tmp;
                    break;
                } else {
                    tmp = tmp.next;
                }
            }
            if (head == null) {
                head = newNode;
            }
            map.put(key, newNode);
        }

        void remove(int index) {
            Node node = map.get(index);
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node == head) {
                head = node.next;
            }
            node.next = null;
            node.prev = null;
            map.remove(index);
        }
    }

    private static class Node {
        long val;
        Node next;
        Node prev;

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}
