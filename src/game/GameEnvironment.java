package game;


/**
 * name- daniel bariudin
 * id- 307758334
 */

import collections.Collidable;
import collections.CollisionInfo;
import mathgeometry.Line;
import mathgeometry.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * The type game.GameLevel environment.
 */
public class GameEnvironment {

    private List<Collidable> allCollidable;

    /**
     * Instantiates a new game.GameLevel environment.
     */
    public GameEnvironment() {
        allCollidable = new ArrayList<>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        allCollidable.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        allCollidable.remove(c);
    }

    /**
     * Add the collidable.
     *
     * @param n the n
     */
    public void addTheCollidable(Collidable n) {
        allCollidable.add(n);
    }

    /**
     * Get closest collision collision info.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable currPos = null;
        Point n = null;
        for (int i = 0; i < allCollidable.size(); i++) {
            Point tempo = trajectory.closestIntersectionToStartOfLine(allCollidable.get(i).getCollisionRectangle());
            if (tempo == null) {
                continue;
            }
            if (n == null || trajectory.start().distance(n) > trajectory.start().distance(tempo)) {
                n = tempo;
                currPos = allCollidable.get(i);
            }


        }
        if (n == null) {
            return null;
        }
        return new CollisionInfo(n, currPos);

    }

    /**
     * Get paddle paddle.
     *
     * @return the paddle
     */
    public Collidable getPaddle() {
        return this.allCollidable.get(0);
    }
}
