package screen;

import animation.Animation;
import biuoop.DrawSurface;

import levels.AnitialBackGround;
import score.HighScoresTable;
import score.ScoreInfo;

import java.awt.Color;
import java.util.List;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable highScoresTable;
    private Boolean end;
    private AnitialBackGround background;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param gameB  the game b
     */
    public HighScoresAnimation(HighScoresTable scores, AnitialBackGround gameB) {
        this.highScoresTable = scores;
        this.background = gameB;
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
        background.drawOn(d);
        d.setColor(Color.black);
        List<ScoreInfo> listOf = highScoresTable.getHighScores();
        d.drawText(50, 50, "Player Name", 30);
        d.drawText(600, 50, "Score", 30);
        d.drawText(170, 580, "Press space - to go back to the menu", 25);
        int num = 100;
        for (int k = 0; k < listOf.size(); k++) {
            d.drawText(50, num, listOf.get(k).getName(), 25);
            d.drawText(615, num, "" + listOf.get(k).getScore(), 25);
            num += 50;

        }

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
