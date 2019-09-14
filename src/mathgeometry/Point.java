package mathgeometry; /**
 *name- daniel bariudin
 * id- 307758334
 */


/**
 * mathgeometry.Point object.
 */
public class Point {
    // members - who I keep (per object - instance of class)
    private double x;
    private double y;

    /**
     * Constractor.
     *
     * @param x value
     * @param y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point

    /**
     * calculate distance.
     *
     * @param other the other
     * @return square root of dx^2 +dy^2
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    // equals -- return true is the points are equal, false otherwise

    /**
     * check if the points are the same.
     *
     * @param other the other
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (this.distance(other) == 0);
    }
    // Return the x and y values of this point

    /**
     * return.
     *
     * @return return the x and y values of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(2, 10);
        if (p1.distance(p2) != 5) {
            System.out.println("Error in distance");
        }
    }

}




