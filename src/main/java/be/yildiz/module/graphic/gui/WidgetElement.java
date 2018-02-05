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

package be.yildiz.module.graphic.gui;

import be.yildiz.module.window.input.KeyboardListener;
import be.yildiz.module.window.input.MouseDoubleLeftClickListener;
import be.yildiz.module.window.input.MouseDragListener;
import be.yildiz.module.window.input.MouseLeftClickListener;
import be.yildiz.module.window.input.MouseMoveListener;
import be.yildiz.module.window.input.MousePosition;
import be.yildiz.module.window.input.MouseReleaseListener;
import be.yildiz.module.window.input.MouseWheelListener;
import be.yildiz.module.window.input.SpecialKeyPressedListener;
import be.yildizgames.common.geometry.Rectangle;

/**
 * @author Grégory Van den Borre
 */
public interface WidgetElement extends Element {

    /**
     * Add a new MouseClickListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseLeftClickListener(MouseLeftClickListener listener);

    /**
     * Add a new MouseDoubleClickListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseDoubleLeftClickListener(MouseDoubleLeftClickListener listener);

    /**
     * Add a new MouseReleaseClickListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseReleaseListener(MouseReleaseListener listener);

    /**
     * Clear the list of all click listeners.
     */
    void removeAllClickListeners();

    /**
     * Add a new KeyboardListener on this widget and set the widget focusable.
     *
     * @param listener Listener to add.
     */
    void addKeyboardListener(KeyboardListener listener);

    /**
     * Add a new SpecialKeyListener on this widget and set the widget focusable.
     *
     * @param listener Listener to add.
     */
    void addSpecialKeyListener(SpecialKeyPressedListener listener);

    /**
     * Add a new MouseMoveListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseMoveListener(MouseMoveListener listener);

    /**
     * Add a new MouseWheelListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseWheelListener(MouseWheelListener listener);

    /**
     * Add a new MouseDragListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseDragListener(MouseDragListener listener);

    /**
     * Add a new OnMouseOverListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addOnMouseOverListener(OnMouseOverListener listener);

    /**
     * Check if a point in contained in this container.
     *
     * @param position Point coordinates.
     * @return <code>true</code> if the point is contained in this container,
     * <code>false</code> otherwise.
     */
    boolean contains(MousePosition position);

    boolean contains(int x, int y);

    /**
     * Add a zone to ignore when checking if the container contains the mouse,
     * if the mouse position is inside this zone, it will be considered as
     * outside of the container.
     *
     * @param zone Empty zone.
     */
    void addEmptyZone(Rectangle zone);

    /**
     * Align the widget
     *
     * @param alignment New alignment value.
     */
    void align(Alignment alignment);

    /**
     * Give the absolute left position instead of relative from parent.
     *
     * @return The parent left position + the widget left position.
     */
    int getAbsoluteLeft();

    /**
     * Give the absolute top position instead of relative from parent.
     *
     * @return The parent top position + the widget top position.
     */
    int getAbsoluteTop();

    void registerAnimation(GuiAnimation anim);

    void playAnimation(String animation);

    void stopAnimation(String name);
}
