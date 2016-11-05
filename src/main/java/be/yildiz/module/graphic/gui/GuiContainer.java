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
import be.yildiz.common.Rectangle;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.vector.Point2D;
import be.yildiz.module.graphic.Material;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A container holding Widget.
 *
 * @author Grégory Van Den Borre
 */
public abstract class GuiContainer extends Widget {

    /**
     * List of all Widget contained in this container.
     */
    private final List<ContainerChild> childrenList = Lists.newList();

    private final List<ContainerChild> dynamicChildrenList = Lists.newList();
    /**
     * List of all child containers.
     */
    private final List<GuiContainer> childrenContainerList = Lists.newList();
    /**
     * Flag to check if it must be considered as a widget par(true) or a widget
     * container(false).
     */
    private final boolean widget;
    /**
     * if <code>true</code>, the content of the container is visible.
     */
    private boolean showContent;
    /**
     * Position of the current focused widget in the children elements list.
     */
    private int currentFocusable = -1;
    /**
     * Background material.
     */
    @Getter
    private Material material;
    /**
     * Container Z order.
     */
    @Getter
    private Zorder z = Zorder.ZERO;

    /**
     * Full container.
     *
     * @param name               Container name, must be unique.
     * @param coordinates        Container size and position.
     * @param backgroundMaterial The Material to use for background.
     * @param parent             Parent container.
     * @param widget             Flag to set this container being considered as container or widget.
     */
    protected GuiContainer(final String name, final BaseCoordinate coordinates, final Material backgroundMaterial,
                           final Optional<GuiContainer> parent, final boolean widget) {
        super(name, coordinates, parent);
        this.material = backgroundMaterial;
        this.setFocusable(true);
        this.widget = widget;
        if (!widget && parent.isPresent()) {
            parent.get().childrenContainerList.add(this);
            parent.get().emptyZones.forEach(this::addEmptyZone);
        }
    }

    /**
     * Make a zoom on the container.
     *
     * @param zoomFactor Zoom value.
     */
    public final void zoom(final float zoomFactor) {
        this.zoomImpl(zoomFactor);
        for (final ContainerChild w : this.childrenList) {
            w.updateSizeAfterZoom(zoomFactor);
        }
    }

    /**
     * Show all the container sub elements.
     */
    public final void showContent() {
        this.showContent = true;
        this.showSubElement();
    }

    /**
     * Hide all the container sub elements.
     */
    public final void hideContent() {
        this.showContent = false;
        this.hideSubElement();
    }

    /**
     * Retrieve a child element from its position.
     *
     * @param position Element position.
     * @return The element at the given position.
     */
    public final String getElementName(final Point2D position) {
        return this.getElementName(position.getX(), position.getY());
    }

    public abstract String getElementName(final int x, final int y);

    /**
     * Retrieve all child elements contained in a rectangle.
     *
     * @param rectangle Rectangle containing the elements.
     * @return All Widget in the rectangle.
     */
    public final List<Widget> getElements(final Rectangle rectangle) {
        return this.childrenList.stream().filter(w -> rectangle.contain(w.getLeft(), w.getTop()) || rectangle.contain(w.getRight(), w.getTop())
                || rectangle.contain(w.getLeft(), w.getBottom())
                || rectangle.contain(w.getRight(), w.getBottom())).collect(Collectors.toList());
    }

    /**
     * Move all children left or right by adding a value to its current x value.
     *
     * @param left Value to add to the children current x position.
     */
    public final void addChildrenLeft(final int left) {
        for (final Widget w : this.childrenList) {
            w.addToLeft(left);
        }
    }

    /**
     * Move all children up or down by adding a value to its current y value.
     *
     * @param top Value to add to the children current y position.
     */
    public final void addChildrenTop(final int top) {
        for (final Widget w : this.childrenList) {
            w.addToTop(top);
        }
    }

    /**
     * Move all children by adding a value to its current position.
     *
     * @param left Value to add to the children current x position.
     * @param top  Value to add to the children current y position.
     */
    public final void addChildrenPosition(final int left, final int top) {
        this.addChildrenPositionImpl(left, top);
        BaseCoordinate c = this.getCoordinates();
        for (final Widget w : this.childrenList) {
            w.updateAddPositionValue(left, top);
            w.setVisible(c.contains(w.getCoordinates()));
        }
    }

    @Override
    protected final void addEmptyZoneImpl(final Rectangle zone) {
        for (GuiContainer c : this.childrenContainerList) {
            c.addEmptyZone(zone);
        }
    }

    /**
     * Implementation specific add children position.
     *
     * @param left Value to add to the children current x position.
     * @param top  Value to add to the children current y position.
     */
    protected abstract void addChildrenPositionImpl(int left, int top);

    /**
     * Add a new Widget in the container.
     *
     * @param child Element to add.
     */
    protected final void addWidget(final ContainerChild child) {
        this.dynamicChildrenList.add(child);
        this.childrenList.add(child);
    }

    void ignore(final Widget widget) {
        this.dynamicChildrenList.remove(widget);
    }

    /**
     * Remove and delete a Widget from the GuiContainer.
     *
     * @param element Element to remove.
     */
    protected final void remove(final ContainerChild element) {
        this.childrenList.remove(element);
        this.dynamicChildrenList.remove(element);
        element.delete();
    }

