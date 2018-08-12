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

import be.yildizgames.common.util.BaseRegisterable;
import be.yildizgames.common.util.Registerer;
import be.yildizgames.module.graphic.Visible;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseWidget;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.KeyboardListener;
import be.yildizgames.module.window.input.MouseDragListener;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;
import be.yildizgames.module.window.input.MouseReleaseListener;
import be.yildizgames.module.window.input.MouseRightClickListener;
import be.yildizgames.module.window.input.MouseWheelListener;

import java.util.Optional;

/**
 * A View is an abstract game component, it can be the game window, or a map, or a gui window. It contains a Z value to help the EventDispatcher to correctly dispatch the inputs: the higher the value,
 * the higher the priority to check if the mouse is in this view. So if a view is on top of another, it has to use a greater Z value.
 *
 * @author Grégory Van den Borre
 */
public abstract class View extends BaseRegisterable implements Comparable<View>, Visible {

    /**
     * Contains all the views.
     */
    private static final Registerer<View> REGISTERER = Registerer.newRegisterer();

    /**
     * Value representing the position of the view in depth, the higher, the closer to the user.
     */
    private final Zorder zOrder;

    /**
     * Container to hold the view elements.
     */
    private final Container container;

    private final GuiEventManager eventManager;

    /**
     * <code>true</code> if this is the currently active window.
     */
    private boolean active;

    /**
     * Widget to use when view is focused, default is the wrapped container.
     */
    private Widget focus;

    /**
     * Full constructor, initialize the visibility to true, add the view to the event dispatcher.
     *
     * @param wrappedContainer Wrapped container.
     * @param z                Z order.
     * @param eventManager     Event manager to notify.
     */
    public View(final Container wrappedContainer, final Zorder z, GuiEventManager eventManager) {
        super(wrappedContainer.getName());
        this.container = wrappedContainer;
        this.container.setZ(z);
        this.zOrder = z;
        this.focus = wrappedContainer;
        View.REGISTERER.register(this);
        this.eventManager = eventManager;
        Optional.ofNullable(this.eventManager)
                .ifPresent(m -> m.addView(this));
    }

    public View(final Container wrappedContainer, final Zorder z) {
        this(wrappedContainer, z, null);
    }

    /**
     * Get a view from its name.
     *
     * @param name View name to retrieve.
     * @return The view associated with the name.
     */
    public static View get(final String name) {
        return View.REGISTERER.get(name);
    }

    /**
     * Compute if the point is in the view. i.e. if the view is at (100,100) and its size is (150,100), a point with x between 100 and 250 and y between 100 and 200 will return true.
     *
     * @param position Point to test.
     * @return <code>true</code> if the point is inside the view, <code>false</code> otherwise.
     */
    public boolean contains(final MousePosition position) {
        return this.container.contains(position);
    }

    public boolean contains(final int x, final int y) {
        return this.container.contains(x, y);
    }

    /**
     * Set the view visible.
     */
    @Override
    public final void show() {
        Optional
                .ofNullable(this.eventManager)
                .ifPresent(m -> m.setFocus(this));
        this.setVisible(true);
    }

    /**
     * Set the view invisible.
     */
    @Override
    public final void hide() {
        this.setVisible(false);
    }

    /**
     * Delete the view.
     */
    public final void delete() {
        this.setVisible(false);
    }

    /**
     * Add a mouse listener to this view.
     *
     * @param listener Listener to use when the mouse is moved in the view.
     */
    public final void addMouseMoveListener(final MouseMoveListener listener) {
        this.container.addMouseMoveListener(listener);
    }

    /**
     * Add a mouse click listener to this view.
     *
     * @param listener Listener to use when the mouse button is pressed in the view.
     */
    public final void addMouseLeftClickListener(final MouseLeftClickListener listener) {
        this.container.addMouseLeftClickListener(listener);
    }

    public final void addMouseRightClickListener(final MouseRightClickListener listener) {
        this.container.addMouseRightClickListener(listener);
    }

