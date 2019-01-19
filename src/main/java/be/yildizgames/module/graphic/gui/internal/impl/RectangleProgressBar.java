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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.gui.image.Image;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.material.Material;

/**
 * A simple progress bar with a border.
 *
 * @author Grégory Van den Borre
 */
final class RectangleProgressBar extends BaseProgressBar {

    private final Image border;

    private final Image content;

    private final int borderSize;

    RectangleProgressBar(final String name, final BaseCoordinate coordinates, final Image border, final Image content, final int borderSize,
                                   final SimpleContainer parent) {
        super(name, coordinates, parent);
        this.border = border;
        this.content = content;
        this.borderSize = borderSize;
    }

    @Override
    protected void updateView(final float progress) {
        this.content.setWidth(Math.round(progress * (this.border.getWidth() - this.borderSize) * 0.01f));
        this.border.hide();
        this.content.show();
    }

    @Override
    public void delete() {
        this.border.delete();
        this.content.delete();
    }

    @Override
    protected void showImpl() {
        this.border.show();
        this.content.show();
    }

    @Override
    protected void hideImpl() {
        this.border.hide();
        this.content.hide();
    }

    @Override
    protected Element setPositionImpl(final int newLeft, final int newTop) {
        this.border.setPosition(newLeft, newTop);
        this.content.setPosition(newLeft + this.borderSize, newTop + this.borderSize);
        return this;
    }

    @Override
    public void setContentMaterial(Material mat) {
        this.content.setMaterial(mat);
    }

}
