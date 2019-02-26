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
import be.yildizgames.module.graphic.gui.Widget;
import be.yildizgames.module.graphic.gui.Zorder;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.impl.HandledContainer;
import be.yildizgames.module.window.input.Key;
import be.yildizgames.module.window.input.MousePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class EventBubblingDispatcher implements GuiEventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventBubblingDispatcher.class);

    private int containerNumber = 1;

    private final List<HandledContainer> views = new ArrayList<>();

    private BaseWidget currentWidgetActive = BaseWidget.DUMMY;

    private BaseWidget widgetUnderMouse = BaseWidget.DUMMY;

    private DebugListener debugListener = DebugListener.EMPTY;

    private BaseWidget defaultWidget = BaseWidget.DUMMY;

    public void setDebugListener(DebugListener l) {
        this.debugListener = l;
    }

    @Override
    public void keyboardKeyPressed(final char character) {
        this.debugListener.displayDebugMessage("Key " + character + " pressed for: " + this.currentWidgetActive);
        this.currentWidgetActive.keyPressed(character);
    }

    @Override
    public void specialKeyPressed(Key key) {
        this.debugListener.displayDebugMessage("Key " + key + " pressed for: " + this.currentWidgetActive);
        this.currentWidgetActive.specialKeyPressed(key);
    }

    @Override
    public void keyboardKeyReleased(char keyCode) {
        this.currentWidgetActive.keyReleased(keyCode);
    }

    @Override
    public void specialKeyReleased(Key key) {
        this.currentWidgetActive.specialKeyReleased(key);
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
        this.setFocus(this.widgetUnderMouse);
        this.widgetUnderMouse.mouseLeftClick(position);
    }

    @Override
    public void setFocus(Widget widget) {
        this.currentWidgetActive.highlightImpl(false);
        this.currentWidgetActive = (BaseWidget) widget;
        this.currentWidgetActive.highlightImpl(true);
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
        boolean dirty = false;
        for (HandledContainer v : this.views) {
            if (v.isVisible() && v.isDirty()) {
                v.clearDirty();
                dirty = true;
            }
        }
        if (dirty) {
            Collections.sort(this.views);
        }
        this.recomputeMouseOver(position);
    }

    private void recomputeMouseOver(MousePosition position) {
        for (HandledContainer v : this.views) {
            if (v.isVisible()) {
                Optional<Widget> foundWidget = v.getWidgetAt(position);
                if (foundWidget.isPresent() && foundWidget.get() != this.widgetUnderMouse) {
                    this.widgetUnderMouse.highlight(false);
                    this.widgetUnderMouse.setMouseOver(false, position);

                    this.widgetUnderMouse = BaseWidget.class.cast(foundWidget.get());
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
    public void addContainer(HandledContainer container) {
        this.containerNumber++;
        container.setZ(new Zorder(this.containerNumber));
        this.views.add(container);
        Collections.sort(this.views);
    }

    @Override
    public void setDefaultContainer(Container container) {
        this.defaultWidget = BaseWidget.class.cast(container);
    }

    @Override
    public void removeContainer(HandledContainer container) {
        this.views.remove(container);
    }

    @Override
    public void setFocus(Container container) {
        this.debugListener.displayDebugMessage("New focus:" + container.getName());
        this.currentWidgetActive = BaseWidget.class.cast(container);
    }

    @Override
    public BaseWidget getWidgetUnderMouse() {
        return this.widgetUnderMouse;
    }
}
