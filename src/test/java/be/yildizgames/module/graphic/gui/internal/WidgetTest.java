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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.gui.DummyGuiFactory;
import be.yildizgames.module.graphic.gui.WidgetMock;
import be.yildizgames.module.graphic.gui.container.ContainerBuilder;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleGuiFactory;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveMockFactory;
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
        SimpleGuiFactory builder = new DummyGuiFactory();
        SimpleContainer c = (SimpleContainer)new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        return new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
    }

    /***/
    @Test
    void testWidget() {
        SimpleGuiFactory builder = new DummyGuiFactory();
        SimpleContainer c = (SimpleContainer)new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        BaseWidget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        assertEquals(c, w.findParent().get());
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

    /***/
    @Test
    void testAddKeyboardListener() {
        BaseWidget w = givenAWidget();
        List<Character> list = new ArrayList<>();
        w.keyPressed('e');
        assertTrue(list.isEmpty());
        w.addKeyboardListener(list::add);
        w.keyPressed('e');
        assertTrue('e' == list.get(0));
    }

    /***/
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

    /***/
    @Test
    void testAddMouseDragListener() {

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
        BaseWidget w = givenAWidget();
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
