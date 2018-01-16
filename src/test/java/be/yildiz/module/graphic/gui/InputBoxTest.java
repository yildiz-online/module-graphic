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

import be.yildiz.module.color.Color;
import be.yildiz.module.coordinate.Coordinates;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.dummy.DummyFont;
import be.yildizgames.common.util.StringUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Grégory Van den Borre
 */
class InputBoxTest {

    private static GuiBuilder builder = new DummyGuiBuilder();

    private static Font f = new DummyFont("f", 5, Color.BLACK);

    private static GuiContainer parent = builder.buildContainerElement("parent", new Coordinates(100,10,0,0), Material.empty());


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
        InputBoxGui box = givenAnInputBox();
        box.addChar(65);
        assertEquals("A", box.getText());
        builder.delete(box);
    }

    @Test
    void testPressKey() {
        InputBoxGui box = givenAnInputBox();
        box.show();
        box.keyPressed('a');
        assertEquals("a", box.getText());
        builder.delete(box);
    }

    @Test
    void testPressDeleteKey() {
        InputBoxGui box = givenAnInputBox();
        box.show();
        box.keyPressed('b');
        box.keyPressed('c');
        box.deleteKeyPressed();
        assertEquals("b", box.getText());
        builder.delete(box);
    }

    @Test
    void testAddCharAboveLimit256() {
        InputBoxGui box = givenAnInputBox();
        box.addChar(257);
        assertEquals("", box.getText());
        builder.delete(box);
    }

    private static InputBoxGui givenAnInputBox() {
        Material m = Material.empty();
        return builder
                .buildInputBox(StringUtil.buildRandomString("ib"),
                        Coordinates.ZERO, f, m, m, m, parent);
    }
}
