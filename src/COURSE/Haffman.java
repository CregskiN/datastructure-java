//package COURSE;
//
//import any_structures.LinkedListStack;
//import structuretools.LinkedList;
//
//public class Haffman {
//    class Node {
//        int value;
//        char e;
//        Node left,right = null;
//
//        public Node(char e) {
//            this.e = e;
//            this.value = 1;
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder res = new StringBuilder();
//            res.append("(字符:");
//            res.append(this.e).append(",");
//            res.append("权值:").append(this.value).append(") ");
//            return res.toString();
//        }
//    }
//
//    String str;
//    Node root = null;
//
//    public Haffman(String str) {
//        this.str = str;
//    }
//
//    // 扫描输入字符串每个字符，并存储出现次数
//    public LinkedList scanStr() {
//        LinkedList<Node> list = new LinkedList<>();
//        for (int i = 0; i < str.length(); i++) {
//            char curChar = str.charAt(i);
//            boolean flag = true; // 判断curChar是否需要重新建Node
//            for (int j = 0; j < list.getSize(); j++) {
//                Node curNode = list.get(j);
//                if (curChar == curNode.e) {
//                    curNode.value++;
//                    flag = false;
//                }
//            }
//            if (flag) {
//                list.addLast(new Node(curChar));
//            }
//        }
//        return list;
//    }
//
//    public String enCode(){
//
//    }
//
//    private void add(Node node) {
//        if (root == null) {
//            root = node;
//        }
//        if (root.left == null){
//            root.left = node;
//        } else if (root.right == null) {
//            root.right = node;
//        }
//
//    }
//
//    private void recursionFont(Node node) {
//        if (node != null) {
//            System.out.print(node.e);
//            System.out.print(" ");
//            recursionFont(node.left);
//            recursionFont(node.right);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        String str = "AAABBACCCDEEA";
//        Haffman haffman = new Haffman(str);
//        System.out.println("您输入的字符串为： " + str);
//        LinkedList list = haffman.scanStr();
//        System.out.println("经扫描，您输入字符串情况为" + haffman.scanStr());
//        // 经扫描，您输入字符串情况为(字符:A,权值:5) ->(字符:B,权值:2) ->(字符:C,权值:3) ->(字符:D,权值:1) ->(字符:E,权值:2) ->NULL
//
//    }
//
//}
