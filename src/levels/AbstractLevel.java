package levels;



/**
 * The type Abstract level.
 */
public abstract class AbstractLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
