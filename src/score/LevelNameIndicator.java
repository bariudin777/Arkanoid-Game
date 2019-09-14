package score;

import biuoop.DrawSurface;
import collections.Sprite;

import java.awt.Color;


/**
 * The type Level name indicator.
 */
public class LevelNameIndicator implements Sprite {

    private String name;


    /**
     * Instantiates a new Level name indicator.
     *
     * @param name the name
     */
    public LevelNameIndicator(String name) {
        this.name = name;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        // fill the block with the score text
        d.setColor(Color.WHITE);
        d.drawText(600, 12, "Level Name: " + name, 16);

    }

    /**
     * Time passed.
     * @param dt dt
     */
    @Override
    public void timePassed(double dt) {
    }
}
