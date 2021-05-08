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

package be.yildizgames.module.graphic;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.graphic.material.Material;

import java.util.Objects;

/**
 * Base class for graphic objects.
 *
 * @author Grégory Van den Borre
 */
public abstract class GraphicObject implements Movable {

    /**
     * <true> If the object cast shadows.
     */
    private boolean castingShadow;

    /**
     * <true> Will render the object, <false> the object will never be visible.
     */
    private boolean visible;

    /**
     * Material used for this graphic object.
     */
    private Material material;

    /**
     * Simple constructor.
     */
    protected GraphicObject() {
        super();
        this.visible = true;
    }

    /**
     * Set the object visible on screen.
     */
    public final void show() {
        if (!this.visible) {
            this.visible = true;
            this.showImpl();
        }
    }

    /**
     * Set the object not visible on screen.
     */
    public final void hide() {
        if (this.visible) {
            this.visible = false;
            this.hideImpl();
        }
    }

    public final GraphicObject setCastShadow(final boolean cast) {
        this.castingShadow = cast;
        this.castShadowImpl(cast);
        return this;
    }

    public final boolean isCastingShadow() {
        return this.castingShadow;
    }

    public final boolean isVisible() {
        return this.visible;
    }

    public final GraphicObject setMaterial(final Material newMaterial) {
        Objects.requireNonNull(newMaterial);
        if (!newMaterial.equals(this.material)) {
            this.material = newMaterial;
            this.setMaterialImpl(newMaterial);
        }
        return this;
    }

    /**
     * Set the material in implementation.
     *
     * @param newMaterial New material to use.
     */
    protected abstract void setMaterialImpl(Material newMaterial);

    /**
     * Set the object casting shadow in implementation.
     *
     * @param cast <code>true</code> to cast shadow, <code>false</code> to stop
     *             casting.
     */
    protected abstract void castShadowImpl(boolean cast);

    /**
     * Show the object in implementation.
     */
    protected abstract void showImpl();

    /**
     * Hide the object in implementation.
     */
    protected abstract void hideImpl();

    public abstract GraphicObject setRenderBehind();

    public abstract GraphicObject setRenderingDistance(int distance);

    public abstract Point3D getScaleSize();

    public abstract GraphicObject scale(float x, float y, float z);

    public abstract GraphicObject setParameter(int index, float v1, float v2, float v3, float v4);

    public abstract GraphicObject setUnpickable();

    public abstract void rotate(float yaw, float pitch);

    public abstract void lookAt(Point3D target);

    public abstract void rotate(float x, float y, float z, float w);

}
