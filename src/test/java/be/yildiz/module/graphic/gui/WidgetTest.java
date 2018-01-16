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

import be.yildiz.module.coordinate.Coordinates;
import be.yildiz.module.coordinate.Position;
import be.yildiz.module.coordinate.Size;
import be.yildiz.module.graphic.gui.container.ContainerBuilder;
import be.yildiz.module.window.input.MouseLeftClickListener;
import be.yildiz.module.window.input.MouseMoveMockFactory;
import be.yildiz.module.window.input.MousePosition;
import be.yildizgames.common.collection.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grégory Van den Borre
 */
class WidgetTest {

    private Widget givenAWidget() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        return new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
    }

    /***/
    @Test
    void testWidget() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        assertEquals(c, w.getParent().get());
    }

    /***/
    @Test
    void testSetLeftWidgetPositionRelative() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testSetLeftWidgetPositionRelativefloat() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testAddMouseClickListener() {
        Widget w = givenAWidget();
        List<MousePosition> list = Lists.newList();
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

    /***/
    @Test
    void testAddKeyboardListener() {
        Widget w = givenAWidget();
        List<Character> list = Lists.newList();
        w.keyPressed('e');
        assertTrue(list.isEmpty());
        w.addKeyboardListener(list::add);
        w.keyPressed('e');
        assertTrue('e' == list.get(0));
    }

    /***/
    @Test
    void testAddMouseMoveListener() {
        Widget w = givenAWidget();
        List<MousePosition> list = Lists.newList();
        w.mouseMove(MouseMoveMockFactory.get(4, 89));
        assertTrue(list.isEmpty());
        w.addMouseMoveListener(list::add);
        w.mouseMove(MouseMoveMockFactory.get(4, 89));
        assertEquals(MouseMoveMockFactory.get(4, 89), list.get(0));
    }

    /***/
    @Test
    void testAddMouseDragListener() {

    }

    @Test
    void testShowHide() {
        Widget w = givenAWidget();
        w.show();
        assertTrue(w.isVisible());
        w.hide();
        assertFalse(w.isVisible());
        w.show();
        assertTrue(w.isVisible());
    }

    @Test
    void testAddToLeft() {
        Widget w = givenAWidget();
        assertEquals(10, w.getLeft());
        w.addToLeft(12);
        assertEquals(22, w.getLeft());
    }

    @Test
    void testAddToTop() {
        Widget w = givenAWidget();
        assertEquals(10, w.getTop());
        w.addToTop(2);
        assertEquals(12, w.getTop());
    }

    @Test
    void testAddToPosition() {
        Widget w = givenAWidget();
        assertEquals(10, w.getTop());
        assertEquals(10, w.getLeft());
        w.addToPosition(45, -2);
        assertEquals(55, w.getLeft());
        assertEquals(8, w.getTop());
    }

    @Test
    void testDelete() {
        // fail("Not yet implemented");
    }

    @Test
    void testHighlightImpl() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetSizeImpl() {
        // fail("Not yet implemented");
    }

    @Test
    void testShowImpl() {
        // fail("Not yet implemented");
    }

    @Test
    void testHideImpl() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetPositionImpl() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testContains() {
        // fail("Not yet implemented");
    }

    @Test
    void testUpdateSizeAfterZoom() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseLeftClick() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseDoubleClick() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseRightClic() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseReleased() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseDragLeft() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseDragRight() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseWheel() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testMouseMove() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testKeyPressed() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testSpecialKeyPressed() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testHighlight() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testFocus() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetTop() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetLeftFloat() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetId() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetHeight() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetCoordinates() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    void testSetAlignement() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetUnaffectedByZoom() {
        // fail("Not yet implemented");
    }

    @Test
    void testIsAffectedByZoom() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetPosition() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetSize() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetHeight() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetWidth() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetLeft() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetRight() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetTop() {
        // fail("Not yet implemented");
    }

    @Test
    void testGetBottom() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetHighlightable() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetIsFocusable() {
        Widget w = givenAWidget();
        assertFalse(w.isFocusable());
        w.setFocusable(true);
        assertTrue(w.isFocusable());
        w.setFocusable(false);
        assertFalse(w.isFocusable());
    }

    @Test
    void testIsHighlightable() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetMouseOver() {
        // fail("Not yet implemented");
    }

    @Test
    void testIsMouseOver() {
        // fail("Not yet implemented");
    }

    @Test
    void testToString() {
        // fail("Not yet implemented");
    }

    @Test
    void testSetOpacity() {
        // fail("Not yet implemented");
    }

}
