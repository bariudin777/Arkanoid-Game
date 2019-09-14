package screen;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean end;
    private Boolean pressedKey;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        end = false;
        pressedKey = true;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        // create the animation[put --- m(key), dt(speed?)]
        if (keyboardSensor.isPressed(key) && !pressedKey) {
            this.end = true;
        }
        if (!keyboardSensor.isPressed(key)) {
            pressedKey = false;
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        if (this.end) {
            this.end = false;
            return true;
        }
        return false;
    }
}
