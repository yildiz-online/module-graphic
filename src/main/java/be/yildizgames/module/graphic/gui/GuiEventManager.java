/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

import be.yildizgames.common.client.debug.DebugListener;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseWidget;
import be.yildizgames.module.graphic.gui.internal.impl.HandledContainer;
import be.yildizgames.module.window.input.WindowInputListener;

/**
 * @author Grégory Van den Borre
 */
public interface GuiEventManager extends WindowInputListener {

    /**
     * Add a container to the dispatcher and set all listeners list for that view.
     *
     * @param container Container to add.
     */
    void addContainer(HandledContainer container);

    /**
     * Remove a container from the event dispatch.
     *
     * @param container Container to remove, it will no longer be affected by mouse and keyboard events.
     */
    void removeContainer(HandledContainer container);

    /**
     * Set the focus on a particular container.
     *
     * @param container Container to set the focus on.
     */
    void setFocus(Container container);

    void setFocus(Widget widget);

    /**
     * @return The current widget under the mouse.
     */
    BaseWidget getWidgetUnderMouse();

    void setDebugListener(DebugListener listener);

    void setDefaultContainer(Container container);

}