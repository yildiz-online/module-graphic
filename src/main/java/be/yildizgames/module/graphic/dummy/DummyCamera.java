/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.module.graphic.dummy;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.graphic.camera.Camera;
import be.yildizgames.module.graphic.light.LensFlare;

public class DummyCamera extends Camera {

    /**
     * Simple constructor.
     */
    public DummyCamera() {
        super(StringUtil.buildRandomString("dummycamera"));
    }

    @Override
    public Camera setFarClip(int far) {
        return this;
    }

    @Override
    public Camera setNearClip(int near) {
        return this;
    }

    @Override
    public void setTargetPosition(Point3D target) {
        //Expected empty implementation.
    }

    @Override
    public void rotateTarget(float yaw, float pitch) {
        //Expected empty implementation.
    }

    @Override
    public void removeListener(LensFlare listener) {
        //Expected empty implementation.
    }

    @Override
    public void setAspectRatio(float ratio) {
        //Expected empty implementation.
    }

    @Override
    public Point3D getTargetPosition() {
        return Point3D.ZERO;
    }

    @Override
    public void initOrigin() {
        //Expected empty implementation.
    }

    @Override
    public void initTarget() {
        //Expected empty implementation.
    }

    @Override
    public void rotate(float yaw, float pitch) {
        //Expected empty implementation.
    }

    @Override
    public void attachTo(Movable movable) {
        //Expected empty implementation.
    }

    @Override
    public void attachToOptional(Movable movable) {
        //Expected empty implementation.
    }

    @Override
    public void detachFromParent() {
        //Expected empty implementation.
    }

    @Override
    public Point3D getPosition() {
        return Point3D.ZERO;
    }

    @Override
    public void setPosition(Point3D point3D) {
        //Expected empty implementation.
    }

    @Override
    public Point3D getAbsolutePosition() {
        return Point3D.ZERO;
    }

    @Override
    public Point3D getDirection() {
        return Point3D.BASE_DIRECTION;
    }

    @Override
    public Point3D getAbsoluteDirection() {
        return Point3D.BASE_DIRECTION;
    }

    @Override
    public void setPosition(float v, float v1, float v2) {
        //Expected empty implementation.
    }

    @Override
    public void setDirection(float v, float v1, float v2) {
        //Expected empty implementation.
    }

    @Override
    public void addOptionalChild(Movable movable) {
        //Expected empty implementation.
    }

    @Override
    public void addChild(Movable movable) {
        //Expected empty implementation.
    }

    @Override
    public void removeChild(Movable movable) {
        //Expected empty implementation.
    }

    @Override
    public void delete() {
        //Expected empty implementation.
    }
}
