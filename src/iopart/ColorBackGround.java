package iopart;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Color back ground.
 */
public class ColorBackGround extends BackGround {

    private Color color;

    /**
     * Instantiates a new Color back ground.
     *
     * @param color the color
     */
    public ColorBackGround(Color color) {
        this.color = color;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * Time passed.
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {

    }
}
