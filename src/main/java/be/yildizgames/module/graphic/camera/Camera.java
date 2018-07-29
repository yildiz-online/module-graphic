/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.graphic.camera;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Axis;
import be.yildizgames.common.geometry.Point2D;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.util.BaseRegisterable;
import be.yildizgames.module.graphic.light.LensFlare;
import be.yildizgames.module.graphic.movable.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Physical camera representation, contains basic operations to set the position, direction,... to use more precise behavior, see camera behaviors.
 *
 * @author Grégory Van den Borre
 */
public abstract class Camera extends BaseRegisterable implements Movable {

    private Point3D relativePosition = Point3D.ZERO;

    /**
     * List of camera listeners.
     */
    private final List<CameraListener> listenerList = new ArrayList<>();

    /**
     * This node is the leading one, and is attached to root, no matter if cameraNode or targetNode is leading the camera move.
     */
    private final Node masterNode;

    private final Node cameraNode;

    /**
     * Tracked entity, if any.
     */
    private final Node targetNode;

    /**
     * relative position of the camera toward an object when reinitialized.
     */
    private Point3D offset = Point3D.ZERO;

    /**
     * Simple constructor.
     *  @param name Camera unique name.
     * @param cameraNode Node for cameraNode.
     * @param targetNode Node for targetNode.
     */
    protected Camera(final String name, Node masterNode, Node cameraNode, Node targetNode) {
        super(name);
        this.cameraNode = cameraNode;
        this.targetNode = targetNode;
        this.masterNode = masterNode;
        this.cameraNode.addChild(this);
    }

    /**
     * Set the camera relative position, this is used to set given a position as the base.
     *
     * @param p Position to set.
     */
   // final void setRelativePosition(final Point3D p) {
   //     this.lookAt(p.add(this.offset), p);
   // }

    /**
     * Add a listener to this camera.
     *
     * @param listener Listener to add.
     */
    final void addListener(final CameraListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * @return The camera current direction.
     */
    @Override
    public final Point3D getDirection() {
        return Point3D.normalize(this.targetNode.getPosition().subtract(this.getPosition()));
    }

    /**
     * An object further than the provided value will not be rendered.
     * @param far Maximum rendering distance.
     * @return This object for chaining.
     */
    public abstract Camera setFarClip(int far);

    /**
     * An object closer than the provided value will not be rendered.
     * @param near Minimum rendering distance.
     * @return This object for chaining.
     */
    public abstract Camera setNearClip(int near);

    /**
     * @return The camera current position.
     */
    public final Point3D getPosition() {
        return this.cameraNode.getPosition();
    }

    @Override
    public final Point3D getAbsolutePosition() {
        return this.cameraNode.getAbsolutePosition();
    }

    @Override
    public final Point3D getAbsoluteDirection() {
        return this.cameraNode.getAbsoluteDirection();
    }

    @Override
    public final void setPosition(float v, float v1, float v2) {
        this.cameraNode.setPosition(v, v1, v2);
    }

    @Override
    public final Movable getInternal() {
        return this;
    }

    @Override
    public final void attachTo(Movable movable) {
        this.cameraNode.attachTo(movable);
    }

    @Override
    public void attachToOptional(Movable movable) {
        this.cameraNode.attachToOptional(movable);
    }

    @Override
    public void setDirection(Point3D point3D) {
        //FIXME implements
    }

    @Override
    public void setDirection(float v, float v1, float v2) {
        //FIXME implements
    }

    @Override
    public void addOptionalChild(Movable movable) {
        this.masterNode.addOptionalChild(movable);
    }

    @Override
    public void addChild(Movable movable) {
        this.masterNode.addChild(movable);
    }

    @Override
    public void removeChild(Movable movable) {
        this.masterNode.removeChild(movable);
    }

    /**
     * Set the camera position. The listeners are notified.
     *
     * @param newPosition Camera new position.
     */
    public final void setPosition(final Point3D newPosition) {
        this.cameraNode.setPosition(newPosition);
    }

    /**
     * Set the camera targetNode position.
     *
     * @param target Camera targetNode position value.
     */
    public final void setTargetPosition(final Point3D target) {
        this.targetNode.setPosition(target);
    }

    public final void setTargetPosition(final float x, final float y, final float z) {
        this.setTargetPosition(Point3D.valueOf(x, y, z));
    }

    /**
     * Rotate the camera following X and Y coordinates. The listeners are notified.
     *
     * @param yaw   X rotation value.
     * @param pitch Y rotation value.
     */
    public final void rotate(final float yaw, final float pitch) {
        this.cameraNode.rotate(yaw, pitch);
    }

    public final void rotateTarget(final float yaw, final float pitch) {
        this.targetNode.rotate(yaw, pitch);
    }

    /**
     * Compute a point from a click on the screen.
     *
     * @param x Screen coordinates X.
     * @param y Screen coordinates Y.
     * @return The point in 3D world.
     */
    public abstract Point3D computeMoveDestination(int x, int y);

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
     * Call implementation to set the position.
     *
     * @param x    2D position X.
     * @param y    2D position Y.
     * @param axis Axis to retrieve 3D position.
     * @return The new camera position.
     */
    protected abstract Point3D setPositionImpl(final int x, final int y, final Axis axis);

    /**
     * Call implementation to set the position.
     *
     * @param posX New x position for the camera.
     * @param posY New y position for the camera.
     * @param posZ New z position for the camera.
     */
    protected abstract void setPositionImpl(final float posX, final float posY, final float posZ);

    /**
     * Remove a camera listener from this camera.
     *
     * @param listener Listener to remove.
     */
    public abstract void removeListener(LensFlare listener);

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

    public abstract void setAspectRatio(float ratio);

    public final Point3D getTargetPosition() {
        return this.targetNode.getPosition();
    }

    public void setRelativePosition(Point3D position) {
        this.relativePosition = position;
    }

    public Point3D getRelativePosition() {
        return relativePosition;
    }

    /**
     * The targetNode will move around the camera.
     */
    public final void initOrigin() {
        this.cameraNode.detachFromParent();
        this.targetNode.detachFromParent();
        this.cameraNode.attachTo(this.masterNode);
        this.targetNode.attachTo(this.cameraNode);
    }

    /**
     * The camera will follow the targetNode.
     */
    public final void initTarget() {
        this.cameraNode.detachFromParent();
        this.targetNode.detachFromParent();
        this.targetNode.attachTo(this.masterNode);
        this.cameraNode.attachTo(this.targetNode);
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
