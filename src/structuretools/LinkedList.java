package structuretools;

public class LinkedList<E> {

    //定义Node类
    private class Node {
        public E e;
        public Node next;   //下一个元素的地址

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
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

    private Node dummyHead;  //链表 虚拟头指针
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);     //为链表设立虚拟头节点dummyHead 无数值(浪费一个空间)
        this.size = 0;
    }

    //获取链表元素个数
    public int getSize() {
        return size; //确切的元素个数
    }

    //返回是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    // 1.创建新节点
    // 2.新节点指向前节点的后节点
    // 3.前节点指向新节点
    //!!关键  找到前节点
    //在链表index(0 - base)位置添加新元素e
    //链表中不是一个常用操作 思维练习
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;   // 0是虚拟头节点 截止到index-1运行完成 prev指在index

        prev.next = new Node(e, prev.next); //  在index位置创建新节点
        size++;
            /*Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;*/
//        }
    }

    //在链表头 添加新元素 e
    public void addFirst(E e) {
        add(0, e);
        /*Node node = new Node(e);
        node.next = head;
        this.head = node;*/
        /*head = new Node(0, head);
        size++;*/
    }

    //在链表末尾 添加新元素e
    public void addLast(E e) {
        add(size, e);
    }

    //获取
    public E get(int index) {

        if (index < 0 || index > size){
            System.out.println(index);
            throw new IllegalArgumentException("Get failed . Illegal index.");
        }

            Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }

    //!修改第index(0 - based) 个位置的元素为e
    //在链表中又不是一个常用操作~~~
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    //查询链表是否有元素e
    //也不是一个常用操作~~~
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }


    //!!!!!!!!!!从链表中删除index(0 - based)位置的元素，返回删除的元素
    //在链表中不是一个常用的操作~~~
    public E remove(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)   //节点跳到 下标 n-1元素
            prev = prev.next;

        //prev代表将删除节点的前节点
        Node retNode = prev.next; // 将 reNode变为要删除节点的索引
        prev.next = retNode.next;   // 让prev指向 要删除节点的下一个节点
        retNode.next = null;
        size--;

        return retNode.e;
    }

    //从链表中删除第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    //从链表中删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

}