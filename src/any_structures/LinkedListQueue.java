package any_structures;
import implement.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    //带尾节点的链表
    private class Node {
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

         Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    private Node head;
    private Node tail;
    private int size;

    private LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void enqueue(E e) {   //入队
        if (tail == null) {//队列为空
            tail = new Node(e);//tail初始化
            head = tail;    //head初始化
        } else {//队列不为空
            tail.next = new Node(e);//tail的下一位 为入队节点
            tail = tail.next;   //维护tail尾节点
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (this.isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue. It's empty.");

        Node retNode = head;    //retNode 存储要出队的head //!!此时retNode 和 head 同时代表头节点
        head = head.next;  // head后移
        retNode.next = null;// retNode-> 箭头清除

        //  仅有一个元素的情况 出队后 队列为空 tail->retNode
        if (head == null)
            tail = null;
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (this.isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue : front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur.e + "->");
            cur = cur.next;
        }

        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }


}
