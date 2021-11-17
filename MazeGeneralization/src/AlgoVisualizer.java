import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static final int DELAY = 5;
    private static final int blockSide = 4;
    private final MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int size) {

        data = new MazeData(size, size);
        int sceneHeight = size * blockSide;
        int sceneWidth = size * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    // 动画逻辑
    private void run() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int size = 141;

        new AlgoVisualizer(size);
    }
}
