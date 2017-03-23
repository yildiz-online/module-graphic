/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.graphic;

import be.yildiz.common.vector.Point3D;

/**
 * Electric arc effect between 2 points.
 *
 * @author Grégory Van den Borre
 */
public abstract class ElectricArc {

    /**
     * Arc origin.
     */
    private final Point3D origin;

    /**
     * Arc end.
     */
    private final Point3D end;

    /**
     * Full constructor.
     *
     * @param start Arc origin.
     * @param end   Arc end.
     */
    protected ElectricArc(final Point3D start, final Point3D end) {
        super();
        this.origin = start;
        this.end = end;
    }

    /**
     * Set the arc material.
     *
     * @param material Material to set.
     * @return This object.
     */
    public abstract ElectricArc setMaterial(Material material);

    /**
     * Set the maximum distance the points can go from the line between start
     * and end points.
     *
     * @param ceil Maximum distance.
     * @return This object.
     */
    public abstract ElectricArc setCeil(int ceil);

    /**
     * Add a blinking light.
     *
     * @param light The light to add.
     * @return This object.
     */
    public abstract ElectricArc addLight(PointLight light);

    public Point3D getOrigin() {
        return origin;
    }

    public Point3D getEnd() {
        return end;
    }
}
