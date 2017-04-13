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

import be.yildiz.common.client.debug.DebugListener;
import be.yildiz.common.client.gui.listener.ArrowKey;
import be.yildiz.common.client.gui.listener.SpecialKey;
import be.yildiz.common.collections.Sets;
import be.yildiz.common.vector.Point2D;

import java.util.Optional;
import java.util.Set;

/**
 * @author Grégory Van den Borre
 */
public class EventBubblingDispatcher implements GuiEventManager {

    private final Set<View> views = Sets.newOrderedSet();

    private Widget currentWidgetFocus = Widget.DUMMY;

    private Widget widgetUnderMouse = Widget.DUMMY;

    private DebugListener debugListener = DebugListener.EMPTY;

    private Widget defaultWidget = Widget.DUMMY;

    public void setDebugListener(DebugListener l) {
        this.debugListener = l;
    }

    @Override
    public void keyboardKeyPressed(final char character) {
        this.debugListener.displayDebugMessage("Key " + character + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.keyPressed(character);
    }

    @Override
    public void keyboardEnterKeyPressed() {
        this.debugListener.displayDebugMessage("Key enter pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.enterKeyPressed();

    }

    @Override
    public void specialKeyPressed(SpecialKey key) {
        this.debugListener.displayDebugMessage("Key " + key + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.specialKeyPressed(key);

    }

    @Override
    public void keyboardNumberPressed(int number) {
        this.debugListener.displayDebugMessage("Key " + number + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.numberKeyPressed(number);

    }

    @Override
    public void keyboardDeleteKeyPressed() {
        this.debugListener.displayDebugMessage("Key delete pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.deleteKeyPressed();

    }

    @Override
    public void keyboardArrowPressed(ArrowKey key) {
        this.debugListener.displayDebugMessage("Key " + key + " pressed for: " + this.currentWidgetFocus);
        this.currentWidgetFocus.arrowKeyPressed(key);

    }

    @Override
    public void keyReleased(int keyCode) {
        this.currentWidgetFocus.keyReleased(keyCode);
    }

    @Override
    public void mouseLeftReleased(Point2D position) {
        this.widgetUnderMouse.mouseLeftReleased(position);
    }

    @Override
    public void mouseRightReleased(Point2D position) {
        this.widgetUnderMouse.mouseRightReleased(position);
    }

    @Override
    public void mouseLeftClick(int x, int y) {
        this.debugListener.displayDebugMessage("Left click on: " + this.widgetUnderMouse);
        this.currentWidgetFocus.highlightImpl(false);
        this.currentWidgetFocus = widgetUnderMouse;
        this.widgetUnderMouse.mouseLeftClick(x, y);
    }

    @Override
    public void mouseRightClick(Point2D position) {
        this.debugListener.displayDebugMessage("Right click on: " + this.widgetUnderMouse);
        this.widgetUnderMouse.mouseRightClick(position);
    }

    @Override
    public void mouseDoubleClick(Point2D position) {
        this.widgetUnderMouse.mouseDoubleClick(position);
    }

    @Override
    public void mouseDragRight(Point2D position, Point2D delta) {
        this.widgetUnderMouse.mouseDragRight(position, delta);
    }

    @Override
    public void mouseDragLeft(Point2D position, Point2D delta) {
        this.widgetUnderMouse.mouseDragLeft(position, delta);

    }

    @Override
    public void mouseDragWheel(Point2D position, Point2D delta) {
        this.widgetUnderMouse.mouseDragWheel(position, delta);
    }

    @Override
    public void mouseMove(Point2D position) {
        for (View v : this.views) {
            if (v.isVisible()) {
                GuiContainer viewContainer = v.getContainer();
                Optional<Widget> foundWidget = viewContainer.getWidgetAt(position);
                if (foundWidget.isPresent() && foundWidget.get() != this.widgetUnderMouse) {
                    this.widgetUnderMouse.highlight(false);
                    this.widgetUnderMouse.setMouseOver(false, position);

                    this.widgetUnderMouse = foundWidget.get();
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
    public void mouseWheel(Point2D cursorPosition, int count) {
        this.widgetUnderMouse.mouseWheel(cursorPosition, count);
    }

    @Override
    public void addView(View view) {
        if(!this.views.add(view)) {
            throw new IllegalArgumentException(view + " was not added successfully.");
        }
    }

    @Override
    public void setDefaultView(View view) {
        this.defaultWidget = view.getContainer();
    }

    @Override
    public void removeView(View view) {
        this.views.remove(view);
    }

    @Override
    public void setFocus(View view) {
        this.debugListener.displayDebugMessage("New focus:" + view.getContainer().getName());
        this.currentWidgetFocus = view.getContainer();
    }

    @Override
    public Widget getWidgetUnderMouse() {
        return this.widgetUnderMouse;
    }

}
