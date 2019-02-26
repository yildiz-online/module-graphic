/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.module.graphic.gui;

import be.yildizgames.module.window.input.Key;
import be.yildizgames.module.window.input.KeyboardListener;

public class WidgetActiveComputer implements KeyboardListener {

    private final Widget[] widgets;

    private final GuiEventManager eventManager;

    private int current = 0;

    public WidgetActiveComputer(GuiEventManager eventManager, Widget... widgets) {
        super();
        this.eventManager = eventManager;
        if(widgets == null) {
            this.widgets = new Widget[0];
        } else {
            this.widgets = widgets;
        }
    }

    @Override
    public boolean keyPressed(char c) {
        return false;
    }

    @Override
    public void specialKeyPressed(Key key) {
        if(key == Key.TAB && widgets.length > 0) {
            this.current++;
            if (this.current > widgets.length) {
                this.current = 0;
            }
            this.eventManager.setFocus(this.widgets[this.current]);
        }
    }
}
