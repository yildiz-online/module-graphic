//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic;

import be.yildiz.common.Rectangle;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.util.BaseRegisterable;
import be.yildiz.common.vector.Axis;
import be.yildiz.common.vector.Point2D;
import be.yildiz.common.vector.Point3D;

import java.util.List;
import java.util.Optional;

/**
 * Physical camera representation, contains basic operations to set the position, direction,... to use more precise behavior, see camera behaviors.
 *
 * @author Grégory Van den Borre
 */
public abstract class AbstractCamera extends BaseRegisterable {

    /**
     * List of camera listeners.
     */
    private final List<CameraListener> listenerList = Lists.newList();
    /**
     * Current camera position.
     */
    private Point3D position = Point3D.ZERO;
    /**
     * Current camera direction.
     */
    private Point3D direction = Point3D.ZERO;
    /**
     * Tracked entity, if any.
     */
    private Optional<Node> tracked = Optional.empty();

    /**
     * relative position of the camera toward an object when reinitialized.
     */
    private Point3D offset = Point3D.ZERO;

    /**
     * Simple constructor.
     *
     * @param name Camera unique name.
     */
    protected AbstractCamera(final String name) {
        super(name);
    }

    /**
     * Set the camera relative position, this is used to set given a position as the base.
     *
     * @param p Position to set.
     */
    public final void setRelativePosition(final Point3D p) {
        this.lookAt(p.add(this.offset), p);
    }

