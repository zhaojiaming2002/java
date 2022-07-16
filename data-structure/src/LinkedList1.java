

public class LinkedList1<T> {

    private class Node {
        private T e;
        private Node next;

        public Node(T e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(T e) {
            this.e = e;
            this.next = null;
        }
    }

    private int N;

    private Node head;

    public LinkedList1() {
        N = 0;
        this.head = null;
    }


    public int Count() {
        return N;
    }

    public boolean IsEmpty() {
        return this.N == 0;
    }
}
