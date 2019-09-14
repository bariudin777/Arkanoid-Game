package screen;

import animation.Animation;
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * The type Game over.
 */
public class GameOver implements Animation {

    private int scoreAfterGame;
    private Boolean end;


    /**
     * Instantiates a new Game over.
     *
     * @param score the score
     */
    public GameOver(int score) {
        this.scoreAfterGame = score;
        this.end = false;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(70, d.getHeight() / 2, "Game Over! Your score is: " + scoreAfterGame, 40);
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.end;
    }
}
