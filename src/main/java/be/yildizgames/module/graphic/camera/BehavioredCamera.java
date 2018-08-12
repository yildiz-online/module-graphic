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

import be.yildizgames.common.geometry.Point2D;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;

import java.util.Optional;

/**
 * Combine a behavior and a camera, when using this class, the camera will use the provided behavior.
 * The default is free fly.
 * @author Grégory Van den Borre
 */
public class BehavioredCamera {

    private final Camera camera;

    private CameraBehavior behavior;

    public BehavioredCamera(Camera camera) {
        super();
        this.camera = camera;
        this.changeBehavior(CameraBehaviors.FREEFLY);
    }

    public final Optional<EntityId> throwRay(Point2D coordinate) {
        return this.camera.throwRay(coordinate);
    }

    public final Optional<EntityId> throwRay(int x, int y) {
        return this.camera.throwRay(x, y);
    }

    public final void changeBehavior(CameraBehaviors behavior) {
        this.changeBehavior(behavior.getBehavior());
    }

    public final void changeBehavior(CameraBehavior behavior) {
        this.behavior = behavior;
        this.behavior.initialise(camera);
    }

    /**
     * Move the camera and the target at the same time.
     * @param destination New position to reach for the camera.
     */
    public final void move(Point3D destination) {
        this.behavior.move(this.camera, destination);
    }

    public final void lookAt(Point3D target) {
        this.behavior.lookAt(this.camera, target);
    }

    public final void setPosition(final Point3D newPosition) {
        //this.behavior.setPosition(this.camera, newPosition);
    }

    public final Point3D getPosition() {
        return this.camera.getPosition();
    }

    public final void rotate(float yaw, float pitch) {
        this.behavior.rotate(this.camera, yaw, pitch);
    }

    public final void setRelativePosition(Point3D position) {
        this.behavior.setRelativePosition(this.camera, position);
    }

    public final Point3D getDirection() {
        return this.camera.getDirection();
    }
}
