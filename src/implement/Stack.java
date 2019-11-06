package implement;

public interface Stack<E> {
//在接口内 编译器为方法自动加上 public abstract
// 字段 public static final

    int getSize();  //得到栈大小
    boolean isEmpty();  //得到栈是否为空
    void push(E e);
    E pop();
    E peek();

}
