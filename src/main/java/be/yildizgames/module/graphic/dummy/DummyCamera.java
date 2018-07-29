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

import be.yildizgames.common.geometry.Axis;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.graphic.camera.Camera;
import be.yildizgames.module.graphic.light.LensFlare;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DummyCamera extends Camera {

    /**
     * Simple constructor.
     */
    public DummyCamera() {
        super(StringUtil.buildRandomString("dummycamera"), new DummyNode(), new DummyNode(), new DummyNode());
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
    public Point3D computeMoveDestination(int x, int y) {
        return Point3D.ZERO;
    }

    @Override
    public List<EntityId> throwPlaneRay(Rectangle rectangle) {
        return new ArrayList<>();
    }

    @Override
    public Optional<EntityId> throwRay(int x, int y) {
        return Optional.empty();
    }

    @Override
    protected Point3D setPositionImpl(int x, int y, Axis axis) {
        return Point3D.ZERO;
    }

    @Override
    protected void setPositionImpl(float posX, float posY, float posZ) {

    }

    @Override
    public void removeListener(LensFlare listener) {

    }

    @Override
    public void setAspectRatio(float ratio) {

    }

    @Override
    public void detachFromParent() {

    }

    @Override
    public void delete() {

    }
}
