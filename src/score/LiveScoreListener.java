package score;

import blockremover.Counter;
import collections.HitListener;
import mathgeometry.Ball;
import mathgeometry.Block;

/**
 * The type Live score listener.
 */
public class LiveScoreListener implements HitListener {

    private Counter liveCounter;

    /**
     * Instantiates a new Live score listener.
     *
     * @param curr the curr
     */
    public LiveScoreListener(Counter curr) {
        this.liveCounter = curr;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getLive() == 0) {
            liveCounter.decrease(1);

        }
    }
}
