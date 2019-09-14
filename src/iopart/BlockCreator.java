package iopart;

import mathgeometry.Block;
import mathgeometry.Point;
import mathgeometry.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;

/**
 * The type Block creator.
 */
public class BlockCreator implements IBlockCreator {
    private Integer height;
    private Integer hitPoints;
    private Integer width;
    private Map<Integer, Color> stringColorMap;
    private Map<Integer, Image> stringImageMap;

    /**
     * Instantiates a new Block creator.
     */
    public BlockCreator() {

    }

    /**
     * Instantiates a new Block creator.
     *
     * @param height         the height
     * @param hitPoints      the hit points
     * @param width          the width
     * @param stringColorMap the string color map
     * @param stringImageMap the string image map
     */
    public BlockCreator(Integer height, Integer hitPoints, Integer width, Map<Integer, Color> stringColorMap,
                        Map<Integer, Image> stringImageMap) {
        this.height = height;
        this.hitPoints = hitPoints;
        this.width = width;
        this.stringColorMap = stringColorMap;
        this.stringImageMap = stringImageMap;
    }

    /**
     * Sets height.
     *
     * @param hOf the height
     */
    public void setHeight(Integer hOf) {
        this.height = hOf;
    }

    /**
     * Sets hit points.
     *
     * @param hitpoints the hit points
     */
    public void setHitPoints(Integer hitpoints) {
        this.hitPoints = hitpoints;
    }

    /**
     * Sets width.
     *
     * @param widthOf the width
     */
    public void setWidth(Integer widthOf) {
        this.width = widthOf;
    }

    /**
     * Sets string color map.
     *
     * @param colorMap the string color map
     */
    public void setStringColorMap(Map<Integer, Color> colorMap) {
        this.stringColorMap = colorMap;
    }

    /**
     * Sets string image map.
     *
     * @param imageMap the string image map
     */
    public void setStringImageMap(Map<Integer, Image> imageMap) {
        this.stringImageMap = imageMap;
    }

    /**
     * Sets string image map.
     *
     * @param xpos string image map
     * @param ypos string image map
     * @return Block
     */
    public Block create(int xpos, int ypos) {
        Block b = new Block(new Rectangle(new Point(xpos, ypos), width, height));
        b.setLive(hitPoints);
        b.setImage(stringImageMap);
        b.setColor(stringColorMap);
        return b;
    }
}
