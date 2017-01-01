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
import be.yildiz.common.Rectangle;

import java.util.Optional;

/**
 * Contains common method for all container children.
 *
 * @author Grégory Van Den Borre
 */
public abstract class ContainerChild extends Widget implements ContainerElement {

    /**
     * If the view containing this widget is zoomed, the widget will be affected or not depending on this value, <code>true</code> by default.
     */
    private boolean affectedByZoom;

    /**
     * Full constructor.
     *
     * @param name        Widget name, must be unique.
     * @param coordinates Widget size and position.
     * @param container   Container holding the widget.
     */
    protected ContainerChild(final String name, final BaseCoordinate coordinates, final Optional<GuiContainer> container) {
        super(name, coordinates, container);
        assert container.isPresent() : "parent parameter is not present.";
        this.affectedByZoom = true;
        container.get().addWidget(this);
    }

    @Override
    public final void detachFromParent() {
        this.parent.ifPresent(p -> p.remove(this));
    }

    @Override
    protected final void addEmptyZoneImpl(Rectangle zone) {
    }

    /**
     * If the view containing this widget is zoomed, the widget will not be affected.
     */
    public final void setUnaffectedByZoom() {
        this.affectedByZoom = false;
    }

    /**
     * Zoom in the container does not update this object size, to keep it coherent, it is updated in this method.
     *
     * @param factor Zoom factor.
     */
    final void updateSizeAfterZoom(final float factor) {
        // if (this.isAffectedByZoom()) {
        // FIXME uncomment
        // this.height *= factor;
        // this.width *= factor;
        // } else {
        // Call directly implementation to avoid modifying width and height
        // attributes.
        // this.setSizeImpl(this.width / factor, this.height / factor);
        // }
    }

    /**
     * @return <code>true</code> if this widget can be zoomed, false otherwise.
     */
    final boolean isAffectedByZoom() {
        return this.affectedByZoom;
    }

    @Override
    public final ContainerElement setLeftFromParent(final PositionRelativeLeft relative) {
        this.setLeft(this.parent.get(), relative, -this.parent.get().getLeft());
        return this;
    }

    @Override
    public final ContainerElement setLeftFromParent(final PositionRelativeLeft relative, final int diff) {
        this.setLeft(this.parent.get(), relative, diff);
        return this;
    }

    @Override
    public final ContainerElement setTopFromParent(final PositionRelativeTop relative) {
        this.setTop(this.parent.get(), relative);
        return this;
    }

    @Override
    public final ContainerElement setTopFromParent(final PositionRelativeTop relative, final int diff) {
        this.setTop(this.parent.get(), relative, diff);
        return this;
    }

    public final boolean isContainer() {
        return false;
    }
}
