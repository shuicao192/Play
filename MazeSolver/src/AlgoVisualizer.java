import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {

    private static final String FILENAME = "D:\\Users\\kigoss\\IdeaProjects\\Play\\MazeSolver\\maze_101_101.txt";
    public static final int BLOCK_SIDE = 6;
    private static final int DELAY = 5;

    private final MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    private int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer() {

        // 初始化数据
        data = new MazeData(FILENAME);
        int sceneWidth = data.getN() * BLOCK_SIDE;
        int sceneHeight = data.getM() * BLOCK_SIDE;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze-Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    // 动画逻辑
    private void run() {
        setData(-1, -1, false);

//        if (!go(data.getEntranceX(), data.getEntranceY()))
//            System.out.println("Maze has no solution");

//        if (stackGo(data.getEntranceX(), data.getEntranceY())) {
//            System.out.println("Maze has no solution");
//        }

        if (queueGo(data.getEntranceX(), data.getEntranceY())) {
            System.out.println("Maze has no solution");
        }

        setData(-1, -1, false);
    }

    private boolean queueGo(int x, int y) {

        LinkedList<Position> queue = new LinkedList<>();

        queue.addLast(new Position(x, y));
        data.visited[x][y] = true;

        while (queue.size() != 0) {
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                data.result[x][y] = true;
                Position des = curPos;
                while (des.prev!=null) {
                    data.result[des.getX()][des.getY()] = true;
                    des = des.prev;
                }
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.x + d[i][0];
                int newY = curPos.y + d[i][1];
                if (data.inArea(newX, newY) &&
                        data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }
        return false;
    }

    private boolean stackGo(int x, int y) {

        Stack<Position> stack = new Stack<>();

        stack.push(new Position(x, y));
        data.visited[x][y] = true;

        while (!stack.empty()) {
            Position curPos = stack.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                Position des = curPos;
                while (des.prev!=null) {
                    data.result[des.getX()][des.getY()] = true;
                    des = des.prev;
                }
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.x + d[i][0];
                int newY = curPos.y + d[i][1];
                if (data.inArea(newX, newY) &&
                        data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }
        return false;
    }

    private boolean go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x or y is out of index in getMaze!");
        }

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) &&
                    data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                if(go(newX, newY)) return true;
            }
        }

        setData(x, y, false);

        return false;
    }

    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x, y))
            data.path[x][y] = isPath;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        new AlgoVisualizer();
    }
}
