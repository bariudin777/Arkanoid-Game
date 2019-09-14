package ballremover;

import blockremover.Counter;
import collections.HitListener;
import game.GameLevel;
import mathgeometry.Ball;
import mathgeometry.Block;


/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter ballCounter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel   the game level
     * @param ballCounter the ball counter
     */
    public BallRemover(GameLevel gameLevel, Counter ballCounter) {
        this.gameLevel = gameLevel;
        this.ballCounter = ballCounter;
    }

    @Override
    public void hitEvent(Block deathB, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.ballCounter.decrease(1);
    }
}
