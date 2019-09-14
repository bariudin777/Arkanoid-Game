package collections; /**
 *name- daniel bariudin
 * id- 307758334
 */


import mathgeometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param n       the n
     * @param currPos the curr pos
     */
    public CollisionInfo(Point n, Collidable currPos) {
        this.collisionObject = currPos;
        this.collisionPoint = n;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisionObject;
    }
}
