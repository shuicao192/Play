import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class AlgoVisualizer {

    private static final int DELAY = 40;

    private Circle circle;
    private LinkedList<Point> points;
    private AlgoFrame frame;
    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        if(sceneWidth != sceneHeight)
            throw new IllegalArgumentException("This demo must be run in a square window!");

        this.N = N;
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneHeight / 2);
        points = new LinkedList();
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("MonteCarlo", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        for (int i = 0; i < N; i++) {
            if (i != 0) {
                frame.render(circle, points);
            }
            AlgoVisHelper.pause(DELAY);

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());
            Point point = new Point(x, y);
            points.add(point);
        }
    }


    public static void main(String[] args) {

        int sceneWidth = 600;
        int sceneHeight = 600;
        int N = 10000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
