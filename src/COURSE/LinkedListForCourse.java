package COURSE;
import java.util.Scanner;
import structuretools.LinkedList;

public class LinkedListForCourse {

    private int num = 0;

    public static void main(String[] args) {

        //1、创建链表
        LinkedList<String> linkedList1 = new LinkedList<>();
        LinkedList<String> linkedList2 = new LinkedList<>();
        LinkedList<String> intersection = new LinkedList<>();
        LinkedList<String> union = new LinkedList<>();

        //2、给链表 每个节点赋值
        System.out.println("请输入集合A的元素，以回车作为间隔， exit结束输入");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.next();
            if (in.equals("exit"))
                break;
            linkedList1.addLast(in);
        }
        System.out.println("请输入集合B的元素，以回车作为间隔， exit结束输入");
        while (true) {
            String in = sc.next();
            if (in.equals("exit"))
                break;
            linkedList2.addLast(in);
        }
        for (int i = 0; i < linkedList1.getSize() - 1; i++) {
            for (int j = i + 1; j < linkedList1.getSize(); j++) {
                if(linkedList1.get(i).equals(linkedList1.get(j)))
                    throw new IllegalArgumentException("您输入的集合A有误");
            }
        }
        for (int i = 0; i < linkedList2.getSize() - 1; i++) {
            for (int j = i + 1; j < linkedList2.getSize(); j++) {
                if(linkedList2.get(i).equals(linkedList2.get(j)))
                    throw new IllegalArgumentException("您输入的集合B有误");
            }
        }

        System.out.println("linkedList1" + ' ' + linkedList1);
        System.out.println("linkedList2" + ' ' + linkedList2);


        //3、计算交集 放入intersection
        for (int i = 0; i < linkedList1.getSize(); i++) {
            for (int j = 0; j < linkedList2.getSize(); j++) {
                if (linkedList1.get(i).equals(linkedList2.get(j)))
                    intersection.addLast(linkedList1.get(i));
            }
        }
        System.out.println("linkedList1 ∩ linkedList2" + ' ' + intersection);


        //4、计算并集 放入union
        for (int i = 0; i < linkedList1.getSize(); i++) {
            union.addLast(linkedList1.get(i));
        }
        LinkedListForCourse l = new LinkedListForCourse();
        for (int i = 0; i < linkedList2.getSize(); i++) {
            l.num = 0;
            for (int j = 0; j < linkedList1.getSize(); j++) {
                if(linkedList2.get(i).equals(linkedList1.get(j)))
                    l.num++;
            }
            if(l.num == 0)
                union.addLast(linkedList2.get(i));
        }


        System.out.println("linkedList1 ∪ linkedList2" + ' ' + union);

    }
}