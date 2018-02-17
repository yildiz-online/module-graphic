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

package be.yildizgames.module.graphic.light;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.color.Color;

/**
 * A PointLight is a light emitting in all direction.
 *
 * @author Grégory Van den Borre
 */
public abstract class PointLight extends Light {

    /**
     * Full constructor.
     *
     * @param name          Light name, must be unique.
     * @param lightPosition Light position.
     */
    protected PointLight(final String name, final Point3D lightPosition) {
        super(name, lightPosition);
    }

    /**
     * Set the light color.
     *
     * @param color Color to set.
     */
    public abstract void setColor(Color color);

    /**
     * Set the light attenuation factor.
     *
     * @param range     Light range.
     * @param constant  Constant value.
     * @param linear    Linear value.
     * @param quadratic Quadratic value.
     */
    public abstract void setAttenuation(float range, float constant, float linear, float quadratic);


    /**
     * @return Light name.
     */
    @Override
    public final String toString() {
        return this.getName();
    }

    /**
     * Enable debug display.
     */
    public abstract void setDebug();
}
