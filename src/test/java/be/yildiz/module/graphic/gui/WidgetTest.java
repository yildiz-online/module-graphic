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

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.client.gui.listener.MouseLeftClickListener;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.vector.Point2D;
import be.yildiz.module.graphic.gui.container.ContainerBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class WidgetTest {

    public final ExpectedException rule = ExpectedException.none();

    private Widget givenAWidget() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        return new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
    }

    /***/
    @Test
    public void testWidget() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = new ContainerBuilder(builder).withSize(new Size(50)).atPosition(new Position(30)).build();
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        Assert.assertEquals(c, w.getParent().get());
    }

    /***/
    @Test
    public void testSetLeftWidgetPositionRelative() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSetLeftWidgetPositionRelativefloat() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testAddMouseClickListener() {
        Widget w = givenAWidget();
        List<Point2D> list = Lists.newList();
        w.mouseLeftClick(12, 14);
        Assert.assertTrue(list.isEmpty());
        w.addMouseLeftClickListener(new MouseLeftClickListener() {

            @Override
            public void click() {
            }

            public void clickAt(int x, int y) {
                list.add(new Point2D(x, y));
            }
        });
        w.mouseLeftClick(12, 14);
        Assert.assertEquals(new Point2D(12, 14), list.get(0));
    }

    /***/
    @Test
    public void testAddKeyboardListener() {
        Widget w = givenAWidget();
        System.out.println(w);
        System.out.println(w.getParent().get());
        List<Character> list = Lists.newList();
        w.keyPressed('e');
        Assert.assertTrue(list.isEmpty());
        w.addKeyboardListener(list::add);
        w.keyPressed('e');
        Assert.assertTrue('e' == list.get(0));
    }

    /***/
    @Test
    public void testAddMouseMoveListener() {
        Widget w = givenAWidget();
        List<Point2D> list = Lists.newList();
        w.mouseMove(new Point2D(4, 89));
        Assert.assertTrue(list.isEmpty());
        w.addMouseMoveListener(list::add);
        w.mouseMove(new Point2D(4, 89));
        Assert.assertEquals(new Point2D(4, 89), list.get(0));
    }

    /***/
    @Test
    public void testAddMouseDragListener() {

    }

    @Test
    public void testShowHide() {
        Widget w = givenAWidget();
        w.show();
        Assert.assertTrue(w.isVisible());
        w.hide();
        Assert.assertFalse(w.isVisible());
        w.show();
        Assert.assertTrue(w.isVisible());
    }

    @Test
    public void testAddToLeft() {
        Widget w = givenAWidget();
        Assert.assertEquals(10, w.getLeft());
        w.addToLeft(12);
        Assert.assertEquals(22, w.getLeft());
    }

    @Test
    public void testAddToTop() {
        Widget w = givenAWidget();
        Assert.assertEquals(10, w.getTop());
        w.addToTop(2);
        Assert.assertEquals(12, w.getTop());
    }

    @Test
    public void testAddToPosition() {
        Widget w = givenAWidget();
        Assert.assertEquals(10, w.getTop());
        Assert.assertEquals(10, w.getLeft());
        w.addToPosition(45, -2);
        Assert.assertEquals(55, w.getLeft());
        Assert.assertEquals(8, w.getTop());
    }

    @Test
    public void testDelete() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testHighlightImpl() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetSizeImpl() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testShowImpl() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testHideImpl() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetPositionImpl() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testContains() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testUpdateSizeAfterZoom() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseLeftClick() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDoubleClick() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseRightClic() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseReleased() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDragLeft() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseDragRight() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseWheel() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testMouseMove() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testKeyPressed() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSpecialKeyPressed() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testHighlight() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testFocus() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetTop() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetLeftFloat() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetId() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetHeight() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetCoordinates() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSetAlignement() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetUnaffectedByZoom() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testIsAffectedByZoom() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetPosition() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetSize() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetHeight() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetWidth() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetLeft() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetRight() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetTop() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetBottom() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetHighlightable() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetIsFocusable() {
        Widget w = givenAWidget();
        Assert.assertFalse(w.isFocusable());
        w.setFocusable(true);
        Assert.assertTrue(w.isFocusable());
        w.setFocusable(false);
        Assert.assertFalse(w.isFocusable());
    }

    @Test
    public void testIsHighlightable() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetMouseOver() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testIsMouseOver() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetOpacity() {
        // Assert.fail("Not yet implemented");
    }

}