    /**
     * Does nothing.
     *
     * @param over Unused.
     */
    @Override
    protected final void highlightImpl(final boolean over) {
    }

    /**
     * Zoom the GuiContainer in implementation.
     *
     * @param factor Factor of zoom.
     */
    protected abstract void zoomImpl(final float factor);

    /**
     * Set a child widget as focused.
     *
     * @param widget Child to focus.
     */
    final void setCurrentFocus(final Widget widget) {
        for (final Widget w : this.dynamicChildrenList) {
            w.focus(false);
        }
        for (GuiContainer c : this.childrenContainerList) {
            for (final Widget w : c.dynamicChildrenList) {
                w.focus(false);
            }
        }
        if (this.dynamicChildrenList.contains(widget) && widget.isFocusable()) {
            this.currentFocusable = this.dynamicChildrenList.indexOf(widget);
            widget.focus(true);
        }
        for (GuiContainer c : this.childrenContainerList) {
            if (c.dynamicChildrenList.contains(widget) && widget.isFocusable()) {
                this.currentFocusable = this.dynamicChildrenList.indexOf(widget);
                widget.focus(true);
                break;
            }
        }
    }

    /**
     * Get the next child with focusable status.
     *
     * @return The focusable child following the current focused child,
     * <code>null</code> if none found.
     */
    final Widget getNextFocusableElement() {
        // first iterate from current position to end of list.
        for (int i = this.currentFocusable + 1; i < this.dynamicChildrenList.size(); i++) {
            if (this.dynamicChildrenList.get(i).isFocusable()) {
                this.updateFocus(i);
                return this.dynamicChildrenList.get(i);
            }
        }
        for (GuiContainer child : this.childrenContainerList) {
            Widget w = child.getNextFocusableElement();
            if (w != null) {
                return w;
            }
        }
        // if nothing found, go back to begin of the list and iterate to current
        // position, to check all positions.
        for (int i = 0; i < this.currentFocusable; i++) {
            if (this.dynamicChildrenList.get(i).isFocusable()) {
                this.updateFocus(i);
                return this.dynamicChildrenList.get(i);
            }
        }
        return null;
    }

    /**
     * Update the focus.
     *
     * @param posList Position of the widget in the children list.
     */
    private void updateFocus(final int posList) {
        // FIXME only updated after a TAB, not after a mouse click, todo the
        // widget must be passed to the contained, check its position in the
        // list.
        if (this.currentFocusable != -1) {
            this.dynamicChildrenList.get(this.currentFocusable).focus(false);
        }
        this.currentFocusable = posList;
        this.dynamicChildrenList.get(this.currentFocusable).focus(true);
        this.resetFocus();
    }

    private void resetFocus() {
        for (GuiContainer c : this.childrenContainerList) {
            c.currentFocusable = -1;
            c.resetFocus();
        }
    }

    /**
     * Set all children visible.
     */
    private void showSubElement() {
        if (this.showContent) {
            this.childrenList.forEach(BaseElement::show);
            for (final GuiContainer c : this.childrenContainerList) {
                c.setVisible(true);
                c.showContent();
            }
        }
    }

    /**
     * Set all children invisible.
     */
    private void hideSubElement() {
        if (!this.showContent) {
            this.childrenList.forEach(BaseElement::hide);
            for (final GuiContainer c : this.childrenContainerList) {
                c.setVisible(false);
                c.hideContent();
            }
        }
    }

    /**
     * Set the GuiContainer material.
     *
     * @param newMaterial Material to use for background..
     */
    public final void setMaterial(final Material newMaterial) {
        if (!this.material.equals(newMaterial)) {
            this.material = newMaterial;
            this.setMaterialImpl(newMaterial);
        }
    }

    /**
     * Set the Material in implementation.
     *
     * @param newMaterial New Material to use.
     */
    protected abstract void setMaterialImpl(final Material newMaterial);

    /**
     * Show or hide the content of this container.
     *
     * @param visible <code>true</code> will display the content, <code>false</code>
     *                will hide it.
     */
    public final void setContentVisible(final boolean visible) {
        if (visible) {
            this.showContent();
        } else {
            this.hideContent();
        }
    }

    /**
     * Set the container Z order.
     *
     * @param z Z order.
     */
    public final void setZ(final Zorder z) {
        this.z = z;
        this.setZImpl(z);
    }

    /**
     * Implementation specific to set the Z order.
     *
     * @param z Z order.
     */
    protected abstract void setZImpl(Zorder z);

    public void disableHighlight() {
        this.highlight(false);
        for (Widget w : this.dynamicChildrenList) {
            w.highlight(false);
        }
        this.childrenContainerList.forEach(GuiContainer::disableHighlight);
    }

    public Optional<Widget> getWidgetAt(Point2D position) {
        return this.getWidgetAt(position.getX(), position.getY());
    }

    public Optional<Widget> getWidgetAt(int x, int y) {
        for (GuiContainer c : this.childrenContainerList) {
            if (c.isVisible()) {
                Optional<Widget> result = c.getWidgetAt(x, y);
                if (result.isPresent()) {
                    return result;
                }
            }
        }

        for (Widget w : this.dynamicChildrenList) {
            if (w.isVisible() && w.contains(x, y)) {
                return Optional.of(w);
            }
        }
        return Optional.empty();
    }


}
