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

import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.dummy.DummyFont;
import be.yildizgames.module.graphic.gui.DummyGuiFactory;
import be.yildizgames.module.graphic.gui.inputbox.InputBox;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.Key;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Grégory Van den Borre
 */
class InputBoxTest {

    private static final StandardGuiFactory builder = new DummyGuiFactory();

    private static Font f = new DummyFont("f", 5, Color.BLACK);

    private static final SimpleContainer parent = builder.buildOverlayContainer("parent", Material.empty(), FullCoordinates.full(100, 10, 0, 0));

    @Test
    void testGetText() {
        InputBox box = givenAnInputBox();
        assertEquals("", box.getText());
        builder.delete(box);
    }

    @Test
    void testSetText() {
        InputBox box = givenAnInputBox();
        box.setText("abc");
        assertEquals("abc", box.getText());
        builder.delete(box);
    }

    @Test
    void testRemoveChar() {
        InputBox box = givenAnInputBox();
        box.setText("abc");
        box.removeChar();
        assertEquals("ab", box.getText());
        builder.delete(box);
    }

    @Test
    void testRemoveCharNoText() {
        InputBox box = givenAnInputBox();
        box.removeChar();
        assertEquals("", box.getText());
        builder.delete(box);
    }

    @Test
    void testAddChar() {
        SimpleInputBox box = givenAnInputBox();
        box.addChar(65);
        assertEquals("A", box.getText());
        builder.delete(box);
    }

    @Test
    void testPressKey() {
        SimpleInputBox box = givenAnInputBox();
        box.show();
        box.keyPressed('a');
        assertEquals("a", box.getText());
        builder.delete(box);
    }

    @Test
    void testPressDeleteKey() {
        SimpleInputBox box = givenAnInputBox();
        box.show();
        box.keyPressed('b');
        box.keyPressed('c');
        box.specialKeyPressed(Key.DELETE);
        assertEquals("b", box.getText());
        builder.delete(box);
    }

    @Test
    void testAddCharAboveLimit256() {
        SimpleInputBox box = givenAnInputBox();
        box.addChar(257);
        assertEquals("", box.getText());
        builder.delete(box);
    }

    private static SimpleInputBox givenAnInputBox() {
        Material m = Material.empty();
        return builder
                .buildInputBox(StringUtil.buildRandomString("ib"),
                        FullCoordinates.ZERO, f, m, m, m, parent);
    }
}
