import java.awt.*;

public class Bullet {

    private Point coord;
    private double index = 0;

    public Bullet(int x, int y) {
        coord = new Point(x, y);
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }
    public void setCoordX(int x) {
        this.coord.x = x;
    }
    public void setCoordY(int y) {
        this.coord.y = y;
    }
    public Point getCoord() {
        return coord;
    }
    public int getCoordX() {
        return coord.x;
    }
    public int getCoordY() {
        return coord.y;
    }
    public void incBullet() {
        coord.y -= 5;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    public double getIndex() {
        return index;
    }
}
