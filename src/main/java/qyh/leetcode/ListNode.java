package qyh.leetcode;

/**
 * 链表节点
 *
 * @since 2020-11-22
 */
public class ListNode {
    int val;

    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
