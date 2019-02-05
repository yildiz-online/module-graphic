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

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Size;
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
public class WidgetTest {

    private BaseWidget givenAWidget() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Container c = builder
                .container()
                .withSize(new Size(50))
                .atPosition(new Position(30))
                .build();
        return new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
    }

    /***/
    @Test
    public void testWidget() {
        StandardGuiFactory builder = new DummyGuiFactory();
        Container c = builder
                .container()
                .withSize(10,10)
                .atPosition(10,10)
                .build();

        BaseWidget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        assertEquals(c, w.findParent().get());
    }

    /***/
    @Test
    public void testSetLeftWidgetPositionRelative() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSetLeftWidgetPositionRelativefloat() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testAddMouseClickListener() {
        BaseWidget w = givenAWidget();
        List<MousePosition> list = new ArrayList<>();
        w.mouseLeftClick(MouseMoveMockFactory.get(12, 14));
        assertTrue(list.isEmpty());
        w.addMouseLeftClickListener(new MouseLeftClickListener() {

            @Override
            public public void click() {
            }

            public public void clickAt(MousePosition p) {
                list.add(p);
            }
        });
        w.mouseLeftClick(MouseMoveMockFactory.get(12, 14));
        assertEquals(MouseMoveMockFactory.get(12, 14), list.get(0));
    }

    /***/
    @Test
    public void testAddKeyboardListener() {
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
    public void testAddMouseMoveListener() {
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
    public void testAddMouseDragListener() {

    }

    @Test
    public void testShowHide() {
        BaseWidget w = givenAWidget();
        w.show();
        assertTrue(w.isVisible());
        w.hide();
        assertFalse(w.isVisible());
        w.show();
        assertTrue(w.isVisible());
    }

    @Test
    public void testAddToLeft() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getLeft());
        w.addToLeft(12);
        assertEquals(22, w.getLeft());
    }

    @Test
    public void testAddToTop() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getTop());
        w.addToTop(2);
        assertEquals(12, w.getTop());
    }

    @Test
    public void testAddToPosition() {
        BaseWidget w = givenAWidget();
        assertEquals(10, w.getTop());
        assertEquals(10, w.getLeft());
        w.addToPosition(45, -2);
        assertEquals(55, w.getLeft());
        assertEquals(8, w.getTop());
    }

    @Test
    public void testDelete() {
        // fail("Not yet implemented");
    }

    @Test
    public void testHighlightImpl() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetSizeImpl() {
        // fail("Not yet implemented");
    }

    @Test
    public void testShowImpl() {
        // fail("Not yet implemented");
    }

    @Test
    public void testHideImpl() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetPositionImpl() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testContains() {
        // fail("Not yet implemented");
    }

    @Test
    public void testUpdateSizeAfterZoom() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseLeftClick() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDoubleClick() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseRightClic() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseReleased() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDragLeft() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDragRight() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseWheel() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseMove() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testKeyPressed() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSpecialKeyPressed() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testHighlight() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testFocus() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetTop() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetLeftFloat() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetId() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetHeight() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetCoordinates() {
        // fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSetAlignement() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetUnaffectedByZoom() {
        // fail("Not yet implemented");
    }

    @Test
    public void testIsAffectedByZoom() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetPosition() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetSize() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetHeight() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetWidth() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetLeft() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetRight() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetTop() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetBottom() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetHighlightable() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetIsFocusable() {
        BaseWidget w = givenAWidget();
        assertFalse(w.isFocusable());
        w.setFocusable(true);
        assertTrue(w.isFocusable());
        w.setFocusable(false);
        assertFalse(w.isFocusable());
    }

    @Test
    public void testIsHighlightable() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetMouseOver() {
        // fail("Not yet implemented");
    }

    @Test
    public void testIsMouseOver() {
        // fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetOpacity() {
        // fail("Not yet implemented");
    }

}
