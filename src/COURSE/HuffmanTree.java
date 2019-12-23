package COURSE;

import java.util.LinkedList;


public class HuffmanTree<E> {
    private class Node {
        E data;
        double weight;
        Node left, right;
        String code = null;

        // 叶子节点
        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
            left = null;
            right = null;
        }

        // 中间节点
        public Node(Node left, Node right) {
            this.data = null;
            this.weight = left.weight + right.weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("(data = ").append(data).append(", weight = ").append(weight).append("," + "code=").append(code).append(")");
            return res.toString();
        }

    }

    int size;
    Node root;
    LinkedList<Node> nodes = new LinkedList<>();
    private String nodeCode = ""; // 左0 右1


    public HuffmanTree() {
        size = 0;
        root = null;
    }

    // 添加： 批量添加
    public void add(Character[] names, Double[] weights) {
        for (int i = 0; i < names.length; i++) {
            add((E) names[i], weights[i]);
        }
    }

    private void add(E data, double weight) {
        Node node = new Node(data, weight);
        nodes.add(node);
        size++;
    }

    // 排序
    private void sort() {
        LinkedList<Node> newNodes = new LinkedList<>();
        while (!nodes.isEmpty()) {
            int index = 0;
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(index).weight < nodes.get(i).weight) {
                    index = i;
                }
            }
            newNodes.add(nodes.remove(index));
        }
        nodes = newNodes;
        System.out.print("排序结果为");
        System.out.println(nodes);
    }

    // 生成树
    private void generateTree() {
        Node min1 = nodes.removeLast();
        Node min2 = nodes.removeLast();

        Node temNode = new Node(min1, min2);
        while (!nodes.isEmpty()) {
            Node minNode = nodes.removeLast();
            temNode = new Node(minNode, temNode);
        }
        this.root = temNode;
    }

    // 私有：前序遍历 + 编码
    private void preOrder(Node node) {
        Node cur = node;

        if (cur.left != null) {
            nodeCode = nodeCode + "0";
            preOrder(cur.left);
        }
        if (cur.right != null) {
            nodeCode = nodeCode + "1";
            preOrder(cur.right);
        }

        if (cur.left == null && cur.right == null && cur.data != null) {
            cur.code = nodeCode;
            System.out.println("节点" + cur.data + "的编码为" + cur.code);
            nodeCode = nodeCode.substring(0,nodeCode.length() -1);
            return;
        }
    }


    // 中序遍历
    public void inOrder() {
        sort(); // 排序
        generateTree(); // 生成树
        preOrder(root);// 编码
        inOrder(root); // 遍历
    }

    // 私有 ： 中序遍历递归
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (node.data != null) {
            System.out.println(node);
        }
        inOrder(node.right);
    }

    public static void main(String[] args) {
        Character[] names = {'A', 'B', 'C', 'D', 'E', 'F'};
        Double[] weights = {15.0, 30.0, 20.0, 25.0, 3.0, 7.0};

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.add(names, weights);
        huffmanTree.inOrder();

    }
}

/*
* 思路
*   add() 输入names[] weights[]
*   sort()排序
*   generateTree()生成树
*   preOrder() 先序遍历生成节点编码code
*   inOrder() 前序遍历输出编码
*
*       root
*      /   \
*   B       tem
*         /    \
*       D       tem
*             /     \
*           C       tem
*                 /     \
*               A       tem
*                     /    \
*                   E       E
* */