package mathgeometry;

/**
 * mathgeometry.Velocity object.
 */
public class Velocity {
    private double vecY;
    private double vecX;
    private double angle;
    private double speed;

    /**
     * Constractor.
     *
     * @param vecX x
     * @param vecY y
     */
    public Velocity(double vecX, double vecY) {
        this.vecY = vecY;
        this.vecX = vecX;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets vecX.
     *
     * @return the vecX
     */
    public double getDy() {
        return vecY;
    }

    /**
     * Gets vecY.
     *
     * @return the vecY
     */
    public double getDx() {
        return vecX;
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p the p
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + vecX, p.getY() + vecY));
    }

    /**
     * Prev point point.
     *
     * @param n the n
     * @return the point
     */
    public Point prevPoint(Point n) {
        return new Point(n.getX() - vecX, n.getY() - vecY);
    }

    /**
     * Adjust dt.
     *
     * @param dt the dt
     * @return the velocity
     */
    public Velocity adjustDt(double dt) {
        return new Velocity(this.vecX * dt, this.vecY * dt);
    }
}
