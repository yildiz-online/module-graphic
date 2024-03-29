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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.common.geometry.Point2D;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.common.util.Checker;
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.graphic.gui.OnMouseOverListener;
import be.yildizgames.module.graphic.gui.Widget;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.window.input.Key;
import be.yildizgames.module.window.input.KeyboardListener;
import be.yildizgames.module.window.input.MouseDoubleLeftClickListener;
import be.yildizgames.module.window.input.MouseDragListener;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;
import be.yildizgames.module.window.input.MouseReleaseListener;
import be.yildizgames.module.window.input.MouseRightClickListener;
import be.yildizgames.module.window.input.MouseWheelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This is the parent of all components of the gui.
 *
 * @author Grégory Van Den Borre
 */
public abstract class BaseWidget extends BaseElement implements Widget {

    /**
     * An empty widget.
     */
    static final BaseWidget DUMMY = new DummyWidget();
    /**
     * Parent of the widget.
     */
    private final Container parent;
    /**
     * List of zones to ignore to check if a point is inside the container.
     */
    protected final Set<Rectangle> emptyZones = new HashSet<>();
    /**
     * Listener for the mouse left click event.
     */
    private final List<MouseLeftClickListener> leftClickListener = new ArrayList<>();
    /**
     * Listener for the mouse right click event.
     */
    private final List<MouseRightClickListener> rightClickListener = new ArrayList<>();
    /**
     * Listener for the mouse double left click event.
     */
    private final List<MouseDoubleLeftClickListener> doubleLeftClickListener = new ArrayList<>();
    /**
     * Listener for the mouse release events.
     */
    private final List<MouseReleaseListener> releaseListener = new ArrayList<>();
    /**
     * Listener for the mouse move events.
     */
    private final List<MouseMoveListener> mouseMoveListener = new ArrayList<>();
    /**
     * Listener for the mouse drag(move while holding button) events.
     */
    private final List<MouseDragListener> mouseDragListener = new ArrayList<>();
    /**
     * Listener for the mouse wheel roll events.
     */
    private final List<MouseWheelListener> wheelListener = new ArrayList<>();
    /**
     * Listener for the keyboard events.
     */
    private final List<KeyboardListener> keyboardListener = new ArrayList<>();

    /**
     * Listeners for mouse over state changes.
     */
    private final List<OnMouseOverListener> onMouseOverListenerList = new ArrayList<>();
    /**
     * <code>true</code> if the mouse cursor is currently above the element, of
     * if the element is focused <code>false</code> otherwise.
     */
    private boolean highlighted;
    /**
     * <code>true</code> if the widget can be focused, it is usually the case
     * for widgets associated with keyboard listener.
     */
    private boolean focusable;
    /**
     * <code>true</code> if the widget can be highlighted, it is usually the
     * case for buttons, input box....
     */
    private boolean highlightable;
    /**
     * <code>true</code> if mouse is over.
     */
    private boolean mouseOver;
    /**
     * Value to set active or nor the mouse click listeners.
     */
    private boolean mouseClickActive = true;
    /**
     * To force the contains method to return false, this case will ignore
     * inputs.
     */
    private boolean forceContainsFalse;

    private boolean emptyZoneDisabled;

    private final Map<String, BaseAnimationGui> animations = new HashMap<>();

    /**
     * Full constructor.
     *
     * @param name        Widget unique name.
     * @param coordinates Widget initial size and position.
     */
    protected BaseWidget(final String name, final Coordinates coordinates) {
        this(name, coordinates, null);
    }

    /**
     * Full constructor.
     *
     * @param name        Widget unique name.
     * @param coordinates Widget initial size and position.
     * @param parent      Optional parent container.
     */
    protected BaseWidget(final String name, final Coordinates coordinates, final Container parent) {
        super(name, coordinates);
        this.parent = parent;
    }

    @Override
    public final void addMouseLeftClickListener(final MouseLeftClickListener listener) {
        this.leftClickListener.add(listener);
    }

    @Override
    public final void addMouseRightClickListener(final MouseRightClickListener listener) {
        this.rightClickListener.add(listener);
    }

    @Override
    public final void addMouseDoubleLeftClickListener(final MouseDoubleLeftClickListener listener) {
        this.doubleLeftClickListener.add(listener);
    }

    @Override
    public final void addMouseReleaseListener(final MouseReleaseListener listener) {
        this.releaseListener.add(listener);
    }

