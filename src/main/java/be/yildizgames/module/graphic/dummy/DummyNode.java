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

package be.yildizgames.module.graphic.dummy;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.geometry.Quaternion;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.module.graphic.movable.Node;

public class DummyNode extends Node {

    private Point3D position = Point3D.ZERO;

    protected DummyNode() {
        super(EntityId.WORLD, null);
    }

    @Override
    public void attachTo(Movable movable) {

    }

    @Override
    public void attachToOptional(Movable movable) {

    }

    @Override
    public void detachFromParent() {

    }

    @Override
    public Point3D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point3D position) {
        this.position = position;
    }

    @Override
    public void scale(float scaleX, float scaleY, float scaleZ) {

    }

    @Override
    public Point3D translate(float moveX, float moveY, float moveZ) {
        return Point3D.ZERO;
    }

    @Override
    public void setDirection(float dirX, float dirY, float dirZ) {

    }

    @Override
    public void addOptionalChild(Movable movable) {

    }

    @Override
    public void addChild(Movable movable) {

    }

    @Override
    public void removeChild(Movable movable) {

    }

    @Override
    public Movable getInternal() {
        return null;
    }

    @Override
    public Point3D getDirection() {
        return Point3D.ZERO;
    }

    @Override
    public void setPosition(float v, float v1, float v2) {
        this.position = Point3D.valueOf(v, v1, v2);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public Point3D rotate(float yaw, float pitch) {
        return Point3D.ZERO;
    }

    @Override
    public void rotate(float quatX, float quatY, float quatZ, float quatW) {

    }

    @Override
    public Quaternion getOrientation() {
        return new Quaternion(0,0,0,0);
    }

    @Override
    protected void deleteImpl() {

    }
}
