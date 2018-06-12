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

import be.yildizgames.module.graphic.camera.behavior.FixedCameraBehavior;
import be.yildizgames.module.graphic.camera.behavior.FpsCameraBehavior;
import be.yildizgames.module.graphic.camera.behavior.FreeFlyCameraBehavior;
import be.yildizgames.module.graphic.camera.behavior.RtsCameraBehavior;
import be.yildizgames.module.graphic.camera.behavior.TargetCameraBehavior;
import be.yildizgames.module.graphic.camera.behavior.TpsCameraBehavior;

/**
 * Provide the different predefined camera behaviors.
 * @author Grégory Van den Borre
 */
public enum  CameraBehaviors {

    FREEFLY(new FreeFlyCameraBehavior()),
    TPS(new TpsCameraBehavior()),
    FPS(new FpsCameraBehavior()),
    RTS(new RtsCameraBehavior()),

    /**
     * With this behavior, once the camera position and target position have been set, they are locked and can no longer be modified.
     */
    FIXED(new FixedCameraBehavior()),
    TARGET(new TargetCameraBehavior());

    private final CameraBehavior behavior;

    CameraBehaviors(CameraBehavior behavior) {
        this.behavior = behavior;
    }


    public CameraBehavior getBehavior() {
        return this.behavior;
    }
}