    @Override
    public final void removeAllClickListeners() {
        this.leftClickListener.clear();
        this.doubleLeftClickListener.clear();
        this.releaseListener.clear();
        this.rightClickListener.clear();
    }

    @Override
    public final void addKeyboardListener(final KeyboardListener listener) {
        this.keyboardListener.add(listener);
        this.focusable = true;
    }

    @Override
    public final void addMouseMoveListener(final MouseMoveListener listener) {
        this.mouseMoveListener.add(listener);
    }

    @Override
    public final void addMouseWheelListener(final MouseWheelListener listener) {
        this.wheelListener.add(listener);
    }

    @Override
    public final void addMouseDragListener(final MouseDragListener listener) {
        this.mouseDragListener.add(listener);
    }

    @Override
    public final void addOnMouseOverListener(final OnMouseOverListener listener) {
        this.onMouseOverListenerList.add(listener);
    }

    @Override
    public final void registerAnimation(BaseAnimationGui anim) {
        this.animations.put(anim.getName(), anim);
    }

    @Override
    public final void playAnimation(String name) {
        Optional.ofNullable(this.animations.get(name)).ifPresent(BaseAnimationGui::start);
    }

    @Override
    public final void stopAnimation(String name) {
        Optional.ofNullable(this.animations.get(name)).ifPresent(BaseAnimationGui::stop);
    }

    /**
     * Implementation to set the widget highlight.
     *
     * @param highlightState Highlight state.
     */
    protected abstract void highlightImpl(final boolean highlightState);

    @Override
    public final boolean contains(final MousePosition position) {
        return this.contains(position.getX(), position.getY());
    }

    @Override
    public final boolean contains(final int x, final int y) {
        if (this.forceContainsFalse) {
            return false;
        }
        if (!emptyZoneDisabled) {
            for (Rectangle r : this.emptyZones) {
                if (Checker.inRange(x, r.getLeft(), r.getRight()) && Checker.inRange(y, r.getTop(), r.getBottom())) {
                    return false;
                }
            }
        }
        Point2D p = this.getAbsolutePosition();
        return Checker.inRange(x, p.x, p.x + this.getWidth()) && Checker.inRange(y, p.y, p.y + this.virtualHeight);
    }

    @Override
    public final void addEmptyZone(final Rectangle zone) {
        this.emptyZones.add(zone);
        this.addEmptyZoneImpl(zone);
    }

    protected abstract void addEmptyZoneImpl(Rectangle zone);

    /**
     * Called when the mouse left button is pressed. Only to be used from the
     * EventDispatcher.
     *
     * @param position Mouse position.
     */
    public final void mouseLeftClick(MousePosition position) {
        if (this.mouseClickActive) {
            for (final MouseLeftClickListener listener : this.leftClickListener) {
                listener.click();
                listener.clickAt(position);
            }
        }
    }

    public final Point2D getAbsolutePosition() {
        Point2D p = Point2D.valueOf(this.getLeft(), this.getTop());
        p = p.add(this.parent == null ? Point2D.ZERO : this.parent.getAbsolutePosition());
        return p;
    }

    /**
     * Called when the mouse left button is pressed twice. Only to be used from
     * the EventDispatcher.
     *
     * @param position Mouse position.
     */
    final void mouseDoubleClick(final MousePosition position) {
        if (this.mouseClickActive) {
            for (final MouseDoubleLeftClickListener listener : this.doubleLeftClickListener) {
                listener.click(position);
            }
        }
    }

    /**
     * Called when the mouse right button is pressed. Only to be used from the
     * EventDispatcher.
     *
     * @param position Mouse position.
     */
    final void mouseRightClick(final MousePosition position) {
        if (this.mouseClickActive) {
            for (final MouseRightClickListener listener : this.rightClickListener) {
                listener.click(position);
            }
        }
    }

    /**
     * Called when the mouse button is released. Only to be used from the
     * EventDispatcher.
     *
     * @param position Mouse position.
     */

    final void mouseLeftReleased(final MousePosition position) {
        if (this.mouseClickActive) {
            for (final MouseReleaseListener listener : this.releaseListener) {
                listener.mouseLeftReleased(position);
            }
            for (final MouseDragListener listener : this.mouseDragListener) {
                listener.mouseDragLeftReleased(position);
            }
        }
    }

