package screen;

import animation.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type You win.
 */
public class  YouWin implements Animation {

    private int theScore;
    private Boolean end;


    /**
     * Instantiates a new You win.
     *
     * @param score the score
     */
    public YouWin(int score) {
        this.theScore = score;
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
        d.drawText(70, d.getHeight() / 2, "You Win! the score is: " + theScore, 50);

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return end;
    }
}
