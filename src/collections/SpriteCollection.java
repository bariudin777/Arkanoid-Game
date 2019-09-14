package collections; /**
 * name- daniel bariudin
 * id- 307758334
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type collections.Sprite collection.
 */
public class SpriteCollection {

    private List<Sprite> collectionOfSprites;

    /**
     * Instantiates a new collections.Sprite collection.
     */
    public SpriteCollection() {
        collectionOfSprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param n the n
     */
    public void addSprite(Sprite n) {
        collectionOfSprites.add(n);
    }

    /**
     * Remove sprite.
     *
     * @param n the n
     */
    public void removeSprite(Sprite n) {
        collectionOfSprites.remove(n);
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < collectionOfSprites.size(); i++) {
            collectionOfSprites.get(i).drawOn(d);
        }
    }

    /**
     * Notify all time passed.
     *
     * @param dt the dt
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < collectionOfSprites.size(); i++) {
            collectionOfSprites.get(i).timePassed(dt);
        }
    }
}


