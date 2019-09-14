package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}
