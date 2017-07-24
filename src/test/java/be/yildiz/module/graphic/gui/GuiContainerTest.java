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

import be.yildiz.common.BaseCoordinate;
import be.yildiz.common.Coordinates;
import be.yildiz.common.Size;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.container.ContainerBuilder;
import be.yildiz.module.window.input.MouseMoveMockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public final class GuiContainerTest {

    private static final Coordinates CR = new Coordinates(new Size(10), BaseCoordinate.ZERO.left, BaseCoordinate.ZERO.top);

    /**
     * Scenario: <li>A: 2 widgets(W1,W2) in the same container, expected: W1,W2,W1,W2</li> <li>B: 1 more widget(W3) in the same container, expected: W3,W1,W2,W3,W1,W2,W3</li> <li>C: 1 more widget(W4)
     * in a child container, expected: W4,W1,W2,W3,W4,W1,W2,W3,W4</li> <li>D: 1 more widget(W5) in the parent container, expected: W1,W2,W3,W5(parent container has priority on child),W4,W1,W2,W3,W5,W4
     * </li> <li>E: 1 more widget(W6) in the parent container, not focusable, expected: same as D</li>
     */
    @Test
    public void functionnalTestGetNextFocusableElement() {
        GuiBuilder builder = new DummyGuiBuilder();
        ContainerBuilder cb = new ContainerBuilder(builder);
        GuiContainer c =cb.withCoordinates(new Coordinates(50, 60, 10, 20)).build();
        // A
        Widget w1 = new WidgetMock("w1", CR, c).setFocusable(true);
        Widget w2 = new WidgetMock("w2", CR, c).setFocusable(true);
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        // B
        Widget w3 = new WidgetMock("w3", CR, c).setFocusable(true);
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        // C
        GuiContainer c2 = new ContainerBuilder(builder)
                .withCoordinates(new Coordinates(50, 60, 10, 20))
                .withParent(c)
                .build();
        Widget w4 = new WidgetMock("w4", CR, c2).setFocusable(true);
        Assert.assertEquals(w4, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        // D
        Widget w5 = new WidgetMock("w5", CR, c).setFocusable(true);
        Assert.assertEquals(w5, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w5, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w5, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        // E
        new WidgetMock("w6", CR, c);
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w5, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
        Assert.assertEquals(w1, c.getNextFocusableElement());
        Assert.assertEquals(w2, c.getNextFocusableElement());
        Assert.assertEquals(w3, c.getNextFocusableElement());
        Assert.assertEquals(w5, c.getNextFocusableElement());
        Assert.assertEquals(w4, c.getNextFocusableElement());
    }

    @Test
    public void testGetNextFocusableElement() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withCoordinates(new Coordinates(50, 60, 10, 20)).build();
        Assert.assertNull(c.getNextFocusableElement());
        Widget w1 = new WidgetMock("w1", CR, c);
        Assert.assertNull(c.getNextFocusableElement());
        w1.setFocusable(true);
        Assert.assertEquals(w1, c.getNextFocusableElement());
    }

    @Test
    public void testGuiContainer() {
        GuiBuilder builder = new DummyGuiBuilder();
        Coordinates cr = new Coordinates(50, 60, 10, 20);
        GuiContainer c = new ContainerBuilder(builder).withName("test").withCoordinates(cr).build();
        Assert.assertEquals("test", c.getName());
        Assert.assertEquals(cr.left, c.getLeft());
        Assert.assertEquals(cr.top, c.getTop());
        Assert.assertEquals(cr.width, c.getWidth());
        Assert.assertEquals(cr.height, c.getHeight());
        Assert.assertEquals(cr, c.getCoordinates());
        Assert.assertEquals(Material.empty(), c.getMaterial());
    }

    @Test
    public void testContains() {
        GuiBuilder builder = new DummyGuiBuilder();
        final int cWidth = 50;
        final int cHeight = 60;
        final int cX = 10;
        final int cY = 20;

        final int c2Width = 20;
        final int c2Height = 20;
        final int c2X = 10;
        final int c2Y = 10;

        GuiContainer c = new ContainerBuilder(builder).withCoordinates(new Coordinates(cWidth, cHeight, cX, cY)).build();
        for (int x = cX; x <= cX + cWidth; x++) {
            for (int y = cY; y <= cY + cHeight; y++) {
                Assert.assertTrue(c.contains(MouseMoveMockFactory.get(x, y)));
            }
        }
        Assert.assertFalse(c.contains(MouseMoveMockFactory.get(cX - 1, cY + 2)));
        Assert.assertFalse(c.contains(MouseMoveMockFactory.get(cX + 2, cY - 1)));
        GuiContainer c2 = new ContainerBuilder(builder)
                .withCoordinates(new Coordinates(c2Width, c2Height, c2X, c2Y))
                .withParent(c)
                .build();

        for (int x = c2X + cX; x <= c2X + cX + c2Width; x++) {
            for (int y = c2Y + cY; y <= c2Y + cY + c2Width; y++) {
                Assert.assertTrue("test:" + x + "," + y, c2.contains(MouseMoveMockFactory.get(x, y)));
            }
        }
        Assert.assertFalse(c2.contains(MouseMoveMockFactory.get(19, 30)));
        Assert.assertFalse(c2.contains(MouseMoveMockFactory.get(20, 29)));
    }

    @Test
    public void testContainsVirtualHeight() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withSize(new Size(50)).build();
        Assert.assertTrue(c.contains(MouseMoveMockFactory.get(10, 10)));
        Assert.assertFalse(c.contains(MouseMoveMockFactory.get(10, 60)));
        c.setVirtualHeight(80);
        Assert.assertTrue(c.contains(MouseMoveMockFactory.get(10, 60)));
    }
}
