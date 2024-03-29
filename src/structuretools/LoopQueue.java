package structuretools;
import implement.Queue;


public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];    //循环队列 浪费一个容量
        front = 0;  //队首下标
        tail = 0;   //队尾下标
        size = 0;   //size表示元素个数
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return data.length - 1;
    }

    @Override
    public void enqueue(E e){   //入队//队尾

        if((tail + 1) % data.length == front )  //由此产生一个位置的浪费
            resize(getCapacity() * 2);  //不使用data.length 会加上被浪费的一个位置
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }


    @Override
    public E dequeue(){ //出队//队首

        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;  //我在C里用 |data.length - front|
        size --;
            //适当缩容
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront(){
        if (front == tail)
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue : size = %d , capacity = %d \n", size , getCapacity()));
        res.append("front [");
//        for (int i = front; i < tail + data.length ; i = front + 1) { //错误写法 存在 i > capacity
        for (int i = front; i != tail ; i = (i + 1) % data.length) {
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(',');
        }
        res.append("] tail");
    //return toString(res);写法错误 StringBuilder 有 toString
        return res.toString();
    }



    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1]; //+1 为浪费的空间
        for(int i = 0;i < size; i++)
            newData[i] = data[(front + i) % data.length];
        data = newData;
        front = 0;
        tail = size;
    }


    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
