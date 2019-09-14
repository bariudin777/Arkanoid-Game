package score;

import biuoop.DrawSurface;
import blockremover.Counter;
import collections.Sprite;
import mathgeometry.Block;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {

    private Counter liveScore;
    private Block block;


    /**
     * Instantiates a new Lives indicator.
     *
     * @param counter the counter
     */
    public LivesIndicator(Counter counter) {
        this.liveScore = counter;

    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        String liveText = "" + liveScore.getValue();
        d.setColor(Color.WHITE);
        d.drawText(100, 12, "Lives :" + liveText, 16);
    }

    /**
     * Time passed.
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {

    }
}
