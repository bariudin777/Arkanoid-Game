package mathgeometry; /**
 *name- daniel bariudin
 * id- 307758334
 */

import java.util.List;
import java.util.ArrayList;



/**
 * The type mathgeometry.Rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Instantiates a new mathgeometry.Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> myList = new ArrayList<>();

        double x1 = upperLeft.getX();
        double y1 = upperLeft.getY();
        double x2 = width + x1;
        double y2 = height + y1;

        Line upperLine = new Line(x1, y1, x2, y1);
        Line lowerLine = new Line(x1, y2, x2, y2);
        Line rightLine = new Line(x2, y1, x2, y2);
        Line leftLine = new Line(x1, y1, x1, y2);

        if (line.isIntersecting(upperLine)) {
            myList.add(line.intersection(upperLine));
        }

        if (line.isIntersecting(lowerLine)) {
            myList.add(line.intersection(lowerLine));
        }

        if (line.isIntersecting(rightLine)) {
            myList.add(line.intersection(rightLine));
        }

        if (line.isIntersecting(leftLine)) {
            myList.add(line.intersection(leftLine));
        }

        return myList;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * mathgeometry.Block side list.
     *
     * @return the list
     */
    public List<Line> blockSide() {
        List<Line> theSideOf = new ArrayList<>();

        double x1 = upperLeft.getX();
        double y1 = upperLeft.getY();
        double x2 = width + x1;
        double y2 = height + y1;

        // 0 = up
        // 1 = down
        // 2 = right
        // 3 = left
        theSideOf.add(new Line(x1, y1, x2, y1));
        theSideOf.add(new Line(x1, y2, x2, y2));
        theSideOf.add(new Line(x2, y1, x2, y2));
        theSideOf.add(new Line(x1, y1, x1, y2));

        return theSideOf;
    }

}


