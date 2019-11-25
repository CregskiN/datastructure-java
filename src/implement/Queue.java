package implement;

public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e); // 入队
    E dequeue(); // 出队
    E getFront();
}
