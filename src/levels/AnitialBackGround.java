package levels;

import biuoop.DrawSurface;
import collections.Sprite;
import collections.SpriteCollection;


import java.awt.Color;

/**
 * The type Anitial back ground.
 */
public class AnitialBackGround implements Sprite {
    private SpriteCollection spriteCollection;


    /**
     * Instantiates a new Anitial back ground.
     */
    public AnitialBackGround() {
        spriteCollection = new SpriteCollection();

    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        spriteCollection.drawAllOn(d);
    }

    /**
     * Time passed.
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {
    }
}
