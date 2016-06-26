//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic.gui;

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.client.gui.listener.MouseLeftClickListener;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.vector.Point2D;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class WidgetTest {

    public final ExpectedException rule = ExpectedException.none();

    /***/
    @Test
    public void testWidget() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
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
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
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
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        List<Character> list = Lists.newList();
        w.keyPressed('e');
        Assert.assertTrue(list.isEmpty());
        w.addKeyboardListener(ch -> list.add(ch));
        w.keyPressed('e');
        Assert.assertTrue('e' == list.get(0));
    }

    /***/
    @Test
    public void testAddMouseMoveListener() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        List<Point2D> list = Lists.newList();
        w.mouseMove(new Point2D(4, 89));
        Assert.assertTrue(list.isEmpty());
        w.addMouseMoveListener(p -> list.add(p));
        w.mouseMove(new Point2D(4, 89));
        Assert.assertEquals(new Point2D(4, 89), list.get(0));
    }

    /***/
    @Test
    public void testAddMouseDragListener() {

    }

    @Test
    public void testShowHide() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        w.show();
        Assert.assertTrue(w.isVisible());
        w.hide();
        Assert.assertFalse(w.isVisible());
        w.show();
        Assert.assertTrue(w.isVisible());
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#addToLeft(float)}.
     */
    @Test
    public void testAddToLeft() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        Assert.assertEquals(10, w.getLeft());
        w.addToLeft(12);
        Assert.assertEquals(22, w.getLeft());
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#addToTop(float)}.
     */
    @Test
    public void testAddToTop() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        Assert.assertEquals(10, w.getTop());
        w.addToTop(2);
        Assert.assertEquals(12, w.getTop());
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#addToPosition(float, float)}.
     */
    @Test
    public void testAddToPosition() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        Assert.assertEquals(10, w.getTop());
        Assert.assertEquals(10, w.getLeft());
        w.addToPosition(45, -2);
        Assert.assertEquals(55, w.getLeft());
        Assert.assertEquals(8, w.getTop());
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#delete()}.
     */
    @Test
    public void testDelete() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#highlightImpl(boolean)}.
     */
    @Test
    public void testHighlightImpl() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setSizeImpl(float, float)}.
     */
    @Test
    public void testSetSizeImpl() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#showImpl()}.
     */
    @Test
    public void testShowImpl() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#hideImpl()}.
     */
    @Test
    public void testHideImpl() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setPositionImpl(float, float)} .
     */
    @Test
    public void testSetPositionImpl() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testContains() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#updateSizeAfterZoom(float)}.
     */
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

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setTop(float)} .
     */
    @Test
    public void testSetTop() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setLeft(float)}.
     */
    @Test
    public void testSetLeftFloat() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getId()}.
     */
    @Test
    public void testGetId() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setHeight(float)}.
     */
    @Test
    public void testSetHeight() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getCoordinates()}.
     */
    @Test
    public void testGetCoordinates() {
        // Assert.fail("Not yet implemented");
    }

    /***/
    @Test
    public void testSetAlignement() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setUnaffectedByZoom()}.
     */
    @Test
    public void testSetUnaffectedByZoom() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#isAffectedByZoom()}.
     */
    @Test
    public void testIsAffectedByZoom() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setPosition(float, float)}.
     */
    @Test
    public void testSetPosition() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setSize(float, float)}.
     */
    @Test
    public void testSetSize() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getHeight()}.
     */
    @Test
    public void testGetHeight() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getWidth()}.
     */
    @Test
    public void testGetWidth() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getLeft()}.
     */
    @Test
    public void testGetLeft() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getRight()}.
     */
    @Test
    public void testGetRight() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getTop()}.
     */
    @Test
    public void testGetTop() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#getBottom()}.
     */
    @Test
    public void testGetBottom() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setHighlightable()}.
     */
    @Test
    public void testSetHighlightable() {
        // Assert.fail("Not yet implemented");
    }

    @Test
    public void testSetIsFocusable() {
        GuiBuilder builder = new DummyGuiBuilder();
        GuiContainer c = builder.buildOverlayContainer(new Coordinates(new Size(50), new Position(30)));
        Widget w = new WidgetMock(new Coordinates(new Size(10), new Position(10)), c);
        Assert.assertFalse(w.isFocusable());
        w.setFocusable(true);
        Assert.assertTrue(w.isFocusable());
        w.setFocusable(false);
        Assert.assertFalse(w.isFocusable());
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#isHighlightable()}.
     */
    @Test
    public void testIsHighlightable() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setMouseOver(boolean)}.
     */
    @Test
    public void testSetMouseOver() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#isMouseOver()} .
     */
    @Test
    public void testIsMouseOver() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#toString()}.
     */
    @Test
    public void testToString() {
        // Assert.fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.yildiz.client.graphic.gui.Widget#setOpacity(float)}.
     */
    @Test
    public void testSetOpacity() {
        // Assert.fail("Not yet implemented");
    }

}
