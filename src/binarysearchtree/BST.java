package binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(e);
            return res.toString();
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

    /* 深度优先遍历 */
    /* 递归写法 */
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
    public void inOrder() {
        inOrder(root);
    }

    // 私有 ： 中续遍历递归
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历 ： 内存释放
    public void postOrder() {
        postOrder(root);
    }

    // 私有
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }

    /* 非递归写法
    @Stack实现
    */
    // 遍历： 前序遍历(非递归)
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // 遍历： 中序遍历(非递归)
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {   // 根节点到最深左节点入栈
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur);
                cur = cur.right;
            }

        }
    }

    /*
     * 对二叉树  5
     *       3      6
     *     2   4      8
     *  先序遍历： 5 3 2 4 6 8
     *  后序遍历： 2 4 3 8 6 5
     *  逆后序遍历： 5 6 8 3 4 2
     *  可见 - 逆后序遍历 相当于 先序遍历交换左右遍历顺序
     *  所以 后序遍历需要两个栈  二叉树 - 先序左右相反遍历 -> stack1 - 逆 -> stack2，
     *  stack2即为后序遍历
     *
     * */
    // 遍历： 后序遍历(非递归)
    public void postOrderNR() {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);


        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();

            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }

    }

    /* 层序遍历  @ Warning起始点深度为0 */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    // 查询： 最小值e
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        Node minNode = minimum(root);
        return minNode.e;
    }

    // 私有： 返回以node为根，最大值node
    private Node minimum(Node node) {
        if (node.left == null) { // 最小元素在二分搜索树最左侧
            return node;
        }
        return minimum(node.left);
    }

    // 查询： 最大值e
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    // 私有： 返回以node为根，最小值node
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node);
    }

    // 删除： 二分搜索树中，最小的node，并返回e
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 私有：以node为根的二分搜索树的最小节点
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除： 二分搜索树中，最大的node，并返回e
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除并返回：以node为根的二分搜索树的最大节点
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除： 指定节点 Hibbard Deletion
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除并返回： 以node为根，值为e的节点 (并更新)
    private Node remove(Node node, E e) {

        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);

        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);

        } else { // e == node.e

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除结点左右子树均不为空的情况
            // 找到比待删除结点大的最小节点， 及待删除结点右子树的最小节点
            // 用这个节点替换待删除节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            size++;

            successor.left = node.left;

            node.left = node.right = null;
            size--;

            return successor;
        }
        return node;
    }


    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

}
