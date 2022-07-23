import org.w3c.dom.Node;

public class LinkedList1<T> {

    // 内部类，不让用户好奇访问
    private class Node {
        // 存储任意数据
        private T e;
        // 下一个节点
        private Node next;

        public Node(T e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(T e) {
            this.e = e;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

    // 头节点
    private Node head;
    // 有几个元素~
    private int N;

    public LinkedList1() {
        N = 0;
        this.head = null;
    }


    public int count() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void add(int index, T e) {
        if (index < 0 || index > N) {
            throw new RuntimeException("非法越界");
        }
        if (index == 0) {
            // 创建一个节点
            Node node = new Node(e);
            // 新节点下一个节点等于，头节点
            node.next = head;

//            System.out.println("index == 0-->node.next -->" + node.next);
            // 头节点等于新节点
            head = node;
//            System.out.println(head);
        } else {

            Node node = new Node(e);

            Node pre = head;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            // 先吧前一个下一个节点赋值给新的节点下一个节点
            node.next = pre.next;
            // 吧前一个的下一个节点复制给，新的
            pre.next = node;
//            System.out.println(">0" + node.next);

        }
        N++;
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public void addLast(T e) {
        add(N, e);
    }

    public T get(int index) {
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;

    }

    public T getFirst() {
        return get(0);
    }


    public T set(int index, T e) {
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
        return cur.e;
    }

    public boolean contains(T e) {
        Node cur = head;
        while (cur != null) {
            if (cur.next.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    public T removeAt(int index) {
        if (index < 0 || index >= N) {
            throw new RuntimeException("非法越界");
        }
        if (index == 0) {
            head = head.next;
            N--;
            return head.e;
        } else {
            Node pre = head;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            Node delNode = pre.next;
            pre.next = delNode.next;
            N--;
            return delNode.e;

        }
    }


    public T removeFirst() {
        return removeAt(0);
    }

    public T removeLast() {
        return removeAt(N - 1);
    }


    public T remove(T e) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            if (cur.e.equals(e)) {
                break;
            }
            pre = cur;
            cur = cur.next;

        }
        if (cur != null && head != cur) {
            pre.next = cur.next;
            N--;
            return cur.e;
        } else if (cur == head && cur.equals(head)) {

            T t = removeFirst();
            return t;
        } else {
            return null;
        }


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = head;
        for (int i = 0; i < N; i++) {
            stringBuilder.append(cur.e + "-->");
            cur = cur.next;
        }
        stringBuilder.append("null");
        return stringBuilder.toString();

    }
}
