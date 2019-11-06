package structuretools;

public class Array<E> {

    private E[] data; //定义 访问+类型+数组名
    private int size;   //数组中元素的个数 <指向第一个没有元素的下标>


    //构造函数 ， 传入数组的容量capacity构造Array
    public Array(int capacity) { //capacity表示数组最大容量
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);   //默认的 capacity = 10
    }


    //返回数组内元素个数
    public int getSize() {
        return size;
    }

    //返回数组最大容纳元素个数
    public int getCapacity() {
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    //////////////////////////////增增增增增增增增增增增增增增增增增增增增增增增增增增增增增
    //在所有元素后添加 e
    public void addLast(E e) {
        //System.out.println("addLast传参成功" + e);
        add(size, e);
    }

    //在所有数组最前添加 e
    public void addFirst(E e) {
        add(0, e);
    }

    //在index位置插入新元素e
    public void add(int index, E e) {

        //判断：传入下标是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast is failed. Array is full.");

        if (size == data.length) //判断：添加前数组是否已满
            //throw new IllegalArgumentException("AddLast is failed. Array is full.");
            resize(2*data.length);  //动态数组操作 私有方法 resize 扩容2倍
        // 合法执行插入
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];    //向后漂移

        data[index] = e;
        size++;
    }

    //////////////////////////////查查查查查查查查查查查查查查查查查查查查
    //得到索引index对应的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed . Index is illegal");
        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed . Index is illegal");
        data[index] = e;
    }

    //查找数组中!是否!e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))  //.equals()用于两个对象值的比较
                // == 为 两个对象 引用比较
                return true;
        }
        return false;
    }

    // 查询数组中元素e 的索引， 若不存在则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }

    public E getLast(){
        return get(size - 1);
        //不使用get(data[size - 1])  size = 0 不会报错 而get可以
    }

    public E getFirst(){
        return get(0);
    }





    //删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删//删
    //删除索引为index的元素 //同时返回被删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed . Index is illegal");

        E res = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        //此时data[size]的数值 == data[size-1]  不会被JAVA垃圾回收机制自动回收
        data[size] = null;  //启动语句   //称为loitering objects != memory leak
                                        // 游离的对象无法自动回收 ！= 内存泄漏
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return res;
    }

    //删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //删除第二个元素
    public E removeLast() {
        return remove(size - 1);
    }

    //1.查询数组内有没有e 有则删除 ！！不能保证数组中没有元素e
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;//没有元素e存在
    }



    @Override   //覆盖标记
    public String toString() {
        //控制 直接打印类的输出形式
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d , capacity = %d\n", size, data.length));
        // StringBuilder 动态字符串类  String.format 文本处理工具
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            /*if (i != size - 1)    //size-1为已赋值元素中最后一个元素的index
                res.append(",");*/
        }
        res.append(']');    //build数组=>字符串  形如[1,2,3]
        return res.toString();
    }


    //扩容 => 动态数组
    private void resize (int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)  //旧数组复制到新数组
            newData[i] = data[i];
        data = newData; //新数组引用给旧引用
    }   //动态数组更新完成

}
