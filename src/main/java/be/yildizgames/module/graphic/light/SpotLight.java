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

package be.yildizgames.module.graphic.light;

import be.yildizgames.common.geometry.Point3D;

/**
 * A SpotLight is a light with a spot shape, emitting in a direction.
 *
 * @author Grégory Van den Borre
 */
public abstract class SpotLight extends Light {

    /**
     * Light direction.
     */
    private Point3D direction;

    /**
     * Full constructor.
     *
     * @param name           Light name, must be unique.
     * @param lightPosition  Light position.
     * @param lightDirection Light direction.
     */
    protected SpotLight(final String name, final Point3D lightPosition, final Point3D lightDirection) {
        super(name, lightPosition);
        this.direction = lightDirection;
    }

    /**
     * Translate the direction vector.
     *
     * @param add Value to add.
     */
    public final void addToDirection(final Point3D add) {
        this.direction = this.direction.add(add);
        this.setDirectionImpl(this.direction);
    }

    /**
     * @return The light direction.
     */
    public final Point3D getDirection() {
        return this.direction;
    }

    /**
     * Set new direction value in implementation.
     *
     * @param direction New direction value.
     */
    protected abstract void setDirectionImpl(Point3D direction);
}
