package qyh.microsoft;

/**
 * 给出俩个单向链表的头指针，比如h1，h2，判断这俩个链表是否相交。
 *
 * 假设俩个链表均不带环。
 */
public class Practice06 {
    public static void main(String[] args) {
        Node common = new Node(22, new Node(34, null));
        Node head1 = new Node(1, new Node(11, new Node(12, common)));
        Node head2 = new Node(2, new Node(21, new Node(22, common)));
        System.out.println(isIntersected(head1, head2));
    }

    public static boolean isIntersected(Node node1, Node node2) {
        Node tmpnode1 = node1;
        Node tmpNode2 = node2;
        while (tmpnode1.next != null) {
            tmpnode1 = tmpnode1.next;
        }
        while (tmpNode2.next != null) {
            tmpNode2 = tmpNode2.next;
        }
        return tmpnode1 == tmpNode2;
    }

    private static class Node {
        public int val;
        public Node next;
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
