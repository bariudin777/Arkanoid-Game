package mathgeometry;

import collections.Collidable;
import collections.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.GameLevel;
import collections.HitListener;
import collections.HitNotifier;
/**
 * name- daniel bariudin
 * id- 307758334
 */

/**
 * The type mathgeometry.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private int valid;
    private Map<Integer, Image> imgMap;
    private Map<Integer, Color> colMap;
    private List<HitListener> hitListeners;
    private Block block;
    private Color colorBar;

    /**
     * Instantiates a new mathgeometry.Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        rectangle = rect;
        valid = 0;
        this.colMap = new HashMap<Integer, Color>();
        this.imgMap = new HashMap<Integer, Image>();
        this.hitListeners = new ArrayList<>();

    }

    /**
     * Sets img map.
     *
     * @param mapOf the map of
     */
    public void setImgMap(Map<Integer, Image> mapOf) {
        this.imgMap = mapOf;
    }

    /**
     * Sets col map.
     *
     * @param mapOf the col map
     */
    public void setColMap(Map<Integer, Color> mapOf) {
        this.colMap = mapOf;
    }

    /**
     * Instantiates a new Block.
     *
     * @param block the block
     */
    public Block(Block block) {
        this.block = block;
    }

    /**
     * Instantiates a new mathgeometry.Block.
     *
     * @param upperLeft the upper left
     * @param hight     the hight
     * @param wength    the wength
     */
    public Block(Point upperLeft, int hight, int wength) {
        this(new Rectangle(upperLeft, wength, hight));
    }

    /**
     * getCollisionRectangle.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Weigh double.
     *
     * @return the double
     */
    public double weigh() {
        return this.weigh();
    }

    /**
     * High double.
     *
     * @return the double
     */
    public double high() {
        return this.high();
    }

    /**
     * Upper left double.
     *
     * @return the double
     */
    public double upperLeft() {
        return this.upperLeft();

    }

    /**
     * Sets upper left.
     *
     * @param x the x
     * @param y the y
     */
    public void setUpperLeft(double x, double y) {
        this.setUpperLeft(x, y);
    }

    /**
     * Sets live.
     *
     * @param live the valid
     */
    public void setLive(int live) {
        if (live >= 0) {
            this.valid = live;
        }
    }

    /**
     * Gets live.
     *
     * @return the live
     */
    public int getLive() {
        return valid;
    }

    /**
     * Sets live.
     *
     * @param collisionPoint  the mathgeometry.Point
     * @param currentVelocity the currentVelocity
     * @param hitter          the Ball
     * @return valid
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (valid > 0) {
            valid--;
        }
        List<Line> sides = rectangle.blockSide();
        Line trajectory = new Line(currentVelocity.prevPoint(collisionPoint), collisionPoint);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (trajectory.isIntersecting(sides.get(2)) || trajectory.isIntersecting(sides.get(3))) {
            dx *= (-1);
        }

        if (trajectory.isIntersecting(sides.get(0)) || trajectory.isIntersecting(sides.get(1))) {
            dy *= (-1);
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }


    @Override
    public void drawOn(DrawSurface d) {

        // TODO - handle map correctly
        if (this.imgMap.containsKey(this.valid - 1)) {
            d.drawImage((int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    this.imgMap.get(this.valid - 1));
        } else if (this.colMap.containsKey(valid - 1)) {
            d.setColor(this.colMap.get(this.valid - 1));
            d.fillRectangle((int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()), (int) Math.round(this.rectangle.getHeight()));
        } else if (this.imgMap.containsKey(0)) {
            d.drawImage((int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    this.imgMap.get(0));
        } else if (this.colMap.containsKey(0)) {
            d.setColor(this.colMap.get(this.valid));
            d.fillRectangle((int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()), (int) Math.round(this.rectangle.getHeight()));
        }

        // TODO - handle
        if (this.colMap.containsKey(-1)) {
            d.setColor(this.colMap.get(-1));
            d.drawRectangle((int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()), (int) Math.round(this.rectangle.getHeight()));
        }


    }
//
//        d.setColor(color);
//        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
//                (int) rectangle.getWidth(), (int) rectangle.getHeight());
//        d.setColor(Color.BLACK);
//        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
//                (int) rectangle.getWidth(), (int) rectangle.getHeight());
//        d.setColor(Color.BLACK);
//        int midX = ((int) rectangle.getUpperLeft().getX() + (int) rectangle.getUpperLeft().getX()
//                + (int) rectangle.getWidth()) / 2;
//        int midY = ((int) rectangle.getUpperLeft().getY() + (int) rectangle.getUpperLeft().getY()
//                + (int) rectangle.getHeight()) / 2 + 5;
//        /*
//        if (valid < 0) {
//          removeHitListener();
//        }
//        */
//        //  if (valid >= 0) {
//        //    d.drawText(midX, midY, "" + valid, 16);
//        //}
//    }

    @Override
    public void timePassed(double dt) {
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        //add the item to the list
        hitListeners.add(hl);
    }

    /**
     * Gets size.
     *
     * @param list the list
     * @return the size
     */
    public int getSize(List list) {
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            counter++;
        }
        return counter;
    }

    @Override
    public void removeHitListener(HitListener hl) {
        for (int i = 0; i < getSize(hitListeners); i++) {
            if (hitListeners.get(i) == hl) {
                //remove item from the list
                hitListeners.remove(hl);
            }
        }
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.rectangle = new Rectangle(this.rectangle.getUpperLeft(), width, this.rectangle.getHeight());
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.rectangle = new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), height);
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Map<Integer, Color> color) {
        this.colMap = color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.colorBar = color;
    }

//    public Color setColorBar(Color colorBar) {
//        for (int i = 0; i < colMap.size(); i++) {
//            colMap.put(i, colorBar);
//        }
//        return colMap.get(colorBar);
//    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Map<Integer, Image> image) {
        this.imgMap = image;
    }

    /**
     * Copy block.
     *
     * @return the block
     */
    public Block copy() {
        Block b = new Block(rectangle);
        b.setLive(valid);
        b.setImage(imgMap);
        b.setColor(colMap);
        return b;
    }
}






































