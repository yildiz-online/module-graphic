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

package be.yildizgames.module.graphic.camera.behavior;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.graphic.camera.Camera;
import be.yildizgames.module.graphic.camera.CameraBehavior;

/**
 * With this behavior, the camera position and target can only be set once, after that the position is locked.
 */
public class FixedCameraBehavior implements CameraBehavior {

    private boolean positionLocked;

    private boolean targetLocked;

    public FixedCameraBehavior() {
        super();
    }

    @Override
    public final void lookAt(final Camera camera, final Point3D target) {
        if(!targetLocked) {
            camera.setTargetPosition(target);
            targetLocked = true;
        }
    }

    @Override
    public final void setPosition(final Camera camera, final Point3D newPosition) {
        if(!positionLocked) {
            camera.setPosition(newPosition);
            positionLocked = true;
        }
    }

    public final void move(final Camera camera, final Point3D target) {
        //Does nothing.
    }

    @Override
    public void rotate(Camera camera, float yaw, float pitch) {
        //Does nothing.
    }

    @Override
    public final void setRelativePosition(final Camera camera, final Point3D position) {
        //Does nothing.
    }

    @Override
    public void initialise(Camera camera) {
        camera.initOrigin();
    }

}
