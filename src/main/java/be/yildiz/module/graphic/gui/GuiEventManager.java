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

import be.yildiz.module.window.input.WindowInputListener;
import be.yildizgames.common.client.debug.DebugListener;

/**
 * @author Grégory Van den Borre
 */
public interface GuiEventManager extends WindowInputListener {

    /**
     * Add a view to the dispatcher and set all listeners list for that view.
     *
     * @param view View to add.
     */
    void addView(View view);

    /**
     * Remove a View from the event dispatch.
     *
     * @param view View to remove, it will no longer be affected by mouse and keyboard events.
     */
    void removeView(View view);

    /**
     * Set the focus on a particular View.
     *
     * @param view View to set the focus on.
     */
    void setFocus(View view);

    /**
     * @return The current widget under the mouse.
     */
    Widget getWidgetUnderMouse();

    void setDebugListener(DebugListener listener);

    void setDefaultView(View view);

}