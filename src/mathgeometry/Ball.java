package mathgeometry;


/**
 * name- daniel bariudin
 * id- 307758334
 */

import biuoop.DrawSurface;
import collections.Sprite;
import collections.HitListener;
import collections.Collidable;
import collections.CollisionInfo;


import game.GameLevel;
import game.GameEnvironment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * partof.mathgeometry.Ball object.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity speed;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * Constractor.
     *
     * @param center center of ball
     * @param radius radius of ball
     * @param color  color of ball
     */
    public Ball(Point center, int radius, Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = radius;
        this.color = color;
        this.speed = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
        hitListeners = new ArrayList<>();
    }

    /**
     * Constractor.
     *
     * @param x      point x of center of the ball
     * @param y      point y of center of the ball
     * @param radius radius of ball
     * @param color  color of ball
     */
    public Ball(double x, double y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * get x value.
     *
     * @return x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get y value.
     *
     * @return y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get radius.
     *
     * @return radius size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get color of the ball.
     *
     * @return color color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        this.gameEnvironment = gameEnvironment;
        return gameEnvironment;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.radius);
        d.setColor(color);
        d.fillCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed(double dt) {
        Collidable paddle = this.gameEnvironment.getPaddle();
        if (this.isTheBallIn(paddle)) {
            this.moveTheBallOut(paddle);
        }
        this.moveOneStep(dt);
    }

    /**
     * set velocity of the ball.
     *
     * @param v v
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * get velocity of the ball.
     *
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * moving the ball.
     *
     * @param dt the dt
     */
    public void moveOneStep(double dt) {
        Velocity v = this.speed.adjustDt(dt);
        Point nextPoint = this.speed.applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPoint);

        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            this.center = moveNextToCollision(collisionInfo.collisionPoint(), this.speed);
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), getVelocity()));
        } else {
            this.center = nextPoint;
        }
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironments the game.GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironments) {
        this.gameEnvironment = gameEnvironments;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * Is the ball in boolean.
     *
     * @param paddle the paddle
     * @return the boolean
     */
    public boolean isTheBallIn(Collidable paddle) {
        if (this.getX() >= paddle.getCollisionRectangle().getUpperLeft().getX() && this.getX()
                < paddle.getCollisionRectangle().getUpperLeft().getX()
                + paddle.getCollisionRectangle().getWidth()) {
            if (this.getY() >= paddle.getCollisionRectangle().getUpperLeft().getY()
                    && this.getY() < paddle.getCollisionRectangle().getUpperLeft().getY()
                    + paddle.getCollisionRectangle().getHeight()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move the ball out.
     *
     * @param paddle the paddle
     */
    public void moveTheBallOut(Collidable paddle) {
        if (isTheBallIn(paddle)) {
            this.center = new Point(paddle.getCollisionRectangle().getUpperLeft().getX()
                    + paddle.getCollisionRectangle().getWidth() / 2,
                    paddle.getCollisionRectangle().getUpperLeft().getY() - getSize());

            this.setVelocity(this.getVelocity().getDx(), (-1) * this.getVelocity().getDy());
        }
    }

    /**
     * Move next to collision point.
     *
     * @param collisionPoint the collision point
     * @param currVelocity   the curr velocity
     * @return the point
     */
    public Point moveNextToCollision(Point collisionPoint, Velocity currVelocity) {
        double x;
        double y;

        if (currVelocity.getDx() > 0) {
            x = collisionPoint.getX() - 2;
        } else {

            x = collisionPoint.getX() + 2;
        }
        if (currVelocity.getDy() > 0) {
            y = collisionPoint.getY() - 2;
        } else {
            y = collisionPoint.getY() + 2;
        }
        return new Point(x, y);
    }


//    @Override
//    public void addHitListener(HitListener hl) {
//        hitListeners.add(hl);
//    }

//    @Override
//    public void removeHitListener(HitListener hl) {
//        if (hitListeners.equals(0)) {
//            return;
//        }
//        for (int i = 0; i < hitListeners.size(); i++) {
//            if (hitListeners.get(i) == hl)
//                hitListeners.remove(hl);
//        }
//    }

}