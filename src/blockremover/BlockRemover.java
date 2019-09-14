package blockremover;

import collections.HitListener;
import game.GameLevel;
import mathgeometry.Ball;
import mathgeometry.Block;


/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel     the game level
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getLive() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
        }
    }

}
