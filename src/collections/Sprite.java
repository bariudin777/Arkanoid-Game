package collections; /**
 * name- daniel bariudin
 * id- 307758334
 */

import biuoop.DrawSurface;

/**
 * The interface collections.Sprite.
 */
public interface Sprite {


    /**
     * Draw on.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     *
     * @param dt the dt
     */
// notify the sprite that time has passed
    void timePassed(double dt);
}
