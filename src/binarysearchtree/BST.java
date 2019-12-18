package binarysearchtree;


import any_structures.LinkedListStack;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root; //根
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加
    public void add(E e) {
        root = add(root, e);
    }

    // 私有： 添加递归
    private Node add(Node node, E e) {
        //@ 改进细节： 考虑到 root==null, node.left==null,node.right ==null ,简化代码
        // 递归终止条件 : 成功放入
        if (node == null) {
            size++;
            return new Node(e);
        }

        // 不符合终止条件 继续递归
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    // 查询
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 私有： 查询递归 传入以node为跟的树
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) { // 小于
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }
        return false;
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 私有：前序遍历递归
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);


    }

    // 中序遍历  ： 递增排序
    public void inOrder(){
        inOrder(root);
    }

    // 私有
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历 ： 内存释放
    public void postOrder(){
        postOrder(root);
    }

    // 私有
    private void postOrder(Node node){
        if( node == null){
            return ;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }



//    public String toString(){
//        StringBuilder res = new StringBuilder();
//        generateBSTString(root, 0, res);
//        return res.toString();
//    }


}
