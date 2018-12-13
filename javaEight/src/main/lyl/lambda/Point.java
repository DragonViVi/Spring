package lambda;

/**
 * Date:2018/12/7 0007 上午 11:02
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/7 0007 上午 11:02
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Point moveRightBy(int x){
        return new Point(this.x + x, this.y);
    }
}
