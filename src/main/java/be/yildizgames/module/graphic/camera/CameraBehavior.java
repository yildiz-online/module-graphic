/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
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

import be.yildizgames.common.geometry.Point3D;

/**
 * Define how the camera will behave when receiving position and orientation change request.
 */
public interface CameraBehavior {

    /**
     * The camera will look to a given target.
     * @param camera Camera to use.
     * @param target Target to look at.
     */
    void lookAt(Camera camera, Point3D target);

    /**
     * Update the camera position.
     * @param camera Camera to use.
     * @param newPosition New position to set.
     */
    void setPosition(Camera camera, Point3D newPosition);

    /**
     * Move the camera to a given destination.
     * @param camera Camera to use.
     * @param destination destination to go to.
     */
    void move(Camera camera, Point3D destination);

    /**
     * Rotate on the Y axis (vertical one).
     * @param camera Camera to use.
     * @param yaw Angle to rotate on Y axis(vertical), in radians.
     * @param pitch Angle to rotate on X axis (vertical), in radians
     */
    void rotate(Camera camera, float yaw, float pitch);

    void setRelativePosition(Camera camera, Point3D position);

    /**
     * Initialize, or reinitialize the camera.
     * @param camera Camera to use.
     */
    void initialise(Camera camera);
}
