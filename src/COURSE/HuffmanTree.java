package COURSE;

import java.util.LinkedList;


public class HuffmanTree<E> {
    private class Node {
        E data;
        double weight;
        Node left, right;

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
            res.append("(data = ").append(data).append(", weight = ").append(weight).append(")");
            return res.toString();
        }

    }

    int size;
    Node root;
    LinkedList<Node> nodes = new LinkedList<>();

    public HuffmanTree() {
        size = 0;
        root = null;
    }

    // 添加： 批量添加
    public void add(Character[] names, Double[] weights) {
        for (int i = 0; i < names.length; i++) {
            add((E) names[i], weights[i]);
        }

        this.sort();
        this.generateTree();
    }

    private void add(E data, double weight) {
        Node node = new Node(data, weight);
        nodes.add(node);
        size++;
    }

    // 排序
    private void sort() {
        LinkedList<Node> newNodes = new LinkedList<>();

//        System.out.println(nodes); // 排序前

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

//        System.out.println(nodes); // 排序后
    }

    // 生成树
    private void generateTree() {
        // TODO 生成哈夫曼树 (节点在nodes内)
//        Node cur = new Node(nodes.removeLast(), nodes.removeLast());
//
//        while(!nodes.isEmpty()){
//            cur = new Node(nodes.removeLast(),cur);
//        }

//        preOrder(cur);

    }

    // 私有：前序遍历递归
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }


    public static void main(String[] args) {
        Character[] names = {'A', 'B', 'C', 'D', 'E', 'F'};
        Double[] weights = {15.0, 30.0, 20.0, 25.0, 3.0, 7.0};

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.add(names, weights);


    }
}