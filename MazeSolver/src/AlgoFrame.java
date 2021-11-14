import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title) {

        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private MazeData data;

    public void render(MazeData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.getN();
            int h = canvasHeight / data.getM();

            for (int i = 0; i < data.getN(); i++) {
                for (int j = 0; j < data.getM(); j++) {
                    char maze = data.getMaze(i, j);
                    if (maze == MazeData.WALL) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                    } else if (maze == MazeData.ROAD){
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                    } else {
                        throw new IllegalArgumentException("maze block error");
                    }
                    if (data.path[i][j]) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Yellow);
                    }
                    if (data.result[i][j]) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                    }
                    AlgoVisHelper.fillRectangle(g2d, w * i, h * j, w, h);
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


