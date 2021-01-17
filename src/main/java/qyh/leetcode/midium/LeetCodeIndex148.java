package qyh.leetcode.midium;

/**
 * 第148题
 *
 * @since 2020-11-22
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 *
 * 思路1.链表套快排
 */
public class LeetCodeIndex148 {
    public ListNode sortList(ListNode head) {
        return quickSort(head, null);
    }

    private ListNode quickSort(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        int target = head.val;
        ListNode currHead = head;
        head = head.next;
        ListNode higherCur = null;
        ListNode higherPrev = null;
        ListNode mid = null;
        boolean flag = false;
        while (head != tail) {
            if (head.val > target && !flag) {
                higherCur = head;
                flag = true;
            } else if (flag) {
                int tmp = higherCur.val;
                higherCur.val = head.val;
                head.val = tmp;
                flag = false;
            } else {
                higherPrev = head;
            }
            head = head.next;
        }
        if (flag && higherPrev != null) {
            mid = new ListNode(target);
            higherPrev.next = mid;
            mid.next = higherCur;
            currHead = currHead.next;
        }
        mid.next = quickSort(mid.next, head);
        currHead = quickSort(currHead, mid);
        return currHead;
    }
}
