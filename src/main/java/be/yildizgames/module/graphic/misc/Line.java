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

package be.yildizgames.module.graphic.misc;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.graphic.material.Material;

/**
 * A 3D line is a line drawn between 2 points.
 *
 * @author Grégory Van den Borre
 */
public abstract class Line {

    /**
     * Line initial point coordinates.
     */
    private Point3D begin = Point3D.ZERO;

    /**
     * Line end point coordinates.
     */
    private Point3D end = Point3D.ZERO;

    /**
     * Material currently use, if none, the line will be blank.
     */
    private Material material = Material.empty();

    /**
     * <code>true</code> if the line is visible, <code>false</code> otherwise.
     */
    private boolean visible = true;

    /**
     * Simple constructor, only to be called by child.
     */
    protected Line() {
        super();
    }

    /**
     * Set the position of the line first point.
     *
     * @param position First point coordinates.
     */
    public final void setInitialPosition(final Point3D position) {
        this.begin = position;
        this.update(this.begin.x, this.begin.y, this.begin.z, this.end.x, this.end.y, this.end.z);
    }

    /**
     * Set the position of the line last point.
     *
     * @param position Last point coordinates.
     */
    public final void setEndPosition(final Point3D position) {
        this.end = position;
        this.update(this.begin.x, this.begin.y, this.begin.z, this.end.x, this.end.y, this.end.z);
    }

    /**
     * Set the position of the line last point.
     *
     * @param x Last point X coordinate.
     * @param y Last point Y coordinate.
     * @param z Last point Z coordinate.
     */
    public final void setEndPosition(final float x, final float y, final float z) {
        this.setEndPosition(Point3D.valueOf(x, y, z));

    }

    /**
     * Set the position of the line first and last points.
     *
     * @param originPosition First point coordinates, copy, modifying from outside wont affect the line.
     * @param endPosition    Last point coordinates, copy, modifying from outside wont affect the line.
     */
    public final void setPositions(final Point3D originPosition, final Point3D endPosition) {
        this.setInitialPosition(originPosition);
        this.setEndPosition(endPosition);
    }

    /**
     * Set the line visible on screen.
     */
    public final void show() {
        if (!this.visible) {
            this.showImpl();
            this.visible = true;
        }
    }

    /**
     * Set the line invisible on screen.
     */
    public final void hide() {
        if (this.visible) {
            this.hideImpl();
            this.visible = false;
        }
    }

    /**
     * Change the line material.
     *
     * @param newMaterial New Material to affect to the line.
     */
    public final void setMaterial(final Material newMaterial) {
        if (!this.material.equals(newMaterial)) {
            this.material = newMaterial;
            this.setMaterialImpl(newMaterial);
        }
    }

    /**
     * Update the line in implementation.
     *
     * @param beginX First point X coordinate.
     * @param beginY First point Y coordinate.
     * @param beginZ First point Z coordinate.
     * @param endX   Last point X coordinate.
     * @param endY   Last point Y coordinate.
     * @param endZ   Last point Z coordinate.
     */
    protected abstract void update(float beginX, float beginY, float beginZ, float endX, float endY, float endZ);

    /**
     * Set the line invisible in implementation.
     */
    protected abstract void hideImpl();

    /**
     * Set the line visible in implementation.
     */
    protected abstract void showImpl();

    /**
     * Set the new material in implementation.
     *
     * @param newMaterial New material to affect.
     */
    protected abstract void setMaterialImpl(Material newMaterial);

    /**
     * @return <code>true</code> if the line is visible, <code>false</code> otherwise.
     */
    public final boolean isVisible() {
        return this.visible;
    }

}
