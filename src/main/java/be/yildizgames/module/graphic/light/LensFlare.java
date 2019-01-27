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
import be.yildizgames.module.graphic.material.Material;

/**
 * A lens flare is composed of 3 Material moving when the camera moves.
 *
 * @author Grégory Van den Borre
 */
public abstract class LensFlare {

    /**
     * Current position.
     */
    private Point3D position;

    /**
     * Full constructor.
     *
     * @param lensPosition Current position.
     */
    protected LensFlare(final Point3D lensPosition) {
        super();
        this.position = lensPosition;
    }

    /**
     * Update the lens flare position.
     *
     * @param position New position.
     */
    public final void setPosition(final Point3D position) {
        this.position = position;
        this.setPositionImpl(position);
    }

    public Point3D getPosition() {
        return position;
    }

    public abstract void setStreakSize(float w, float h);

    public abstract void setLightSize(float w, float h);

    /**
     * Set the position in implementation.
     *
     * @param position New position.
     */
    protected abstract void setPositionImpl(Point3D position);

    public static class LensFlareMaterial {

        /**
         * Lens flare base material.
         */
        private final Material material;

        private final Material halo;

        private final Material streak;

        private final Material burst;

        public LensFlareMaterial(Material material, Material halo, Material streak, Material burst) {
            this.material = material;
            this.halo = halo;
            this.streak = streak;
            this.burst = burst;
        }

        public Material getMaterial() {
            return material;
        }

        public Material getHalo() {
            return halo;
        }

        public Material getStreak() {
            return streak;
        }

        public Material getBurst() {
            return burst;
        }
    }
}
