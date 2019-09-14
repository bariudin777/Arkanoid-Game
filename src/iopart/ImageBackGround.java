package iopart;

import biuoop.DrawSurface;

import java.awt.Image;


/**
 * The type Image back ground.
 */
public class ImageBackGround extends BackGround {

    private Image image;


    /**
     * Instantiates a new Image back ground.
     *
     * @param image the image
     */
    public ImageBackGround(Image image) {
        this.image = image;
    }

    /**
     * Time passed.
     *
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }
}
