package qyh.microsoft;

/**
 * 链表操作
 *
 * （1）.单链表就地逆置，
 *
 * （2）合并链表
 */
public class Practice14 {
    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        reverse(node);
    }

    public static void reverse(Node head) {
        Node tmpNode1 = head;
        while (tmpNode1 != null && tmpNode1.next != null) {
            Node nnNode = tmpNode1.next.next;
            Node tmpNode2 = head;
            head = tmpNode1.next;
            head.next = tmpNode2;
            tmpNode1.next = nnNode;
        }
    }

    public static class Node {
        int val;
        Node next;
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
