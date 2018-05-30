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

import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.gui.internal.BaseWidget;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;

import java.util.Optional;

/**
 * Contains common method for all container children.
 *
 * @author Grégory Van Den Borre
 */
public abstract class BaseContainerChild extends BaseWidget implements ContainerChild {

    /**
     * If the view containing this widget is zoomed, the widget will be affected or not depending on this value, <code>true</code> by default.
     */
    private boolean affectedByZoom;

    private final SimpleContainer parent;

    /**
     * Full constructor.
     *
     * @param name        Widget name, must be unique.
     * @param coordinates Widget size and position.
     * @param container   Container holding the widget.
     */
    protected BaseContainerChild(final String name, final BaseCoordinate coordinates, final SimpleContainer container) {
        super(name, coordinates, container);
        this.affectedByZoom = true;
        container.addWidget(this);
        this.parent = container;
    }

    @Override
    public final SimpleContainer getParent() {
        return this.parent;
    }

    @Override
    public final void detachFromParent() {
        this.parent.remove(this);
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
    public final void updateSizeAfterZoom(final float factor) {
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
    public final ContainerChild setLeftFromParent(final PositionRelativeLeft relative) {
        Optional.ofNullable(this.parent).ifPresent(p -> this.setLeft(p, relative, -p.getLeft()));
        return this;
    }

    @Override
    public final ContainerChild setLeftFromParent(final PositionRelativeLeft relative, final int diff) {
        Optional.ofNullable(this.parent).ifPresent(p -> this.setLeft(p, relative, diff));
        return this;
    }

    @Override
    public final ContainerChild setTopFromParent(final PositionRelativeTop relative) {
        Optional.ofNullable(this.parent).ifPresent(p -> this.setTop(p, relative));
        return this;
    }

    @Override
    public final ContainerChild setTopFromParent(final PositionRelativeTop relative, final int diff) {
        Optional.ofNullable(this.parent).ifPresent(p -> this.setTop(p, relative, diff));
        return this;
    }

    public final boolean isContainer() {
        return false;
    }
}
