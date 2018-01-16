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

import be.yildiz.module.window.input.MouseDragListener;
import be.yildiz.module.window.input.MousePosition;
import be.yildizgames.common.collection.Lists;
import be.yildizgames.common.geometry.Rectangle;

import java.util.List;

/**
 * Listener to move containers when they are dragged with the mouse. If a
 * container is near the limit of its zone, or near another container, it will
 * be docked to the border.
 *
 * @author Grégory Van den Borre
 */
final class ContainerElementDragListener implements MouseDragListener {

    /**
     * List of all draggable container.
     */
    private static final List<GuiContainer> DOCKABLE_LIST = Lists.newList();

    /**
     * Container associated with this listener.
     */
    private final GuiContainer container;
    /**
     * Limit to dock the container, it can be a parent container border, or the
     * screen border.
     */
    private final Rectangle zoneLimit;
    /**
     * Upper limit to drag the container, i.e a container with a limit of 20
     * will be dragged only if the mouse is between the top of the container and
     * 20px lower.
     */
    private float limit;

    /**
     * Full constructor. Upper limit where the container can be grabbed is set
     * to 30 pixels.
     *
     * @param guiContainer  Container receiving ability to be dragged.
     * @param dragZoneLimit Limit to dock the container.
     */
    ContainerElementDragListener(final GuiContainer guiContainer, final Rectangle dragZoneLimit) {
        super();
        this.container = guiContainer;
        this.limit = this.container.getTop() + 30;
        this.zoneLimit = dragZoneLimit;
        ContainerElementDragListener.DOCKABLE_LIST.add(guiContainer);
    }

    /**
     * Move the container, and dock it with any other container or with the zone
     * border.
     *
     * @param position Current mouse position.
     * @param delta    Difference between this mouse position and the last one.
     */
    @Override
    public void mouseDragLeft(final MousePosition position, final MousePosition delta) {
        final int dockDistance = 20;
        this.limit -= delta.getY();
        if (position.getY() < this.limit) {
            this.container.addToPosition(-delta.getX(), -delta.getY());
            final boolean dragginToLeft = delta.getX() > 0;
            final boolean dragginToTop = delta.getY() > 0;

            if (delta.getX() == 0 && delta.getY() == 0) {
                return;
            }

            if (dragginToLeft) {
                // check limit bound
                if (this.container.getLeft() < this.zoneLimit.getLeft() + dockDistance) {
                    this.container.setLeft(this.zoneLimit.getLeft());
                    // check containers bounds(check container visibility first)
                }
            } else {
                if (this.container.getRight() > this.zoneLimit.getRight() - dockDistance) {
                    this.container.setLeft(this.zoneLimit.getRight() - this.container.getWidth());
                }
            }
            if (dragginToTop) {
                if (this.container.getTop() < this.zoneLimit.getTop() + dockDistance) {
                    this.container.setTop(this.zoneLimit.getTop());
                }
            } else {
                if (this.container.getBottom() > this.zoneLimit.getBottom() - dockDistance) {
                    this.container.setTop(this.zoneLimit.getBottom() - this.container.getHeight());
                }
            }

        }
    }
}
