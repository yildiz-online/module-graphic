/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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
import be.yildizgames.module.graphic.GraphicObject;
import be.yildizgames.module.graphic.material.Material;

public class DummyGraphicObject extends GraphicObject {

    @Override
    protected void setMaterialImpl(Material newMaterial) {
        //Expected empty implementation.
    }

    @Override
    protected void castShadowImpl(boolean cast) {
        //Expected empty implementation.
    }

    @Override
    protected void showImpl() {
        //Expected empty implementation.
    }

    @Override
    protected void hideImpl() {
        //Expected empty implementation.
    }

    @Override
    public GraphicObject setRenderBehind() {
        return this;
    }

    @Override
    public GraphicObject setRenderingDistance(int distance) {
        return this;
    }

    @Override
    public Point3D getScaleSize() {
        return Point3D.ZERO;
    }

    @Override
    public GraphicObject scale(float x, float y, float z) {
        return this;
    }

    @Override
    public void delete() {
        //Expected empty implementation.
    }

    @Override
    public GraphicObject setParameter(int index, float v1, float v2, float v3, float v4) {
        return this;
    }

    @Override
    public GraphicObject setUnpickable() {
        return this;
    }

    @Override
    public void rotate(float yaw, float pitch) {
        //Expected empty implementation.
    }

    @Override
    public void lookAt(Point3D target) {
        //Expected empty implementation.
    }

    @Override
    public void rotate(float x, float y, float z, float w) {
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
    public void setDirection(Point3D point3D) {
        //Expected empty implementation.
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
    public Movable getInternal() {
        return null;
    }
}
