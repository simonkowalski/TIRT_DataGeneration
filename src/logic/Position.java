package logic;

/**
 * @author Tomasz 'TC' Klimek
 */
public class Position {
    private double x;
    private double y;
    
    public Position() {
        x = 0;
        y = 0;
    }
    
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p) {

        Position pos = (Position) p;
        return (int)pos.getX() == (int)getX() && (int)pos.getY() == (int)getY();
    }

    public double distance(Position p) {
        return Math.sqrt(Math.pow((getX() - p.getX()),2) + Math.pow((getY() - p.getY()),2));
    }

    @Override
    public String toString() {
        return "("+getX()+" ,"+getY()+")";
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
}
