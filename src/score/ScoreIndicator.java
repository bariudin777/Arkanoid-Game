package score;

import biuoop.DrawSurface;
import collections.Sprite;
import blockremover.Counter;
import mathgeometry.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {

    private List<Sprite> scoreList;
    private Block scoreBlock;
    private Counter score;


    /**
     * Instantiates a new Score indicator.
     *
     * @param counter the counter
     */
    public ScoreIndicator(Counter counter) {
        this.scoreList = new ArrayList<>();
        this.score = counter;


    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {

        String scoreT = "" + score.getValue();
        // fill the block with the score text
        d.setColor(Color.WHITE);
        d.drawText(350, 12, "Score: " + scoreT, 16);

    }

    /**
     * Time passed.
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {
        for (int i = 0; i < scoreList.size(); i++) {
            scoreList.get(i).timePassed(dt);
        }

    }
}
