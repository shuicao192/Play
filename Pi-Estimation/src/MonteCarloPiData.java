import java.awt.*;
import java.util.LinkedList;

public class MonteCarloPiData {

    private Circle circle;
    private LinkedList<Point> points;
    private int insideCircle = 0;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        this.points = new LinkedList();
    }

    public Circle getCircle() {
        return circle;
    }

    public int getPointNumber() {
        return points.size();
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contain(point)) {
            insideCircle++;
        }
    }

    public Point getPoint(int i) {
        if(i < 0 || i >= points.size())
            throw new IllegalArgumentException("out of bound in getPoint!");

        return points.get(i);
    }

    public double estimatePi() {
        if (points.size() == 0) {
            return 0.0;
        }

        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea * 4 / squareArea;
    }
}
