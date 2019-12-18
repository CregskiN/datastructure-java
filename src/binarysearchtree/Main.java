package binarysearchtree;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for (int num: nums) {
            bst.add(num);
        }

        /*         5
        *      3        6
        *    2   4        8
        * */
        System.out.println("层级遍历");
        bst.levelOrder();


        System.out.println("先序遍历");
        bst.preOrder();
        System.out.println();

        bst.preOrderNR();
        System.out.println();


        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();

        bst.inOrderNR();
        System.out.println();


        System.out.println("后序遍历");
        bst.postOrder();
        System.out.println();

        bst.postOrderNR();
        System.out.println();

//        System.out.println(bst);
    }
}
