package mathgeometry; /**
 *name- daniel bariudin
 * id- 307758334
 */

import java.util.List;

/**
 * The type mathgeometry.Line.
 */
public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double freeB;

    /**
     * Constractor.
     *
     * @param start start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Instantiates a new mathgeometry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope();
        this.varB();
    }

    /**
     * returns start point of line.
     *
     * @return start point of line
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * returns end point of line.
     *
     * @return end point of line
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * get incline of line.
     *
     * @return incline of line
     */
    public double getIncline() {
        return this.slope;
    }

    /**
     * get parameter b.
     *
     * @return parameter b
     */
    public double getB() {
        return this.freeB;
    }


    /**
     * length of line.
     *
     * @return length of line
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * find middle point of line.
     *
     * @return middle point of line
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2); //(x1-x2)/2 ,(y1-y2)/2
    }

    /**
     * calculate incline of a line.
     */
    //k= the denomenator
    private void slope() {
        double k = this.start.getX() - this.end.getX();
        if (k == 0) {
            this.slope = Double.POSITIVE_INFINITY;
        } else {
            this.slope = (this.start.getY() - this.end.getY()) / k;
        }

    }

    /**
     * calculate the free variable -b.
     * b in y = mx + b
     */
    private void varB() {
        this.freeB = this.start.getY() - (this.slope * this.start.getX()); //y-mx=b
    }

    /**
     * check if the lines are equals.
     *
     * @param other line to check
     * @return true if equals, otherwise false
     */
    public boolean equals(Line other) {
        return ((this.start.distance(other.start) < 0.00001 && this.end.distance(other.end) < 0.00001)
                || (this.start.distance(other.end) < 0.00001 && this.end.distance(other.start) < 0.00001));
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other line to check if there is an intersection point with
     * @return point point
     */
    public Point intersection(Line other) {
        double x, y;
        if (this.slope == other.slope) {
            return null;
        } else if (this.slope == Double.POSITIVE_INFINITY) {
            x = this.start.getX();
            y = x * other.slope + other.freeB;
        } else if (other.slope == Double.POSITIVE_INFINITY) {
            x = other.start.getX();
            y = x * this.slope + this.freeB;
        } else {
            x = (this.freeB - other.freeB) / (other.slope - this.slope);
            y = this.slope * x + this.freeB;
        }
        Point intersection = new Point(x, y);
        if (checkPoint(intersection) && other.checkPoint(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * check if their is an intersection point.
     *
     * @param other line to check
     * @return true if their is, else false
     */
    public boolean isIntersecting(Line other) {
        if (intersection(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * check if point is in range of line.
     *
     * @param n point
     * @return true if inside, else false
     */

    private boolean checkPoint(Point n) {
        double num;
        if (this.end.getX() - this.start.getX() != 0) {
            num = (n.getX() - this.start.getX()) / (this.end.getX() - this.start.getX());
        } else {
            num = (n.getY() - this.start().getY()) / (this.end.getY() - this.start.getY());
        }
        if (-0.000001 <= num && num <= 1.000001) {
            return true;
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> myList = rect.intersectionPoints(this);

        if (myList.isEmpty()) {
            return null;
        }

        Point closest = myList.get(0);

        for (int i = 1; i < myList.size(); ++i) {
            if (start.distance(myList.get(i)) < start.distance(closest)) {
                closest = myList.get(i);
            }
        }

        return closest;
    }
}