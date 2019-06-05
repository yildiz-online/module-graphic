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

import be.yildizgames.common.gameobject.Deletable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.util.BaseRegisterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Common abstract class for all light types, ensure that only one name is used.
 *
 * @author Grégory Van den Borre
 */
public abstract class Light extends BaseRegisterable implements Deletable {

    /**
     * List of associated lens flare.
     */
    private final List<LensFlare> lensFlarelList;

    /**
     * Light position.
     */
    private Point3D position;

    /**
     * Full constructor.
     *
     * @param name          Unique name to be used by the Registerer.
     * @param lightPosition Light position.
     */
    protected Light(final String name, final Point3D lightPosition) {
        super(name);
        Objects.requireNonNull(lightPosition);
        this.position = lightPosition;
        this.lensFlarelList = new ArrayList<>();
    }

    /**
     * Move the light.
     *
     * @param toMove Translation value.
     */
    public final void move(final Point3D toMove) {
        this.setPosition(this.position.add(toMove));
    }

    /**
     * Attach a lens flare to follow this light moves.
     *
     * @param lens Lens flare to attach to this light.
     */
    public final void attachLensFlare(final LensFlare lens) {
        Objects.requireNonNull(lens);
        this.lensFlarelList.add(lens);
    }

    /**
     * Update the light position.
     *
     * @param position New position.
     */
    public final void setPosition(final Point3D position) {
        this.position = position;
        this.setPositionImpl(position);
        for (LensFlare lens : this.lensFlarelList) {
            lens.setPosition(position);
        }
    }

    public final Point3D getPosition() {
        return position;
    }

    /**
     * Set the position in implementation.
     *
     * @param position New position.
     */
    protected abstract void setPositionImpl(Point3D position);

    /**
     * Delete the light and all associated lens flares.
     */
    @Override
    public final void delete() {
        this.deleteImpl();
    }

    /**
     * Call specific implementation delete code.
     */
    protected abstract void deleteImpl();

    /**
     * @return The list of associated lens flares.
     */
    public final List<LensFlare> getLensFlareList() {
        return this.lensFlarelList;
    }
}
