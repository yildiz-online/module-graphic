//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic.gui;

import be.yildiz.common.BaseCoordinate;
import be.yildiz.module.graphic.Material;

import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
final class EmptyRectangleImage extends ContainerChild implements Image {

    /**
     * Textured element to use.
     */
    private final AbstractIconElement left;

    /**
     * Textured element to use.
     */
    private final AbstractIconElement top;

    /**
     * Textured element to use.
     */
    private final AbstractIconElement right;

    /**
     * Textured element to use.
     */
    private final AbstractIconElement bottom;

    public EmptyRectangleImage(String name, BaseCoordinate coordinates, AbstractIconElement left, AbstractIconElement top, AbstractIconElement right, AbstractIconElement bottom, GuiContainer container) {
        super(name, coordinates, Optional.of(container));
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    protected void highlightImpl(boolean highlightState) {
    }

    @Override
    public void delete() {
        this.left.delete();
        this.top.delete();
        this.right.delete();
        this.bottom.delete();
    }

    @Override
    protected void showImpl() {
        this.left.show();
        this.top.show();
        this.right.show();
        this.bottom.show();
    }

    @Override
    protected void hideImpl() {
        this.left.hide();
        this.top.hide();
        this.right.hide();
        this.bottom.hide();
    }

    @Override
    protected void setSizeImpl(int newWidth, int newHeight) {
        this.top.setWidth(newWidth);
        this.bottom.setWidth(newWidth);
        this.left.setHeight(newHeight);
        this.right.setHeight(newHeight);
    }

    @Override
    protected Element setPositionImpl(int newLeft, int newTop) {
        this.left.setPosition(newLeft, newTop);
        this.top.setPosition(newLeft, newTop);
        this.right.setPosition(newLeft + this.top.getWidth(), newTop);
        this.bottom.setPosition(newLeft, newTop + this.left.getHeight());
        return this;
    }

    @Override
    public Material getMaterial() {
        return this.left.getMaterial();
    }

    @Override
    public void setMaterial(final Material m) {
        this.left.setMaterial(m);
        this.top.setMaterial(m);
        this.bottom.setMaterial(m);
        this.right.setMaterial(m);
    }

    @Override
    public int getBorderSize() {
        return this.left.getWidth();
    }

}
