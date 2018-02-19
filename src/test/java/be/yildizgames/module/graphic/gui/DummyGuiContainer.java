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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.material.Material;

/**
 * Empty container element.
 *
 * @author Grégory Van den Borre
 */
public final class DummyGuiContainer extends SimpleContainer {

    /**
     * Full constructor.
     *
     * @param name               Unique name.
     * @param coordinates        Object coordinates.
     * @param backgroundMaterial Material to use.
     */
    public DummyGuiContainer(final String name, final Coordinates coordinates, final Material backgroundMaterial, final boolean widget) {
        super(name, coordinates, backgroundMaterial, widget);
    }


    DummyGuiContainer(String name, BaseCoordinate coordinates, Material background) {
        super(name, coordinates, background, false);
    }


    DummyGuiContainer(String name, BaseCoordinate coordinates, Material background, SimpleContainer parent, boolean widget) {
        super(name, coordinates, background, parent, widget);
    }


    @Override
    public String getElementName(final int x, final int y) {
        return this.getName();
    }

    @Override
    protected void addChildrenPositionImpl(final int left, final int top) {
    }

    @Override
    protected void zoomImpl(final float factor) {
    }

    @Override
    protected void setMaterialImpl(final Material newMaterial) {
    }

    @Override
    public void delete() {
    }

    @Override
    protected void showImpl() {
    }

    @Override
    protected void hideImpl() {
    }

    @Override
    protected void setSizeImpl(final int newWidth, final int newHeight) {
    }

    @Override
    protected Element setPositionImpl(final int newLeft, final int newTop) {
        return this;
    }

    @Override
    protected void setZImpl(final Zorder z) {
    }

    @Override
    public void addWidget(ContainerChild child) {

    }

    @Override
    public void remove(ContainerChild child) {

    }
}
