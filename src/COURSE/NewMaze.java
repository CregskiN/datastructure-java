package COURSE;

import java.util.Stack;

public class NewMaze {

    private class Position {
        public int x, y, direction;

        public Position(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.direction = -1; //-1 -- 未定义方向
        }

        public Position() {
            this.x = -1;
            this.y = -1;
            this.direction = -1;
        }

        public void setPosition(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        //方法： 移动节点 -- 传入原节点，返回移动后的节点
        public Position move() {
            //右为x正向， 下为y正向
            Position newPos = new Position(this.x, this.y, this.direction);
            switch (this.direction) {
                case 0: //右移
                    newPos.x += 1;
                    break;
                case 1: //下移
                    newPos.y += 1;
                    break;
                case 2: //左移
                    newPos.x -= 1;
                    break;
                case 3: //上移
                    newPos.y -= 1;
                    break;
            }
            return newPos;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('(');
            res.append(this.x + 1 + ',' + this.y + 1);
            res.append(')');
            return res.toString();
        }
    }

    //定义： '?' -起点 '!' -终点 ' ' -通道块，'#' -墙壁，'*' -已走路径，'@' -已走但不通
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

    //方法： 标记已走路径
    public void markFoot(Position pos) {
        maze[pos.y][pos.x] = '*';
    }

    //方法： 标记已走但走不通路段
    public void markCongestion(Position pos) {
        maze[pos.y][pos.x] = '@';
    }

    //方法： 寻找起点
    public Position findStart() {
        Position start = new Position();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '?') {
                    start.x = j;
                    start.y = i;
                }
            }
        }
        return start;
    }

    //方法： 打印地图
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.println(maze[i][j]);
            }
        }
    }

    //方法： 开始寻路, 输入起点，寻找终点，返回路径
    public Stack Walking(Position start) {
        Stack<Position> path = new Stack<>();
        start.setPosition(start.x, start.y, 0);
        path.push(start);
        do {
            Position cur = path.pop();

            Position test = cur.move();
            if(maze[test.y][test.x] == '#'){
                markCongestion(test);
            }

        } while (maze[path.pop().y][path.pop().x] != '!');
        return path;
    }

}
