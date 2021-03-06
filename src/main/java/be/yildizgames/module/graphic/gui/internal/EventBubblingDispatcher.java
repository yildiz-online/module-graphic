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

import be.yildizgames.common.client.debug.DebugListener;
import be.yildizgames.module.graphic.gui.GuiEventManager;
import be.yildizgames.module.graphic.gui.View;
import be.yildizgames.module.graphic.gui.Widget;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.window.input.Key;
import be.yildizgames.module.window.input.MousePosition;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Grégory Van den Borre
 */
public class EventBubblingDispatcher implements GuiEventManager {

    private static final System.Logger LOGGER = System.getLogger(EventBubblingDispatcher.class.getName());

    private final Set<View> views = new TreeSet<>();

    private BaseWidget currentWidgetFocus = BaseWidget.DUMMY;

    private BaseWidget widgetUnderMouse = BaseWidget.DUMMY;

    private DebugListener debugListener = DebugListener.EMPTY;

    private BaseWidget defaultWidget = BaseWidget.DUMMY;

    public void setDebugListener(DebugListener l) {
        this.debugListener = l;
    }

    @Override
    public void keyboardKeyPressed(final char character) {
        this.debugListener.displayDebugMessage("Key " + character + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.keyPressed(character);
    }

    @Override
    public void specialKeyPressed(Key key) {
        this.debugListener.displayDebugMessage("Key " + key + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.specialKeyPressed(key);
    }


    @Override
    public void keyboardKeyReleased(char keyCode) {
        this.currentWidgetFocus.keyReleased(keyCode);
    }

    @Override
    public void specialKeyReleased(Key key) {
        this.currentWidgetFocus.specialKeyReleased(key);
    }

    @Override
    public void mouseLeftReleased(MousePosition position) {
        this.widgetUnderMouse.mouseLeftReleased(position);
    }

    @Override
    public void mouseRightReleased(MousePosition position) {
        this.widgetUnderMouse.mouseRightReleased(position);
    }

    @Override
    public void mouseLeftClick(MousePosition position) {
        this.debugListener.displayDebugMessage("Left click on: " + this.widgetUnderMouse);
        this.currentWidgetFocus.highlightImpl(false);
        this.currentWidgetFocus = widgetUnderMouse;
        this.widgetUnderMouse.mouseLeftClick(position);
    }

    @Override
    public void mouseRightClick(MousePosition position) {
        this.debugListener.displayDebugMessage("Right click on: " + this.widgetUnderMouse);
        this.widgetUnderMouse.mouseRightClick(position);
    }

    @Override
    public void mouseDoubleClick(MousePosition position) {
        this.widgetUnderMouse.mouseDoubleClick(position);
    }

    @Override
    public void mouseDragRight(MousePosition position, MousePosition delta) {
        this.widgetUnderMouse.mouseDragRight(position, delta);
    }

    @Override
    public void mouseDragLeft(MousePosition position, MousePosition delta) {
        this.widgetUnderMouse.mouseDragLeft(position, delta);

    }

    @Override
    public void mouseDragWheel(MousePosition position, MousePosition delta) {
        this.widgetUnderMouse.mouseDragWheel(position, delta);
    }

    @Override
    public void mouseMove(MousePosition position) {
        for (View v : this.views) {
            if (v.isVisible() && v.isActive()) {
                Container viewContainer = v.getContainer();
                Optional<Widget> foundWidget = viewContainer.getWidgetAt(position);
                if (foundWidget.isPresent() && foundWidget.get() != this.widgetUnderMouse) {
                    this.widgetUnderMouse.highlight(false);
                    this.widgetUnderMouse.setMouseOver(false, position);

                    this.widgetUnderMouse = (BaseWidget) foundWidget.get();
                    this.widgetUnderMouse.highlight(true);
                    this.debugListener.displayDebugMessage(this.widgetUnderMouse);
                    this.widgetUnderMouse.mouseMove(position);
                    this.widgetUnderMouse.setMouseOver(true, position);
                    return;
                } else if (foundWidget.isPresent()) {
                    this.widgetUnderMouse.mouseMove(position);
                    return;
                }
            }
        }
        this.widgetUnderMouse.highlight(false);
        this.widgetUnderMouse.setMouseOver(false, position);
        this.widgetUnderMouse = this.defaultWidget;
        this.widgetUnderMouse.mouseMove(position);
    }

    @Override
    public void mouseWheel(MousePosition cursorPosition, int count) {
        this.widgetUnderMouse.mouseWheel(cursorPosition, count);
    }

    @Override
    public void addView(View view) {
        if (!this.views.add(view)) {
            LOGGER.log(System.Logger.Level.ERROR, "{0} was not added successfully.", view);
            LOGGER.log(System.Logger.Level.ERROR, "Views already registered: {0}", views.toArray());
        }
    }

    @Override
    public void setDefaultView(View view) {
        this.defaultWidget = (BaseWidget) view.getContainer();
    }

    @Override
    public void removeView(View view) {
        this.views.remove(view);
    }

    @Override
    public void setFocus(View view) {
        this.debugListener.displayDebugMessage("New focus:" + view.getContainer().getName());
        this.currentWidgetFocus = (BaseWidget) view.getContainer();
    }

    @Override
    public BaseWidget getWidgetUnderMouse() {
        return this.widgetUnderMouse;
    }
}
