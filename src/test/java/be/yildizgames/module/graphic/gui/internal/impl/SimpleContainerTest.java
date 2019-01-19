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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.gui.DummyGuiFactory;
import be.yildizgames.module.graphic.gui.WidgetMock;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseWidget;
import be.yildizgames.module.graphic.gui.internal.MouseMoveMockFactory;
import be.yildizgames.module.graphic.material.Material;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Grégory Van den Borre
 */
final class SimpleContainerTest {

    private static final Coordinates CR = new Coordinates(new Size(10), BaseCoordinate.ZERO.left, BaseCoordinate.ZERO.top);

    /**
     * Scenario: <li>A: 2 widgets(W1,W2) in the same container, expected: W1,W2,W1,W2</li> <li>B: 1 more widget(W3) in the same container, expected: W3,W1,W2,W3,W1,W2,W3</li> <li>C: 1 more widget(W4)
     * in a child container, expected: W4,W1,W2,W3,W4,W1,W2,W3,W4</li> <li>D: 1 more widget(W5) in the parent container, expected: W1,W2,W3,W5(parent container has priority on child),W4,W1,W2,W3,W5,W4
     * </li> <li>E: 1 more widget(W6) in the parent container, not focusable, expected: same as D</li>
     */
    @Test
    void functionalTestGetNextFocusableElement() {
        StandardGuiFactory builder = new DummyGuiFactory();
        SimpleContainerBuilder cb = new SimpleContainerBuilder(builder);
        SimpleContainer c = (SimpleContainer)cb.withCoordinates(new Coordinates(50, 60, 10, 20)).build();
        // A
        BaseWidget w1 = new WidgetMock("w1", CR, c);
        w1.setFocusable(true);
        BaseWidget w2 = new WidgetMock("w2", CR, c);
        w2.setFocusable(true);
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        // B
        BaseWidget w3 = new WidgetMock("w3", CR, c);
        w3.setFocusable(true);
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        // C
        SimpleContainer c2 = (SimpleContainer) new SimpleContainerBuilder(builder)
                .withCoordinates(new Coordinates(50, 60, 10, 20))
                .withParent(c)
                .build();
        BaseWidget w4 = new WidgetMock("w4", CR, c2);
        w4.setFocusable(true);
        assertEquals(w4, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        // D
        BaseWidget w5 = new WidgetMock("w5", CR, c);
        w5.setFocusable(true);
        assertEquals(w5, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w5, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w5, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        // E
        new WidgetMock("w6", CR, c);
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w5, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
        assertEquals(w1, c.getNextFocusableElement());
        assertEquals(w2, c.getNextFocusableElement());
        assertEquals(w3, c.getNextFocusableElement());
        assertEquals(w5, c.getNextFocusableElement());
        assertEquals(w4, c.getNextFocusableElement());
    }

    @Test
    void testGetNextFocusableElement() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Container c = new SimpleContainerBuilder(builder).withCoordinates(new Coordinates(50, 60, 10, 20)).build();
        assertNull(c.getNextFocusableElement());
        BaseWidget w1 = new WidgetMock("w1", CR, c);
        assertNull(c.getNextFocusableElement());
        w1.setFocusable(true);
        assertEquals(w1, c.getNextFocusableElement());
    }

    @Test
    void testGuiContainer() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Coordinates cr = new Coordinates(50, 60, 10, 20);
        SimpleContainer c = (SimpleContainer)new SimpleContainerBuilder(builder).withName("test").withCoordinates(cr).build();
        assertEquals("test", c.getName());
        assertEquals(cr.left, c.getLeft());
        assertEquals(cr.top, c.getTop());
        assertEquals(cr.width, c.getWidth());
        assertEquals(cr.height, c.getHeight());
        assertEquals(cr, c.getCoordinates());
        assertEquals(Material.empty(), c.getMaterial());
    }

    @Test
    void testContains() {
        StandardGuiFactory builder = new DummyGuiFactory();
        final int cWidth = 50;
        final int cHeight = 60;
        final int cX = 10;
        final int cY = 20;

        final int c2Width = 20;
        final int c2Height = 20;
        final int c2X = 10;
        final int c2Y = 10;

        SimpleContainer c = (SimpleContainer)new SimpleContainerBuilder(builder).withCoordinates(new Coordinates(cWidth, cHeight, cX, cY)).build();
        for (int x = cX; x <= cX + cWidth; x++) {
            for (int y = cY; y <= cY + cHeight; y++) {
                assertTrue(c.contains(MouseMoveMockFactory.get(x, y)));
            }
        }
        assertFalse(c.contains(MouseMoveMockFactory.get(cX - 1, cY + 2)));
        assertFalse(c.contains(MouseMoveMockFactory.get(cX + 2, cY - 1)));
        SimpleContainer c2 = (SimpleContainer)new SimpleContainerBuilder(builder)
                .withCoordinates(new Coordinates(c2Width, c2Height, c2X, c2Y))
                .withParent(c)
                .build();

        for (int x = c2X + cX; x <= c2X + cX + c2Width; x++) {
            for (int y = c2Y + cY; y <= c2Y + cY + c2Width; y++) {
                assertTrue(c2.contains(MouseMoveMockFactory.get(x, y)));
            }
        }
        assertFalse(c2.contains(MouseMoveMockFactory.get(19, 30)));
        assertFalse(c2.contains(MouseMoveMockFactory.get(20, 29)));
    }

    @Test
    void testContainsVirtualHeight() {
        StandardGuiFactory builder = new DummyGuiFactory();
        SimpleContainer c = (SimpleContainer)new SimpleContainerBuilder(builder).withSize(new Size(50)).build();
        assertTrue(c.contains(MouseMoveMockFactory.get(10, 10)));
        assertFalse(c.contains(MouseMoveMockFactory.get(10, 60)));
        c.setVirtualHeight(80);
        assertTrue(c.contains(MouseMoveMockFactory.get(10, 60)));
    }
}
