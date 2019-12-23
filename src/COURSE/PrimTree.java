package COURSE;

import structuretools.Array;

public class PrimTree {
    private class Node {
        int index; // 第index号节点，即name
        boolean selected; // 是否被加入prim树
        int minDist; // 最小边权值
        int parentIndex; // 父节点

        public Node(int index) {
            this.index = index;
            selected = false;
            minDist = Integer.MAX_VALUE;
            parentIndex = -1;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("{index=" + index + ", selected=" + selected + ", minDist=" + minDist + ", parentIndex=" + parentIndex + ")\n");
            return res.toString();
        }
    }

    int map[][];
    Node root;
    Array<Node> nodes = new Array<>();

    // 初始化
    public PrimTree(int root, int[][] map) {
        this.map = map;
        initTree(root);

        while (!isFinished()) {
            update();
            System.out.println(nodes);
            int addNodeIndex = scan();
            add(addNodeIndex);
        }

    }

    private void initTree(int root) {
        // 包括根节点的标记
        for (int i = 0; i < this.map.length; i++) {
            this.nodes.addLast(new Node(i));
        }
        this.root = nodes.get(root);
        this.root.selected = true;
    }

    // 标记已选点集 与 未选点集 连线
    private void update() {

        for (int i = 0; i < nodes.getSize() - 1; i++) {
            Node cur = nodes.get(i);

            // 已选点
            if (cur.selected) {
                // cur点到next点的权值
                int[] row = map[cur.index];

                for (int j = 0; j < row.length; j++) {
                    Node next = nodes.get(j);

                    // 与未选点比较
                    if (!next.selected && row[j] < next.minDist) {
                        next.minDist = row[j];
                        next.parentIndex = cur.index;
                    }

                }
            }
        }
    }

    // 返回 这些连线中权值最小的现对应的 未选点
    private int scan() {
        // 找出nodes中minDist最小的点，加入PrimTree并初始化minDist
        int minDist = Integer.MAX_VALUE;
        int minDistIndex = -1;

        for (int i = 0; i < nodes.getSize(); i++) {
            Node cur = nodes.get(i);
            if (!cur.selected && cur.minDist <= minDist) {
                minDist = cur.minDist;
                minDistIndex = i;
            }
        }
        return minDistIndex;
    }

    private boolean isFinished() {
        for (int i = 0; i < nodes.getSize() - 1; i++) {
            if (!nodes.get(i).selected) {
                return false;
            }
        }
        return true;
    }

    // 讲该未选点标记为 已选点
    private void add(int index) {
        nodes.get(index).selected = true;
    }

    private void print(){
        System.out.println(nodes);
    }


    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        int[][] map = {
                {max, 4, max, max, max, max, max, 8},
                {4, max, 8, max, max, max, max, 11, max},
                {max, 8, max, 7, max, 4, max, max, 2},
                {max, max, 7, max, 9, 14, max, max, max},
                {max, max, max, 9, max, 10, max, max, max},
                {max, max, 4, 14, 10, max, 2, max, max},
                {max, max, max, max, max, 2, max, 1, 6},
                {8, 11, max, max, max, max, 1, max, 7},
                {max, max, 2, max, max, max, 6, 7, max}
        };

        int[][] mapTest = {
                {max,4,3,max,max,max,max,max},
                {4,max,5,5,9,max,max,max},
                {3,5,max,5,max,max,max,5},
                {max,5,5,max,7,6,5,4},
                {max,9,max,max,7,3,max,max},
                {max,max,max,6,3,max,2,max},
                {max,max,max,5,max,2,max,6},
                {max,max,5,4,max,max,6,max}
        };

//        PrimTree p = new PrimTree(0, map);
//        p.print();

        PrimTree test = new PrimTree(0,mapTest);
        test.print();
    }
}