    /**
     * Add a mouse release listener to this view.
     *
     * @param listener Listener to use when the mouse button is released in the view.
     */
    public final void addMouseReleaseListener(final MouseReleaseListener listener) {
        this.container.addMouseReleaseListener(listener);
    }

    /**
     * Add a keyboard listener to this view.
     *
     * @param listener Listener to use when the keyboard is used in the view.
     */
    public final void addKeyboardListener(final KeyboardListener listener) {
        this.container.addKeyboardListener(listener);
    }

    /**
     * Add a mouse drag listener to this view.
     *
     * @param listener Listener to use when the mouse is dragged in this view.
     */
    public final void addMouseDragListener(final MouseDragListener listener) {
        this.container.addMouseDragListener(listener);
    }

    /**
     * Add a mouse wheel listener to this view.
     *
     * @param listener Listener to use when the mouse is scrolled in this view.
     */
    public final void addMouseWheelListener(final MouseWheelListener listener) {
        this.container.addMouseWheelListener(listener);
    }

    /**
     * Set the focus on a widget.
     *
     * @param focus Widget receiving the focus
     */
    final void updateCurrentFocus(final BaseWidget focus) {
        this.container.setCurrentFocus(focus);
    }

    /**
     * @return The next focusable element from the widget list of this view.
     */
    final Widget getNextFocusableElement() {
        Widget w = this.container.getNextFocusableElement();
        this.container.setCurrentFocus(w);
        return w;
    }

    /**
     * @return <code>true</code> if the view is currently visible.
     */
    public final boolean isVisible() {
        return this.container.isVisible();
    }

    /**
     * Set the View visible or not.
     *
     * @param visibleState <code>true</code> to set the View visible, <code>false</code> to hide it.
     */
    public final void setVisible(final boolean visibleState) {
        if (visibleState) {
            this.container.show();
            this.container.showContent();
        } else {
            this.container.hide();
        }
        this.setVisibleImpl(visibleState);
    }

    /**
     * Compare 2 View by their z order.
     *
     * @param otherView Other View to compare with this one.
     * @return 0 if equals, 1 if the other view is greater, -1 otherwise.
     */
    @Override
    public final int compareTo(final View otherView) {
        return this.zOrder.compareTo(otherView.zOrder);
    }

    /**
     * Set the view position.
     *
     * @param left View left position value.
     * @param top  View top position value.
     */
    public final void setPosition(final int left, final int top) {
        this.container.setPosition(left, top);
    }

    /**
     * Set the background material.
     *
     * @param background New background material.
     */
    public final void setBackground(final Material background) {
        this.container.setMaterial(background);
    }

    /**
     * Set specific implementation when setVisible is called.
     *
     * @param show <code>true</code> if the view is set to visible, <code>false</code> if it is hidden.
     */
    protected abstract void setVisibleImpl(boolean show);

    /**
     * Called when the mouse is in the view.
     *
     * @param active <code>true</code> if the mouse is in the view, false otherwise.
     */
    public final void setActive(final boolean active) {
        this.active = active;
        this.setActiveImpl(active);
    }

    /**
     * Implementation specific set active.
     *
     * @param active Active state.
     */
    protected abstract void setActiveImpl(boolean active);

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.container.hashCode();
        result = prime * result + this.zOrder.getValue();
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final View other = (View) obj;
        return this.container.equals(other.container) && this.zOrder.getValue() == other.zOrder.getValue();
    }

    /**
     * @return The class name and the z order.
     */
    @Override
    public final String toString() {
        return this.getClass().toString() + ":" + this.getContainer() + ":"  + this.zOrder.getValue();
    }

    public void disableHighlight() {
        this.container.disableHighlight();
    }

    public Container getContainer() {
        return container;
    }

    public boolean isActive() {
        return active;
    }

    public Widget getFocus() {
        return focus;
    }

    protected void setFocus(BaseWidget focus) {
        this.focus = focus;
    }
}