    /**
     * Called when the mouse button is released. Only to be used from the
     * EventDispatcher.
     *
     * @param position Mouse position.
     */

    final void mouseRightReleased(final MousePosition position) {
        if (this.mouseClickActive) {
            for (final MouseReleaseListener listener : this.releaseListener) {
                listener.mouseRightReleased(position);
            }
            for (final MouseDragListener listener : this.mouseDragListener) {
                listener.mouseDragRightReleased(position);
            }
        }
    }

    /**
     * Called when the mouse is dragged with the left button. Only to be used
     * from the EventDispatcher.
     *
     * @param position Mouse current position.
     * @param delta    Difference between the current mouse position and the last
     *                 position.
     */
    final void mouseDragLeft(final MousePosition position, final MousePosition delta) {
        for (final MouseDragListener listener : this.mouseDragListener) {
            listener.mouseDragLeft(position, delta);
        }
    }

    /**
     * Called when the mouse is dragged with the right button. Only to be used
     * from the EventDispatcher.
     *
     * @param position Mouse current position.
     * @param delta    Difference between the current mouse position and the last
     *                 position.
     */
    final void mouseDragRight(final MousePosition position, final MousePosition delta) {
        for (final MouseDragListener listener : this.mouseDragListener) {
            listener.mouseDragRight(position, delta);
        }
    }

    /**
     * Called when the mouse wheel is rolled. Only to be used from the
     * EventDispatcher.
     *
     * @param mousePosition Mouse position.
     * @param count         Wheel roll count.
     */
    final void mouseWheel(final MousePosition mousePosition, final int count) {
        for (final MouseWheelListener listener : this.wheelListener) {
            listener.scroll(mousePosition, count);
        }
    }

    /**
     * Called when the mouse is dragged with the middle button. Only to be used
     * from the EventDispatcher.
     *
     * @param position Mouse current position.
     * @param delta    Difference between the current mouse position and the last
     *                 position.
     */
    final void mouseDragWheel(final MousePosition position, final MousePosition delta) {
        for (final MouseDragListener listener : this.mouseDragListener) {
            listener.mouseDragWheel(position, delta);
        }
    }

    /**
     * Called when the mouse is moved. Only to be used from the EventDispatcher.
     *
     * @param position Current mouse position.
     * @return false.
     */
    final boolean mouseMove(final MousePosition position) {
        for (final MouseMoveListener listener : this.mouseMoveListener) {
            listener.move(position);
        }
        return false;
    }

    /**
     * Called when a key is pressed on the keyboard. Only to be used from the
     * EventDispatcher.
     *
     * @param key Character pressed.
     */
    public final void keyPressed(final char key) {
        boolean received = false;
        if (this.isVisible()) {
            for (final KeyboardListener listener : this.keyboardListener) {
                if (!received) {
                    received = listener.keyPressed(key);
                } else {
                    listener.keyPressed(key);
                }
            }
        }
        if (!received) {
            Optional.ofNullable(this.parent).ifPresent(p -> p.keyPressed(key));
        }
    }

    /**
     * Called when a special key is pressed on the keyboard. Only to be used
     * from the EventDispatcher.
     *
     * @param keyCode Key pressed value.
     */
    public final void specialKeyPressed(final Key keyCode) {
        if (this.isVisible()) {
            for (final KeyboardListener listener : this.keyboardListener) {
                listener.specialKeyPressed(keyCode);
            }
        }
    }

    /**
     * Called when a keyboard key is released.
     *
     * @param keyCode Code of the released key.
     */
    final void keyReleased(final char keyCode) {
        if (this.isVisible()) {
            for (final KeyboardListener listener : this.keyboardListener) {
                listener.keyReleased(keyCode);
            }
        }
    }

    /**
     * Called when a keyboard key is released.
     *
     * @param keyCode Code of the released key.
     */
    final void specialKeyReleased(final Key keyCode) {
        if (this.isVisible()) {
            for (final KeyboardListener listener : this.keyboardListener) {
                listener.specialKeyReleased(keyCode);
            }
        }
    }

    /**
     * Called when the highlight status must be updated.
     *
     * @param over New highlight status.
     */
    public final void highlight(final boolean over) {
        if (this.isVisible() && this.highlightable && this.highlighted != over) {
            this.highlighted = over;
            this.highlightImpl(over);
        }
    }

