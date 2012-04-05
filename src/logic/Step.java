package logic;

/**
 *
 * @author TC
 */
public class Step {
    private double t;
    private double x;
    private double y;

    public Step(double t, double x, double y) {
        this.t = t;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the t
     */
    public double getT() {
        return t;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }
}
