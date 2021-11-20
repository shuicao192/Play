import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {

    private static final int DELAY = 2;
    private static final int blockSide = 4;
    private final MazeData data;        // 数据
    private AlgoFrame frame;    // 视图
    private static final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(int size) {

        data = new MazeData(size, size);
        int sceneHeight = size * blockSide;
        int sceneWidth = size * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(this::run).start();
        });
    }

    // 动画逻辑
    private void run() {
        setData(-1, -1);
        //go(data.getEntranceX(), data.getEntranceY() + 1);
        //stackGo(data.getEntranceX(), data.getEntranceY() + 1);
        //queueGo(data.getEntranceX(), data.getEntranceY() + 1);
        randomGo(data.getEntranceX(), data.getEntranceY() + 1);
        setData(-1, -1);
    }

    private void randomGo(int x, int y) {
        RandomQueue<Position> queue = new RandomQueue<>();

        queue.add(new Position(x, y));
        data.visited[x][y] = true;
        data.openMist(x, y);

        while (!queue.isEmpty()) {
            Position curPos = queue.remove();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.x + d[i][0] * 2;
                int newY = curPos.y + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setData(curPos.x + d[i][0], curPos.y + d[i][1]);
                }
            }
        }
    }

    private void queueGo(int x, int y) {
        LinkedList<Position> queue = new LinkedList<>();

        queue.addLast(new Position(x, y));
        data.visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position curPos = queue.removeFirst();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.x + d[i][0] * 2;
                int newY = curPos.y + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.x + d[i][0], curPos.y + d[i][1]);
                }
            }
        }

    }

    private void stackGo(int x, int y) {
        Stack<Position> stack = new Stack<>();

        stack.push(new Position(x, y));
        data.visited[x][y] = true;

        while (!stack.isEmpty()) {
            Position curPos = stack.pop();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.x + d[i][0] * 2;
                int newY = curPos.y + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.x + d[i][0], curPos.y + d[i][1]);
                }
            }
        }

    }

    private void go(int x, int y) {
        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x,y are out of index in go function!");

        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0] * 2;
            int newY = y + d[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + d[i][0], y + d[i][1]);
                go(newX, newY);
            }
        }
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                for (int i = 0; i < data.N; i++)
                    for (int j = 0; j < data.M; j++) {
                        data.visited[i][j] = false;
                    }

                new Thread(() -> {
                    if (goMaze(data.getEntranceX(), data.getEntranceY())) {
                        System.out.println("Maze has no solution");
                    }
                }).start();
            }
        }
    }

    private boolean goMaze(int x, int y) {
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
                    data.maze[newX][newY] == MazeData.ROAD && !data.visited[newX][newY]) {
                if(goMaze(newX, newY)) return true;
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
        int size = 141;

        new AlgoVisualizer(size);
    }
}
