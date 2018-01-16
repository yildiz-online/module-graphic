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

/**
 * Complex progress bar composed of several images to use borders.
 *
 * @author Grégory Van den Borre
 */
final class ComplexProgressBar extends ProgressBar {

    /**
     * Left part.
     */
    private final AbstractIconElement leftIcon;

    /**
     * Middle part, its width depends of the progress value.
     */
    private final AbstractIconElement middleIcon;

    /**
     * Right part, its position move following the middle part width.
     */
    private final AbstractIconElement rightIcon;

    /**
     * Middle empty part.
     */
    private final AbstractIconElement middleEmptyIcon;

    /**
     * Right empty part.
     */
    private final AbstractIconElement rightEmptyIcon;

    /**
     * Computed value to set the middle element width depending on the progress.
     */
    private final float computedMibbleBarSize;

    /**
     * Full constructor.
     *
     * @param name        Widget name, must be unique.
     * @param coordinates Widget coordinates, width value is used for a 100% completed
     *                    progress bar.
     * @param left        Icon element for the progress bar left part.
     * @param middle      Icon element for the progress bar middle part.
     * @param right       Icon element for the progress bar right part.
     * @param middleEmpty Icon element for the progress bar middle part when empty.
     * @param rightEmpty  Icon element for the progress bar right part when empty.
     * @param parent      Container holding this widget.
     */
    ComplexProgressBar(final String name, final BaseCoordinate coordinates, final AbstractIconElement left,
                       final AbstractIconElement middle, final AbstractIconElement right, final AbstractIconElement middleEmpty,
                       final AbstractIconElement rightEmpty, final GuiContainer parent) {
        super(name, coordinates, parent);
        this.leftIcon = left;
        this.middleIcon = middle;
        this.rightIcon = right;
        this.middleEmptyIcon = middleEmpty;
        this.rightEmptyIcon = rightEmpty;
        this.computedMibbleBarSize = (coordinates.width - left.getWidth() * 2) * 0.01f;
    }

    @Override
    protected void delete() {
        this.leftIcon.delete();
        this.rightIcon.delete();
        this.middleIcon.delete();
        this.middleEmptyIcon.delete();
        this.rightEmptyIcon.delete();
    }

    @Override
    protected void showImpl() {
        this.leftIcon.show();
        this.middleIcon.show();
        this.rightIcon.show();
        this.middleEmptyIcon.show();
        this.rightEmptyIcon.show();
    }

    @Override
    protected void hideImpl() {
        this.leftIcon.hide();
        this.middleIcon.hide();
        this.rightIcon.hide();
        this.middleEmptyIcon.hide();
        this.rightEmptyIcon.hide();
    }

    @Override
    protected Element setPositionImpl(final int x, final int y) {
        this.leftIcon.setPosition(x, y);
        this.middleIcon.setPosition(this.leftIcon.getRight(), y);
        this.rightIcon.setPosition(this.middleIcon.getRight() - 3, y);
        this.middleEmptyIcon.setPosition(this.leftIcon.getRight(), y);
        this.rightEmptyIcon.setPosition(this.middleEmptyIcon.getRight(), y);
        return this;
    }

    /**
     * Update the progress state.
     *
     * @param progress New progress value.
     */
    @Override
    protected void updateView(final float progress) {
        this.middleIcon.setWidth(Math.round(progress * this.computedMibbleBarSize));
        this.rightIcon.setLeft(this.middleIcon.getRight() - 3);
    }

    @Override
    public void setContentMaterial(final Material mat) {
        this.middleIcon.setMaterial(mat);
    }
}
