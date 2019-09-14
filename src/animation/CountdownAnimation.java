package animation;

import biuoop.DrawSurface;
import collections.SpriteCollection;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.

/**
 * The type Countdown animation.
 */

public class CountdownAnimation implements Animation {

    private double numOfSec;
    private int countDown;
    private SpriteCollection gameScreen;
    private boolean first;
    private boolean stop;
    private long startTime;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.numOfSec = numOfSeconds;
        first = true;
        stop = false;
        countDown = countFrom;
        this.startTime = Long.MAX_VALUE;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        gameScreen.drawAllOn(d);
        if (first) {
            first = false;
            startTime = System.currentTimeMillis();
        }
        double curr = System.currentTimeMillis();
        curr -= startTime;
        curr /= 1000;
        double timeOf = numOfSec / (countDown + 1);
        int k = countDown;
        while ((curr -= timeOf) > 0) {
            --k;
        }

        if (k != 0) {
            d.drawText(400, 300, "" + k, 50);
        } else {
            d.drawText(400, 300, "Go!", 50);
        }


    }

    @Override
    public boolean shouldStop() {
        return System.currentTimeMillis() >= startTime + (numOfSec * 1000);
    }
}
