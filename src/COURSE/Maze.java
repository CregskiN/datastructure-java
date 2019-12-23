package COURSE;

import any_structures.LinkedListQueue;

public class Maze {

    static class Position {
        public int x, y;
        public int num = 0;
        public Position font = null;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(int y, int x, Position font) {
            this.x = x;
            this.y = y;
            this.font = font;
        }

        public Position(int y, int x, int num, Position font) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.font = font;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('(');
            res.append(y);
            res.append(',');
            res.append(x);
            res.append(')');
            return res.toString();
        }
    }

    public LinkedListQueue<Position> queue = new LinkedListQueue<>();

    //定义迷宫，' '表示通道块，'#'表示墙壁，在后面的执行过程中，迷宫的元素可能变成'*'表示路径，'@'表示曾经走过但是无法到达出口
    public char maze[][] = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '?', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#', '#', ' ', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '!', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    public void setMaze(int y, int x, char i) {
        maze[y][x] = i;
    }

    //方法： 寻找起点
    public Position findStart() {
        int x = -1, y = -1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '?') {
                    x = j;
                    y = i;
                }
            }
        }
        if (x == -1 || y == -1) {
            throw new ArithmeticException("无出发点！");
        }
        return new Position(x, y);
    }

    //方法： 寻找终点
    public Position findEnd() {
        int x = -1, y = -1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '!') {
                    x = j;
                    y = i;
                }
            }
        }
        if (x == -1 || y == -1) {
            throw new ArithmeticException("无终点！");
        }
        return new Position(x, y);
    }

    private void markFoot(Position cur) {
        maze[cur.y][cur.x] = '*';
    }

    //打印迷宫
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
                System.out.print(" ");
                if (j == maze[j].length - 1) {
                    System.out.print('\n');
                }
            }
        }
        System.out.println(' ');
    }

    // 搜索cur 四周通道块，入队
    private void searchAround(Position cur, int num) {
        if (maze[cur.y][cur.x + 1] == ' ') {
            Position p = new Position(cur.y, cur.x + 1, num, cur);
            queue.enqueue(p);
        }
        if (maze[cur.y + 1][cur.x] == ' ') {
            Position p = new Position(cur.y + 1, cur.x, num, cur);
            queue.enqueue(p);
        }
        if (maze[cur.y][cur.x - 1] == ' ') {
            Position p = new Position(cur.y, cur.x - 1, num, cur);
            queue.enqueue(p);
        }
        if (maze[cur.y - 1][cur.x] == ' ') {
            Position p = new Position(cur.y - 1, cur.x, num, cur);
            queue.enqueue(p);
        }
    }

    public void walking(Position res) {

        Position start = findStart();
        start.font = new Position(-1, -1, 0, null); // 类似虚拟头节点
        start.num = 1;
        Position end = findEnd();
        queue.enqueue(start);
        Position cur;


        for (int i = 1; ; i++) {
            while (!queue.isEmpty() && queue.getFront().num == i) {
                cur = queue.dequeue();
                if ((cur.x == end.x - 1 && cur.y == end.y) || (cur.x == end.x && cur.y == end.y - 1) ||
                        (cur.x == end.x + 1 && cur.y == end.y) || (cur.x == end.x && cur.y == end.y + 1)
                ) {
                    res.font = cur;
                    break;
                }
                markFoot(cur);
                searchAround(cur, i + 1);
            }
            System.out.println(queue);
            this.printMaze();

            if (res.font != null) {
                break;
            }
        }


        System.out.println("找到终点了" + res.font);
    }


    public static void main(String[] args) {
        Maze maze = new Maze();
        Position end = new Position(-1, -1, -1, null);

        maze.walking(end);
        Position cur = end.font;

        while (cur.font != null) {
            maze.setMaze(cur.y, cur.x, '+');
            cur = cur.font;
        }

        maze.printMaze();
        System.out.println("总共走了" + end.font.num + "步");

    }

}





