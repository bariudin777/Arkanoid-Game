package animation;

import biuoop.DrawSurface;
import game.GameFlow;
import levels.LevelInformation;

import java.util.List;

/**
 * The type Game animation.
 */
public class GameAnimation implements Animation {

    private GameFlow game;
    private List<LevelInformation> levels;
    private boolean end;

    /**
     * Instantiates a new Game animation.
     *
     * @param game   the game
     * @param levels the levels
     */
    public GameAnimation(GameFlow game, List<LevelInformation> levels) {
        this.game = game;
        this.levels = levels;
        this.end = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.game.runLevels(this.levels);
        this.end = true;
    }

    @Override
    public boolean shouldStop() {
        if (this.end) {
            this.end = false;
            return true;
        }
        return false;
    }
}
