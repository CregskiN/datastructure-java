package COURSE;

import any_structures.LinkedListStack;


public class Maze {

    class Position {
        public int x, y;
        public int direction;

        public Position(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('(');
            res.append(this.x);
            res.append(',');
            res.append(this.y);
            res.append(')');
            return res.toString();
        }
    }

    //定义迷宫，' '表示通道块，'#'表示墙壁，在后面的执行过程中，迷宫的元素可能变成'*'表示路径，'@'表示曾经走过但是无法到达出口
    Position start = new Position(1, 1, 0);
    Position end = new Position(8, 8, 0);
    LinkedListStack<Position> stack = new LinkedListStack<>();
    public char maze[][] = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#', '#', ' ', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    //对已走过的路径标记
    private void footPrint(Position currentPosition) {//已走路标记
        maze[currentPosition.x][currentPosition.y] = '*';
    }

    //对拥堵块标记
    private void markPrint(Position congestedPosition) {//拥堵路标记
        maze[congestedPosition.x][congestedPosition.y] = '@';
    }

    //控制移动
    private Position moveTo(Position currentPosition, int direction) {
        switch (direction) {
            case '1':
                currentPosition.y++;
                break; //向东走，列号+1
            case '2':
                currentPosition.x++;
                break; //向下走 行号+1
            case '3':
                currentPosition.y--;
                break; //向左走 列号-1
            case '4':
                currentPosition.x--;
                break; //向上走 行号-1
        }
        return currentPosition;
    }

    //判断当前位置是否是通道块
    private boolean isPass(Position currentPosition) {
        return maze[currentPosition.x][currentPosition.y] == ' ';
    }

    //若迷宫存在start到end的通路，则求得一条路径 存在栈内，并返回boolean
    private boolean mazePath(Position start, Position end) {
        Position cur = start; //设定选中位置为入口
        do {
            if (isPass(cur)) {//判：可移动至选中位置？
                footPrint(cur);//1、地图标记
                Position e = new Position(cur.x, cur.y, cur.direction);
                stack.push(e);//2、已过路径入栈
                if (cur.x == end.x && cur.y == end.y) {//3、判：选中为终点？
                    return true;
                }
                cur = moveTo(e, 1);//4、移动至
            } else {//不能移动到选中位置
                Position e = stack.pop();//弹栈
                while (e.direction == 4 && !stack.isEmpty()) {
                    markPrint(e);
                    e = stack.pop();
                }
                if (e.direction < 4) {
                    e.direction++;
                    stack.push(e);
                    cur = moveTo(e, e.direction);
                }
            }
        } while (!stack.isEmpty());

        return false;
    }

    //打印迷宫
    private void printMaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("  ");
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //打印路径
    void printPath() {
        System.out.println(stack);
    }


    public static void main(String[] args) {
        Maze maze = new Maze();
        System.out.println("迷宫初始化为：");
        maze.printMaze();

        System.out.println("入口为：" + maze.start);
        System.out.println("出口为：" + maze.end);

        if (maze.mazePath(maze.start, maze.end)) {
            System.out.println("存在通路，其路径为：");
            System.out.println(maze.stack);
        } else {
            System.out.println("不存在通路");
            maze.printMaze();
        }
    }

}



