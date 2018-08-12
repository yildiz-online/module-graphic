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
import be.yildizgames.module.graphic.gui.internal.BaseAnimationGui;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.window.input.KeyboardListener;
import be.yildizgames.module.window.input.MouseDoubleLeftClickListener;
import be.yildizgames.module.window.input.MouseDragListener;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;
import be.yildizgames.module.window.input.MouseReleaseListener;
import be.yildizgames.module.window.input.MouseRightClickListener;
import be.yildizgames.module.window.input.MouseWheelListener;

/**
 * @author Grégory Van den Borre
 */
public interface Widget extends Element {

    /**
     * Add a new MouseClickListener on this widget.
     *
     * @param listener Listener to add.
     */
    void addMouseLeftClickListener(MouseLeftClickListener listener);

    void addMouseRightClickListener(MouseRightClickListener listener);

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

    void registerAnimation(BaseAnimationGui anim);

    void playAnimation(String animation);

    void stopAnimation(String name);

    void focus(boolean b);

    boolean isFocusable();

    void highlight(boolean b);
}
