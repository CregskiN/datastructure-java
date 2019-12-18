package COURSE;


import any_structures.LinkedListStack;

public class BinaryTree<E extends Comparable<E>> {

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

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素 e
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

    /*// 查询
    public boolean contains(E e) {

    }

    // 私有： 查询递归 传入以node为跟的树
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }
    }
*/

    // 递归遍历 先序
    public void recursionFont(Node node) {
        if (node != null) {
            System.out.print(node.e);
            System.out.print(" ");
            recursionFont(node.left);
            recursionFont(node.right);
        }

    }

    // 中序
    public void recursionMid(Node node) {
        if (node != null) {
            recursionMid(node.left);
            System.out.print(node.e);
            System.out.print(" ");
            recursionMid(node.right);
        }

    }

    public void recursionLast(Node node) {
        if (node != null) {
            recursionLast(node.left);
            recursionLast(node.right);
            System.out.print(node.e);
            System.out.print(" ");
        }

    }

    // 非递归遍历 // 先序遍历，用栈实现 //先入后出
    public void noRecursion(Node node) {
        LinkedListStack<Node> stack = new LinkedListStack<>();
        Node cur = node;
        stack.push(cur);

        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.e + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    public static void main(String[] args) {
        BinaryTree<Integer> bst = new BinaryTree<>();
        for (int i = 0; i < 10; i++) {
            bst.add((int) (Math.random() * 100));
            System.out.println();
        }

        bst.recursionFont(bst.root);
        System.out.println(' ');
        bst.recursionMid(bst.root);
        System.out.println(' ');
        bst.recursionLast(bst.root);
        System.out.println(' ');
        bst.noRecursion(bst.root);
    }


}
