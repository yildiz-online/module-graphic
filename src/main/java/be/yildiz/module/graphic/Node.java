/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.graphic;

import be.yildiz.common.collections.Maps;
import be.yildiz.common.gameobject.Movable;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;
import be.yildiz.common.vector.Quaternion;

import java.util.Map;
import java.util.Optional;

/**
 * A node is a movable object, all objects to move are attached to it.
 *
 * @author Grégry Van den Borre
 */
public abstract class Node implements Movable {

    /**
     * List of nodes by Id.
     */
    private static final Map<EntityId, Movable> nodes = Maps.newMap();

    /**
     * Node unique Id, optional.
     */
    private final EntityId id;

    private Movable parent;

    protected Node(final EntityId id, final Movable parent) {
        super();
        nodes.put(id, this);
        this.id = id;
        this.parent = parent;
    }

    protected Node(final Movable parent) {
        super();
        this.id = null;
        this.parent = parent;
    }

    /**
     * Get a node by its Id.
     *
     * @param id Id to get.
     * @return The matching node.
     */
    public static Movable getById(final EntityId id) {
        return nodes.get(id);
    }

    /**
     * @return The current object position.
     */
    public abstract Point3D getPosition();

    /**
     * Set the node position.
     *
     * @param position Node new position.
     */
    public abstract void setPosition(final Point3D position);

    /**
     * Scale the node.
     *
     * @param scale Scale factor.
     */
    public final void scale(float scale) {
        this.scale(scale, scale, scale);
    }

    /**
     * Scale the node.
     *
     * @param scaleX Width scale factor.
     * @param scaleY Height scale factor.
     * @param scaleZ Depth scale factor.
     */
    public abstract void scale(float scaleX, float scaleY, float scaleZ);


    /**
     * Translate the node in a direction.
     *
     * @param moveX Translation X value.
     * @param moveY Translation Y value.
     * @param moveZ Translation Z value.
     * @return the new node position after the translation.
     */
    public abstract Point3D translate(float moveX, float moveY, float moveZ);

    /**
     * Set the node direction.
     *
     * @param dirX Node direction X value.
     * @param dirY Node direction Y value.
     * @param dirZ Node direction Z value.
     */
    public abstract void setDirection(float dirX, float dirY, float dirZ);

    /**
     * @return The node facing direction.
     */
    public abstract Point3D getDirection();

    /**
     * Set the node direction.
     *
     * @param direction Node new direction.
     */
    public final void setDirection(final Point3D direction) {
        this.setDirection(direction.x, direction.y, direction.z);
    }

    /**
     * Set the objects attached to the node visible or invisible.
     *
     * @param visible <code>true</code> to set the objects attached to the node visible, false to hide them.
     */
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.show();
        } else {
            this.hide();
        }
    }

    /**
     * Set all object attached to the node visible.
     */
    public abstract void show();

    /**
     * Set all object attached to the node visible.
     */
    public abstract void hide();

    /**
     * Rotate the node on X and Y axis.
     *
     * @param yaw   Rotation on X axis value.
     * @param pitch Rotation on Y axis value.
     * @return The new direction vector after the rotation transformation.
     */
    public abstract Point3D rotate(float yaw, float pitch);

    /**
     * Rotate the node with a quaternion values.
     *
     * @param quatX Rotation X value.
     * @param quatY Rotation Y value.
     * @param quatZ Rotation Z value.
     * @param quatW Rotation W value.
     */
    public abstract void rotate(float quatX, float quatY, float quatZ, float quatW);

    /**
     * @return The node current orientation in a quaternion.
     */
    public abstract Quaternion getOrientation();

    /**
     * @return The position in the world, not the one relative to the parent.
     */
    public final Point3D getAbsolutePosition() {
        return this.parent.getAbsolutePosition().add(this.getPosition());
    }

    public final Point3D getAbsoluteDirection() {
        //FIXME values should be normalized before being added together?
        return this.parent.getAbsoluteDirection().add(this.getDirection());
    }

    /**
     * Delete the node and attached objects.
     */
    public final void delete() {
        Optional.ofNullable(this.id).ifPresent(nodes::remove);
        this.deleteImpl();
    }

    /**
     * Delete the node and attached objects.
     */
    protected abstract void deleteImpl();
}
