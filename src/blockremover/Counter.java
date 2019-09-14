package blockremover;

/**
 * The type Counter.
 */
public class Counter {

    private int c;

    /**
     * Instantiates a new Counter.
     *
     * @param c the c
     */
    public Counter(int c) {
        this.c = c;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        c += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        c -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return c;
    }
}
