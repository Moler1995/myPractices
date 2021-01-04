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
        Node node = new Node(1, new Node(2, new Node(3, new Node(5, null))));
        Node node1 = new Node(4, null);
        merge(node, node1);
        // reverse(node);
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

    public static Node merge(Node head1, Node head2) {
        Node newNode = null;
        Node tmpNode = null;
        while (head1 != null && head2 != null) {
            if (newNode == null) {
                if (head1.val < head2.val) {
                    newNode = head1;
                    head1 = head1.next;
                } else {
                    newNode = head2;
                    head2 = head2.next;
                }
                tmpNode = newNode;
            } else {
                if (head1.val < head2.val) {
                    newNode.next = head1;
                    head1 = head1.next;
                } else {
                    newNode.next = head2;
                    head2 = head2.next;
                }
                newNode = newNode.next;
                if (head1 == null) {
                    newNode.next = head2;
                } else if (head2 == null) {
                    newNode.next = head1;
                }
            }
        }
        return tmpNode;
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
