package any_structures;
import java.util.Random;
import implement.Stack;

class TestStructures {

    //测试stack运行opCount 个 push 和 pop操作所需要的时间 ，单位：秒
    private static double testStack(Stack<Integer> stack, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));  //入栈
        for (int i = 0; i < opCount; i++)
            stack.pop();    //出栈

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    public static void main(String[] args) {

        int opCount = 10000000;   //链表栈快一些  因数组栈需要动态分配内存

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time :" + time1 + "s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack , time:" + time2 + "s");

        //这个时间比较复杂 因为LinkedListStack 包含更多new 操作 在内存中寻找可开辟内存的空间
    }
}
