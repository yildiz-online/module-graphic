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

package be.yildiz.module.graphic.gui;

import be.yildiz.module.coordinate.BaseCoordinate;
import be.yildiz.module.graphic.Material;
import be.yildizgames.common.util.Registerer;

/**
 * An Icon element is a simple texture on a plane. The element can be moved,
 * rotated.
 *
 * @author Grégory Van den Borre
 */
public abstract class AbstractIconElement extends BaseElement {

    /**
     * The registerer will force the name to be unique, or throw an exception if
     * it is not.
     */
    private static final Registerer<AbstractIconElement> REGISTERER = Registerer.newRegisterer();

    /**
     * Icon material.
     */
    private Material material;

    /**
     * Full constructor.
     *
     * @param name         Icon name, must be unique.
     * @param coordinates  Element position and size.
     * @param iconMaterial Material to use on this icon.
     */
    protected AbstractIconElement(final String name, final BaseCoordinate coordinates, final Material iconMaterial) {
        super(name, coordinates);
        this.material = iconMaterial;
        AbstractIconElement.REGISTERER.register(this);
    }

    public static AbstractIconElement getByName(final String name) {
        return AbstractIconElement.REGISTERER.get(name);
    }

    /**
     * Rotate the texture.
     *
     * @param value angle to rotate, in radians.
     */
    protected abstract void rotateRadian(final float value);

    /**
     * Retrieve the object Z value in implementation.
     *
     * @return this icon Z value.
     */
    protected abstract int getZ();

    /**
     * Remove this object from its registerer.
     */
    protected final void removeFromRegisterer() {
        AbstractIconElement.REGISTERER.remove(this);
    }

    /**
     * @return The material applied on this icon.
     */
    final Material getMaterial() {
        return this.material;
    }

    /**
     * Set a new Material.
     *
     * @param newMaterial New material to set.
     */
    final void setMaterial(final Material newMaterial) {
        if (!this.material.equals(newMaterial)) {
            this.material = newMaterial;
            this.setMaterialImpl(newMaterial);
        }
    }

    /**
     * Set the material in implementation.
     *
     * @param newMaterial Material to set.
     */
    protected abstract void setMaterialImpl(final Material newMaterial);

    /**
     * Retrieve the parent container name in implementation.
     *
     * @return The parent container name.
     */
    protected abstract String getParentName();
}
