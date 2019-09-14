package mathgeometry; /**
 *name- daniel bariudin
 * id- 307758334
 */

/**
 * mathgeometry.Range object.
 */
public class Range {
    private double start;
    private double end;

    /**
     * Constractor.
     *
     * @param start start point
     * @param end   end point
     */
    public Range(double start, double end) {
        this.start = start;
        this.end = end;
        if (start > end) {
            this.start = end;
            this.end = start;
        }
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public double getStart() {
        return start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public double getEnd() {
        return end;
    }

    /**
     * Check in range boolean.
     *
     * @param number the number
     * @return the boolean
     */
    public boolean checkTheRange(double number) {
        if (-0.0001 < number - start && -0.0001 < end - number) {
            return true;
        }
        return false;
    }
}
