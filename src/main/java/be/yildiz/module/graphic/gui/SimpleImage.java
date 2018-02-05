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

package be.yildiz.module.graphic.gui;

import be.yildiz.module.coordinate.BaseCoordinate;
import be.yildiz.module.graphic.Material;

import java.util.Optional;

/**
 * An Image is a simple widget drawing a texture.
 *
 * @author Grégory Van den Borre
 */
final class SimpleImage extends ContainerChild implements Image {

    /**
     * Textured element to use.
     */
    private final AbstractIconElement image;

    /**
     * Full constructor.
     *
     * @param name        Unique image name.
     * @param coordinates Image position and size.
     * @param icon        Icon element to use for the image.
     * @param container   Container holding the image.
     */
    SimpleImage(final String name, final BaseCoordinate coordinates, final AbstractIconElement icon, final GuiContainer container) {
        super(name, coordinates, Optional.of(container));
        this.image = icon;
    }

    /**
     * Rotate the image.
     *
     * @param angle Angle to rotate, in radians
     */
    public void rotateRadian(final float angle) {
        this.image.rotateRadian(angle);
    }

    /**
     * @return The image material.
     */
    @Override
    public Material getMaterial() {
        return this.image.getMaterial();
    }

    /**
     * Set the image material.
     */
    @Override
    public void setMaterial(final Material texture) {
        this.image.setMaterial(texture);
    }

    /**
     * Free all resources for this widget.
     */
    @Override
    public void delete() {
        this.image.delete();
    }

    /**
     * @return The parent container name retrieved from implementation, useful for debug.
     */
    public String getInternalParentName() {
        return this.image.getParentName();
    }

    /**
     * Does nothing, an image cannot be highlighted.
     *
     * @param over Unused.
     */
    @Override
    protected void highlightImpl(final boolean over) {
        //Does nothing.
    }

    @Override
    protected void setSizeImpl(final int width, final int height) {
        this.image.setSize(width, height);
    }

    /**
     * Show the embedded icon element.
     */
    @Override
    protected void showImpl() {
        this.image.show();
    }

    /**
     * Hide the embedded icon element.
     */
    @Override
    protected void hideImpl() {
        this.image.hide();
    }

    /**
     * Set the position in implementation.
     *
     * @param posX Icon element new X position.
     * @param posY Icon element new Y position.
     */
    @Override
    protected Element setPositionImpl(final int posX, final int posY) {
        this.image.setPosition(posX, posY);
        return this;
    }

}
