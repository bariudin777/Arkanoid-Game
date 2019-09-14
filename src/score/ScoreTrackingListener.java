package score;

import collections.HitListener;
import blockremover.Counter;
import game.GameLevel;
import mathgeometry.Ball;
import mathgeometry.Block;


/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;
    private GameLevel gameLevel;


    /**
     * Instantiates a new Score tracking listener.
     *
     * @param curr the curr
     */
    public ScoreTrackingListener(Counter curr) {
        this.currentScore = curr;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // if the ball hits the block---- +5 points
        currentScore.increase(5);
        if (beingHit.getLive() == 0) {
            currentScore.increase(10);
        }
    }
}
