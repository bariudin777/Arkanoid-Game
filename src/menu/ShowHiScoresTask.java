package menu;

import animation.Animation;
import animation.AnimationRunner;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation hSa;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.hSa = highScoresAnimation;

    }

    @Override
    public Void run() {
        this.runner.run(this.hSa);
        return null;
    }
}