    /**
     * Add a listener to this camera.
     *
     * @param listener Listener to add.
     */
    public final void addListener(final CameraListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * @return The camera current direction.
     */
    public final Point3D getDirection() {
        return this.direction;
    }

    /**
     * Set the camera direction. The listeners are notified.
     *
     * @param newDirection New direction.
     */
    public final void setDirection(final Point3D newDirection) {
        this.setDirection(newDirection.x, newDirection.y, newDirection.z);
    }

    /**
     * Synchronize the camera direction with the camera implementation direction.
     */
    final void refreshDirection() {
        this.direction = this.getDirectionImpl();
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * @return The camera current position.
     */
    public final Point3D getPosition() {
        return this.position;
    }

    /**
     * Set the camera position. The listeners are notified.
     *
     * @param newPosition Camera new position.
     */
    public final void setPosition(final Point3D newPosition) {
        this.position = newPosition;
        this.setPositionImpl(this.position.x, this.position.y, this.position.z);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Set the camera target position.
     *
     * @param target Camera target position value.
     */
    public final void lookAt(final Point3D target) {
        this.direction = this.lookAtImpl(target);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Set the camera position. The listeners are notified.
     *
     * @param posX Camera position x value.
     * @param posY Camera position y value.
     * @param posZ Camera position z value.
     */
    public final void setPosition(final float posX, final float posY, final float posZ) {
        this.setPosition(Point3D.xyz(posX, posY, posZ));
    }

    /**
     * Set the camera position without modifying Y value. The listeners are notified.
     *
     * @param newPosition Camera new position.
     * @return The camera position.
     */
    public final Point3D setXZPosition(final Point3D newPosition) {
        this.setPosition(newPosition.x, this.position.y, newPosition.z);
        return this.position;
    }

    /**
     * Set the position from 2D coordinates. The listeners are notified.
     *
     * @param newPosition 2D position.
     * @param axis        Axis to use to retrieve 3D position.
     */
    public final void setPosition(final Point2D newPosition, final Axis axis) {
        this.position = this.setPositionImpl(newPosition, axis);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Move the camera following the camera implementation. The listeners are notified.
     *
     * @param moveVector Vector used to move the camera.
     */
    public final void move(final Point3D moveVector) {
        this.move(moveVector.x, moveVector.y, moveVector.z);
    }

    /**
     * Move the camera following the camera implementation. The listeners are notified.
     *
     * @param moveX X value to move the camera.
     * @param moveY Y value to move the camera.
     * @param moveZ Z value to move the camera.
     */
    public final void move(final float moveX, final float moveY, final float moveZ) {
        this.position = this.moveImpl(moveX, moveY, moveZ);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Move the camera on 2 axis only, Y value is kept unchanged.
     *
     * @param x X value to move the camera.
     * @param z Z value to move the camera.
     */
    public final void moveXZ(final float x, final float z) {
        float y = this.position.y;
        Point3D p = this.moveImpl(x, 0, z);
        this.setPosition(p.x, y, p.z);
    }

    /**
     * Set the camera direction. The listeners are notified.
     *
     * @param dirX Direction X value.
     * @param dirY Direction Y value.
     * @param dirZ Direction Z value.
     */
    public final void setDirection(final float dirX, final float dirY, final float dirZ) {
        this.direction = this.setOrientationImpl(dirX, dirY, dirZ);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Set the camera position and target position.
     *
     * @param pos    Camera position.
     * @param target Camera target position.
     */
    public final void lookAt(final Point3D pos, final Point3D target) {
        this.setPosition(pos.x, pos.y, pos.z);
        this.lookAt(target);
    }

    /**
     * Rotate the camera following a point coordinates. The listeners are notified.
     *
     * @param rotation Coordinates to use to rotate.
     */
    public final void rotate(final Point2D rotation) {
        this.rotate(rotation.getX(), rotation.getY());
    }

    /**
     * Rotate the camera following X and Y coordinates. The listeners are notified.
     *
     * @param yaw   X rotation value.
     * @param pitch Y rotation value.
     */
    public final void rotate(final float yaw, final float pitch) {
        this.direction = this.rotateImpl(yaw, pitch);
        for (CameraListener l : this.listenerList) {
            l.update(this.position, this.direction);
        }
    }

    /**
     * Compute a point from a click on the screen.
     *
     * @param coordinate Screen coordinates.
     * @return The point in 3D world.
     */
    public abstract Point3D computeMoveDestination(Point2D coordinate);

    /**
     * Throw a rectangle and return all entity id contained in it.
     *
     * @param rectangle Rectangle to throw.
     * @return All entity id contained in the rectangle.
     */
    public abstract List<EntityId> throwPlaneRay(Rectangle rectangle);

    /**
     * Throw a ray to get the id of the first entity hit.
     *
     * @param coordinate Coordinates to send the ray.
     * @return The entity found.
     */
    public final Optional<EntityId> throwRay(Point2D coordinate) {
        return this.throwRay(coordinate.getX(), coordinate.getY());
    }

    public abstract Optional<EntityId> throwRay(int x, int y);

    /**
     * Rotate the camera along its X axis.
     *
     * @param angle Angle to rotate, in radian.
     */
    protected abstract void yaw(final float angle);

    /**
     * Call the rotation in implementation.
     *
     * @param yaw   Rotation X coordinates.
     * @param pitch Rotation Y coordinates.
     * @return The new camera direction.
     */
    protected abstract Point3D rotateImpl(final float yaw, final float pitch);

    /**
     * Set looking at in implementation.
     *
     * @param target Target position
     * @return The new camera direction.
     */
    protected abstract Point3D lookAtImpl(Point3D target);

    /**
     * Call the move in implementation.
     *
     * @param moveX X value to move.
     * @param moveY Y value to move.
     * @param moveZ Z value to move.
     * @return The new camera position.
     */
    protected abstract Point3D moveImpl(final float moveX, final float moveY, final float moveZ);

    /**
     * Call implementation to set the direction.
     *
     * @param x New direction X value.
     * @param y New direction Y value.
     * @param z New direction Z value.
     * @return The new camera direction.
     */
    protected abstract Point3D setOrientationImpl(final float x, float y, float z);

    /**
     * Call implementation to set the position.
     *
     * @param newPosition 2D position.
     * @param axis        Axis to retrieve 3D position.
     * @return The new camera position.
     */
    protected abstract Point3D setPositionImpl(final Point2D newPosition, final Axis axis);

    /**
     * Call implementation to set the position.
     *
     * @param posX New x position for the camera.
     * @param posY New y position for the camera.
     * @param posZ New z position for the camera.
     */
    protected abstract void setPositionImpl(final float posX, final float posY, final float posZ);

    /**
     * @return The camera current direction in the implementation.
     */
    protected abstract Point3D getDirectionImpl();

    /**
     * Remove a camera listener from this camera.
     *
     * @param listener Listener to remove.
     */
    public abstract void removeListener(LensFlare listener);

    /**
     * Stop auto tracking an entity or a position.
     */
    public final void stopAutoTrack() {
        this.tracked = Optional.empty();
        this.stopAutoTrackImpl();
    }

    /**
     * Implementation specific stop auto track.
     */
    protected abstract void stopAutoTrackImpl();

    /**
     * Auto track a given entity, camera will always target that entity.
     *
     * @param e Entity to track.
     */
    public final void autoTrack(final Node e) {
        this.tracked = Optional.of(e);
        this.autoTrackImpl(e);
    }

    /**
     * Implementation specific auto track.
     *
     * @param node Node to track.
     */
    protected abstract void autoTrackImpl(Node node);

    /**
     * Auto track a given position, camera will always target that position.
     *
     * @param position Position to target.
     */
    public abstract void autoTrack(Point3D position);

    /**
     * Stop auto tracking an entity.
     *
     * @param node Node to stop to track.
     */
    public final void stopAutoTrack(final Node node) {
        if (node.equals(this.tracked.get())) {
            this.stopAutoTrack();
        }
    }

    /**
     * @return The camera offset.
     */
    public final Point3D getOffset() {
        return this.offset;
    }

    /**
     * Set a new position offset to apply when the position is relative to another.
     *
     * @param offset Offset to set.
     */
    public final void setOffset(final Point3D offset) {
        this.offset = offset;
    }

    /**
     * @return <code>true</code> if camera is tracking an entity.
     */
    public final boolean isAutoTrack() {
        return this.tracked.isPresent();
    }

    /**
     * @return The position of the tracked entity or camera position if none.
     */
    public final Point3D getTrackPosition() {
        if (this.isAutoTrack()) {
            return this.tracked.get().getPosition();
        } else {
            return this.getPosition();
        }
    }

    /**
     * Possible compositor.
     *
     * @author Grégory Van den Borre
     */
    public enum Compositor {

        /**
         * Glow compositor.
         */
        GLOW("Glow"),

        /**
         * Bloom compositor.
         */
        BLOOM("Bloom"),

        /**
         * Black and white compositor.
         */
        BLACK_AND_WHITE("B&W"),

        /**
         * HDR compositor.
         */
        HDR("HDR");

        /**
         * Compositor name.
         */
        private final String name;

        /**
         * Full construtor.
         *
         * @param name Name for the compositor.
         */
        Compositor(final String name) {
            this.name = name;
        }

        /**
         * @return The compositor name.
         */
        public String getName() {
            return this.name;
        }
    }
}
