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

import java.util.Optional;

/**
 * A progress bar is a widget with a width depending of its progress status.
 *
 * @author Grégory Van den Borre
 */
public abstract class ProgressBar extends ContainerChild {

    /**
     * Current progress value.
     */
    private float progress;

    /**
     * Full constructor.
     *
     * @param name        Widget name, must be unique.
     * @param coordinates Widget coordinates, width value is used for a 100% completed progress bar.
     * @param parent      Container holding this widget.
     */
    protected ProgressBar(final String name, final BaseCoordinate coordinates, final GuiContainer parent) {
        super(name, coordinates, Optional.of(parent));
    }

    /**
     * @return <code>true</code> if the progress state is 100%, <code>false</code> otherwise.
     */
    public final boolean isFinished() {
        return this.progress > 99.0f;
    }

    /**
     * Update the progress state.
     *
     * @param newProgressValue New progress value.
     */
    public final void setProgress(final float newProgressValue) {
        final int maxProgress = 100;
        //FIXME MEDIUM use common.shared.BoundedValue class instead
        if (newProgressValue != this.progress) {
            if (newProgressValue > maxProgress) {
                // recall method to check again equality between param &
                // attribute.
                this.setProgress(maxProgress);
            } else if (newProgressValue < 0) {
                this.setProgress(0);
            } else {
                this.progress = newProgressValue;
            }
            this.updateView(this.progress);
        }
    }

    /**
     * Set the value to 0.
     */
    public final void reset() {
        this.setProgress(0.0f);
    }

    /**
     * Not highlightable, does nothing.
     *
     * @param highlighted Unused.
     */
    @Override
    protected final void highlightImpl(final boolean highlighted) {
        //does nothing.
    }

    @Override
    protected final void setSizeImpl(final int w, final int h) {
        // FIXME implements
    }

    /**
     * Update the progress bar.
     *
     * @param progress new progress value, in percent.
     */
    protected abstract void updateView(final float progress);

    public abstract void setContentMaterial(Material mat);
}
