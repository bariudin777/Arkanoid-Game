package mathgeometry;

import game.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collections.Collidable;
import collections.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * name- daniel bariudin
 * id- 307758334
 */

/**
 * The type mathgeometry.Paddle.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Line partOne;
    private Line partTow;
    private Line partThree;
    private Line partFore;
    private Line partFive;
    private Color color;
    private double limRight;
    private double limLeft;
    private int speed;

    /**
     * Instantiates a new mathgeometry.Paddle.
     *
     * @param rectangle the rectangle
     * @param color     the color
     * @param speed      the speed
     * @param limLeft   the lim left
     * @param limRight  the lim right
     * @param keyboard  the keyboard
     */
    public Paddle(Rectangle rectangle, Color color, int speed, double limLeft,
                  double limRight, KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
        this.limLeft = limLeft;
        this.limRight = limRight;
        this.keyboard = keyboard;
        this.setParts();
    }

    /**
     * Sets parts.
     */
    public void setParts() {
        Point start = new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
        Point end = new Point(start.getX() + rectangle.getWidth() / 5, start.getY());
        partOne = new Line(start, end);
        start = end;
        end = new Point(start.getX() + rectangle.getWidth() / 5, start.getY());
        partTow = new Line(start, end);
        start = end;
        end = new Point(start.getX() + rectangle.getWidth() / 5, start.getY());
        partThree = new Line(start, end);
        start = end;
        end = new Point(start.getX() + rectangle.getWidth() / 5, start.getY());
        partFore = new Line(start, end);
        start = end;
        end = new Point(start.getX() + rectangle.getWidth() / 5, start.getY());
        partFive = new Line(start, end);
    }

    /**
     * Move left.
     *
     * @param dt the dt
     */
    public void moveLeft(double dt) {
        int spd = (int) (Math.floor(this.speed));
        if (this.rectangle.getUpperLeft().getX() - speed <= limLeft) {
            rectangle = new Rectangle(new Point(limLeft, rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
            return;
        }
        rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() - speed,
                rectangle.getUpperLeft().getY()),
                rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Move right.
     *
     * @param dt the dt
     */
    public void moveRight(double dt) {
//        int borderSize = 5;
        int spd = (int) (Math.floor(this.speed * dt));
        if (this.rectangle.getUpperLeft().getX() + rectangle.getWidth() + speed >= limRight) {
            rectangle = new Rectangle(new Point(limRight - rectangle.getWidth(), rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
            return;
        }
        rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() + speed,
                rectangle.getUpperLeft().getY()),
                rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    // collections.Sprite
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        this.setParts();
    }

    /**
     * @param d DrawSurface;
     */
    public void drawOn(DrawSurface d) {

        d.setColor(color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.GRAY);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());


    }


    /**
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), 0);
    }

    /**
     * Sets parts.
     *
     * @param collisionPoint  the mathgeometry.Point
     * @param currentVelocity the keyboard
     * @param hitter          the Ball.
     * @return setVelocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Line> sides = rectangle.blockSide();
        Line trajectory = new Line(collisionPoint, currentVelocity.applyToPoint(collisionPoint));
        Velocity newVelocity;
        int ballSpeed = 5;


        if (trajectory.isIntersecting(partOne)) {
            newVelocity = Velocity.fromAngleAndSpeed(300, ballSpeed);
        } else if (trajectory.isIntersecting(partTow)) {
            newVelocity = Velocity.fromAngleAndSpeed(330, ballSpeed);
        } else if (trajectory.isIntersecting(partThree)) {
            newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        } else if (trajectory.isIntersecting(partFore)) {
            newVelocity = Velocity.fromAngleAndSpeed(30, ballSpeed);
        } else if (trajectory.isIntersecting(partFive)) {
            newVelocity = Velocity.fromAngleAndSpeed(60, ballSpeed);
            // if hitting the sides
        } else {
            newVelocity = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }

        return newVelocity;

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * Sets speed.
     *
     * @param i the speed
     *
     */
    public void setSpeed(int i) {
        this.speed = i;
    }
}