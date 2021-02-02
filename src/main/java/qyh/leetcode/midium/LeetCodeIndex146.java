package qyh.leetcode.midium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，
 * 则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，
 * 从而为新的数据值留出空间。
 * 
 *
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(2, 3);
        System.out.println(lruCache.get(1));
    }

    private static class LRUCache {
        Map<Integer, Node> map;
        Node head;
        Node tail;
        int currElementCount;
        int capacity;
        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.capacity = capacity;
            this.currElementCount = 0;
            this.head = new Node();
            this.tail = new Node();
        }

        public int get(int key) {

            return -1;
        }

        public void put(int key, int value) {
            if (currElementCount == 0) {
                Node newNode = new Node(key, value, head, tail);
                head.next = newNode;
                tail.prev = newNode;
            } else if (currElementCount < capacity) {
                Node newNode;
                if (map.containsKey(key)) {
                    map.get(key).value = value;
                    newNode = map.get(key);
                    newNode.prev.next = newNode.next;
                    newNode.next.prev = newNode.prev;
                } else {
                    newNode = new Node(key, value, null, null);
                    currElementCount++;
                }
                head.next.prev = newNode;
                newNode.next = head.next;
                newNode.prev = head;
                head.next = newNode;
            } else {
                Node newNode;
                if (map.containsKey(key)) {
                    newNode = map.get(key);
                    newNode.value = value;
                }
            }
        }
    }

    private static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node() {

        }

        public Node(int key, int value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
            this.key = key;
        }
    }
}
