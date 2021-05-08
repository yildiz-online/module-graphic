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

package be.yildizgames.module.graphic.billboard;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.color.Color;

import java.util.Objects;

/**
 * A billboard is a plane always facing the camera.
 *
 * @author Grégory Van den Borre
 */
public abstract class Billboard {

    /**
     * Plane width.
     */
    private float width;

    /**
     * Plane height.
     */
    private float height;

    /**
     * Current position.
     */
    private Point3D position = Point3D.ZERO;

    /**
     * Simple constructor.
     */
    protected Billboard() {
        super();
    }

    /**
     * Update the plane size.
     *
     * @param newWidth  New width.
     * @param newHeight New height.
     */
    public final void setSize(final float newWidth, final float newHeight) {
        if(newHeight <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0.");
        }
        if(newWidth <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0.");
        }
        this.width = newWidth;
        this.height = newHeight;
        this.setSizeImpl(newWidth, newHeight);
    }

    /**
     * @return The current position.
     */
    public final Point3D getPosition() {
        return this.position;
    }

    /**
     * Update the plane position.
     *
     * @param newPosition New position.
     */
    public final void setPosition(final Point3D newPosition) {
        Objects.requireNonNull(newPosition);
        this.position = newPosition;
        this.setPositionImpl(newPosition);
    }

    /**
     * @return The plane width.
     */
    public final float getWidth() {
        return this.width;
    }

    /**
     * @return The plane height.
     */
    public final float getHeight() {
        return this.height;
    }

    /**
     * Set the billboard individual color.
     *
     * @param color Color to set.
     */
    public abstract void setColor(Color color);

    /**
     * Set the position in implementation.
     *
     * @param position New position.
     */
    protected abstract void setPositionImpl(Point3D position);

    /**
     * Set the size in implementation.
     *
     * @param newWidth  New width.
     * @param newHeight New height.
     */
    protected abstract void setSizeImpl(float newWidth, float newHeight);
}
