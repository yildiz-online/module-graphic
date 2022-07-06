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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.graphic.gui.DummyGuiFactory;
import be.yildizgames.module.graphic.gui.WidgetMock;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.impl.StandardGuiFactory;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MousePosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Grégory Van den Borre
 */
class WidgetTest {

    private BaseWidget givenAWidget() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Container c = builder
                .container()
                .withSize(FullCoordinates.size(50))
                .atPosition(FullCoordinates.position(30))
                .build();
        return new WidgetMock(FullCoordinates.full(10, 10, 10, 10), c);
    }

    @Test
    void testWidget() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Container c = builder
                .container()
                .withSize(10, 10)
                .atPosition(10, 10)
                .build();

        BaseWidget w = new WidgetMock(FullCoordinates.full(10, 10, 10, 10), c);
        assertEquals(c, w.findParent().get());
    }

    @Test
    void testAddMouseClickListener() {
        BaseWidget w = givenAWidget();
        List<MousePosition> list = new ArrayList<>();
        w.mouseLeftClick(MouseMoveMockFactory.get(12, 14));
        assertTrue(list.isEmpty());
        w.addMouseLeftClickListener(new MouseLeftClickListener() {

            @Override
            public void click() {
            }

            public void clickAt(MousePosition p) {
                list.add(p);
            }
        });
        w.mouseLeftClick(MouseMoveMockFactory.get(12, 14));
        assertEquals(MouseMoveMockFactory.get(12, 14), list.get(0));
    }

    @Test
    void testAddKeyboardListener() {
        BaseWidget w = givenAWidget();
        List<Character> list = new ArrayList<>();
        w.keyPressed('e');
        assertTrue(list.isEmpty());
        w.addKeyboardListener(list::add);
        w.keyPressed('e');
        assertEquals('e', (char) list.get(0));
    }

    @Test
    void testAddMouseMoveListener() {
        BaseWidget w = givenAWidget();
        List<MousePosition> list = new ArrayList<>();
        w.mouseMove(MouseMoveMockFactory.get(4, 89));
        assertTrue(list.isEmpty());
        w.addMouseMoveListener(list::add);
        w.mouseMove(MouseMoveMockFactory.get(4, 89));
        assertEquals(MouseMoveMockFactory.get(4, 89), list.get(0));
    }

    @Test
    void testShowHide() {
        BaseWidget w = givenAWidget();
        w.show();
        assertTrue(w.isVisible());
        w.hide();
        assertFalse(w.isVisible());
        w.show();
        assertTrue(w.isVisible());
    }

    @Test
    void testAddToLeft() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getLeft());
        w.addToLeft(12);
        assertEquals(22, w.getLeft());
    }

    @Test
    void testAddToTop() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getTop());
        w.addToTop(2);
        assertEquals(12, w.getTop());
    }

    @Test
    void testAddToPosition() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getTop());
        assertEquals(10, w.getLeft());
        w.addToPosition(45, -2);
        assertEquals(55, w.getLeft());
        assertEquals(8, w.getTop());
    }

    @Test
    void testSetIsFocusable() {
        BaseWidget w = givenAWidget();
        assertFalse(w.isFocusable());
        w.setFocusable(true);
        assertTrue(w.isFocusable());
        w.setFocusable(false);
        assertFalse(w.isFocusable());
    }
}
