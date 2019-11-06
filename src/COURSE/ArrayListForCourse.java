package COURSE;

import java.util.Scanner;
import structuretools.Array;

public class ArrayListForCourse {
    private int num;

    public static void main(String[] args) {
        //1.创建线性顺序表
        Array<String> arr1 = new Array<>();
        Array<String> arr2 = new Array<>();
        Array<String> intersection = new Array<>();
        Array<String> union = new Array<>();

        //2.往 arr1 arr2  添加元素
        System.out.println("请输入集合A的元素，以回车作为间隔， 输入exit结束输入");
        Scanner sc = new Scanner(System.in);
        /*int nums = sc.nextInt();*/
        while (true) {
            String in = sc.next();
            if (in.equals("exit"))
                break;
            arr1.addLast(in);
        }
        for (int i = 0; i < arr1.getSize() - 1; i++) {
            for (int j = i + 1; j < arr1.getSize(); j++) {
                if(arr1.get(i).equals(arr1.get(j)))
                    throw new IllegalArgumentException("您输入的集合A有误");
            }
        }
        System.out.println("请输入集合的B元素，以回车作为间隔， 输入exit结束输入");
        while (true) {
            String in = sc.next();
            if (in.equals("exit"))
                break;
            arr2.addLast(in);
        }

        for (int i = 0; i < arr2.getSize() - 1; i++) {
            for (int j = i + 1; j < arr2.getSize(); j++) {
                if(arr2.get(i).equals(arr2.get(j)))
                    throw new IllegalArgumentException("您输入的集合B有误");
            }
        }

        System.out.println("arr1" + arr1);
        System.out.println("arr2" + arr2);

        //3、计算交集 放入intersection
        for (int i = 0; i < arr1.getSize(); i++) {
            for (int j = 0; j < arr2.getSize(); j++) {
                if (arr1.get(i).equals(arr2.get(j)))
                    intersection.addLast(arr1.get(i));
            }
        }
        System.out.println("linkedList1 ∩ linkedList2" + ' ' + intersection);


        //4、计算并集 放入union
        for (int i = 0; i < arr1.getSize(); i++) {
            union.addLast(arr1.get(i));
        }

        ArrayListForCourse l = new ArrayListForCourse();
        for (int i = 0; i < arr2.getSize(); i++) {
            l.num = 0;
            for (int j = 0; j < arr1.getSize(); j++) {
                if (arr2.get(i).equals(arr1.get(j)))
                    l.num++;
            }
            if (l.num == 0)
                union.addLast(arr2.get(i));
        }

        System.out.println("linkedList1 ∪ linkedList2" + ' ' + union);

    }



}