    /**
     * Called when the focus status must be updated.
     *
     * @param over New focus status.
     */
    @Override
    public final void focus(final boolean over) {
        if (this.isVisible() && this.focusable && this.highlighted != over) {
            this.highlighted = over;
            this.highlightImpl(over);
        }
    }

    @Override
    public final void align(final Alignment alignment) {
        if (alignment == Alignment.CENTER) {
            this.addToLeft(-this.getWidth() >> 1);
            this.addToTop(-this.getHeight() >> 1);
        } else {
            throw new IllegalArgumentException(alignment + " is not a valid option.");
        }
    }

    /**
     * Activate or disable the mouse button action on this widget.
     *
     * @param active <code>true</code> will set the mouse button active,
     *               <code>false</code> will deactivate them.
     */
    protected final void setMouseClickActive(final boolean active) {
        this.mouseClickActive = active;
    }

    /**
     * @return <code>true</code> if the widget can be focused, false otherwise.
     */
    @Override
    public final boolean isFocusable() {
        return this.focusable;
    }

    /**
     * Set the widget to be considered as focusable or not.
     *
     * @param focusable <code>true</code> to set the widget focusable.
     */
    public final void setFocusable(final boolean focusable) {
        this.focusable = focusable;
    }

    /**
     * @return <code>true</code> if the widget can be highlighted, false
     * otherwise.
     */
    final boolean isHighlightable() {
        return this.highlightable;
    }

    /**
     * Set the widget to be considered as hightligthable or not.
     *
     * @param value <code>true</code> to make the widget highlitable.
     */
    protected final void setHighlightable(final boolean value) {
        this.highlightable = value;
    }

    /**
     * Called when the mouse enter or get out of a widget.
     *
     * @param over     New mouse over status.
     * @param position Current mouse position.
     * @return This object for chaining.
     */
    public final Element setMouseOver(final boolean over, final MousePosition position) {
        if (over != this.mouseOver) {
            this.mouseOver = over;
            for (OnMouseOverListener listener : this.onMouseOverListenerList) {
                listener.notify(over, position);
            }
        }
        return this;
    }

    /**
     * @return <code>true</code> if the mouse is over this widget.
     */
    protected final boolean isMouseOver() {
        return this.mouseOver;
    }

    /**
     * Force the contains method to always return false, in order to ignore
     * inputs.
     */
    public final void forceContainsFalse() {
        this.forceContainsFalse = true;
    }

    /**
     * @return The class name.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + this.getName();
    }

    @Override
    public final int getAbsoluteLeft() {
        return this.getLeft() + (this.parent == null ? 0 : this.parent.getAbsoluteLeft());
    }

    @Override
    public final int getAbsoluteTop() {
        return this.getTop() + (this.parent == null ? 0 : this.parent.getAbsoluteTop());
    }

    public boolean isContainer() {
        return true;
    }

    public final void disableEmptyZone() {
        this.emptyZoneDisabled = true;
    }

    public final void enableEmptyZone() {
        this.emptyZoneDisabled = false;
    }

    public Optional<Container> findParent() {
        return Optional.ofNullable(this.parent);
    }

    /**
     * Empty Widget, does nothing.
     *
     * @author Van den Borre Grégory
     */
    private static class DummyWidget extends BaseWidget {

        /**
         * Simple constructor.
         */
        public DummyWidget() {
            super("DUMMY", FullCoordinates.ZERO);
        }

        /**
         * Does nothing.
         */
        @Override
        public void delete() {
            //does nothing.
        }

        /**
         * Does nothing.
         *
         * @param over Unused.
         */
        @Override
        public void highlightImpl(final boolean over) {
            //does nothing.
        }

        /**
         * Does nothing.
         *
         * @param posX Unused.
         * @param posY Unused.
         */
        @Override
        public BaseWidget setPositionImpl(final int posX, final int posY) {
            return this;
        }

        /**
         * Does nothing.
         *
         * @param width  Unused.
         * @param height Unused.
         */
        @Override
        public void setSizeImpl(final int width, final int height) {
            //does nothing.
        }

        /**
         * Does nothing.
         */
        @Override
        protected void hideImpl() {
            //does nothing.
        }

        /**
         * Does nothing.
         */
        @Override
        protected void showImpl() {
            //does nothing.
        }

        /**
         * Does nothing.
         */
        @Override
        protected void addEmptyZoneImpl(Rectangle zone) {
            //does nothing.
        }
    }
}
