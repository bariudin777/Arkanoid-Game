package screen;

import animation.Animation;
import biuoop.DrawSurface;
import collections.SpriteCollection;

import java.awt.Color;

/**
 * The type Pause.
 */
public class Pause implements Animation {

    private SpriteCollection screen;
    private Boolean end;


    /**
     * Instantiates a new Pause.
     *
     * @param screen the screen
     */
    public Pause(SpriteCollection screen) {
        this.screen = screen;
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
        screen.drawAllOn(d);
        d.drawText(70, d.getHeight() / 2, "Pause", 45);
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
