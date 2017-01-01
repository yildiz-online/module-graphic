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

import be.yildiz.common.BaseCoordinate;
import be.yildiz.module.graphic.Material;

/**
 * Simple progress bar composed of only 2 images: an empty one, and a filled on
 * that will progressively cover the empty.
 *
 * @author Grégory Van den Borre
 */
final class SimpleProgressBar extends ProgressBar {

    /**
     * Empty image.
     */
    private final AbstractIconElement emptyIcon;

    /**
     * Filled image.
     */
    private final AbstractIconElement filledIcon;

    /**
     * Full constructor.
     *
     * @param name        Widget name, must be unique.
     * @param coordinates Widget coordinates, width value is used for a 100% completed
     *                    progress bar.
     * @param empty       Icon element for the progress bar empty part.
     * @param filled      Icon element for the progress bar filled part.
     * @param container   Container holding this widget.
     */
    protected SimpleProgressBar(final String name, final BaseCoordinate coordinates, final AbstractIconElement empty,
                                final AbstractIconElement filled, final GuiContainer container) {
        super(name, coordinates, container);
        this.emptyIcon = empty;
        this.filledIcon = filled;
    }

    @Override
    protected void delete() {
        this.emptyIcon.delete();
        this.filledIcon.delete();
    }

    @Override
    protected void showImpl() {
        this.emptyIcon.show();
        this.filledIcon.show();
    }

    @Override
    protected void hideImpl() {
        this.emptyIcon.hide();
        this.filledIcon.hide();
    }

    @Override
    protected Element setPositionImpl(final int newLeft, final int newTop) {
        this.emptyIcon.setPosition(newLeft, newTop);
        this.filledIcon.setPosition(newLeft, newTop);
        return this;
    }

    @Override
    protected void updateView(final float progress) {
        this.filledIcon.setWidth(Math.round(progress * this.emptyIcon.getWidth() * 0.01f));
    }

    @Override
    public void setContentMaterial(final Material mat) {
        this.filledIcon.setMaterial(mat);
    }

}
