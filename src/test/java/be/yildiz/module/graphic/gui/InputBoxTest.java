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
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Material;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Grégory Van den Borre
 */
public class InputBoxTest {

    private GuiBuilder builder = new DummyGuiBuilder();

    private GuiContainer parent = builder.buildContainerElement("parent", new Coordinates(100,10,0,0), Material.empty());

    @Test
    public void testGetText() {
        InputBox box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        Assert.assertEquals("", box.getText());
        builder.delete(box);
    }

    @Test
    public void testSetText() {
        InputBox box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.setText("abc");
        Assert.assertEquals("abc", box.getText());
        builder.delete(box);
    }

    @Test
    public void testRemoveChar() {
        InputBox box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.setText("abc");
        box.removeChar();
        Assert.assertEquals("ab", box.getText());
        builder.delete(box);
    }

    @Test
    public void testRemoveCharNoText() {
        InputBox box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.removeChar();
        Assert.assertEquals("", box.getText());
        builder.delete(box);
    }

    @Test
    public void testAddChar() {
        InputBoxGui box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.addChar(65);
        Assert.assertEquals("A", box.getText());
        builder.delete(box);
    }

    @Test
    public void testPressKey() {
        InputBoxGui box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.show();
        box.keyPressed('a');
        Assert.assertEquals("a", box.getText());
        builder.delete(box);
    }

    @Test
    public void testPressDeleteKey() {
        InputBoxGui box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.show();
        box.keyPressed('b');
        box.keyPressed('c');
        box.deleteKeyPressed();
        Assert.assertEquals("b", box.getText());
        builder.delete(box);
    }

    @Test
    public void testAddCharAboveLimit256() {
        InputBoxGui box = builder.buildInputBox(StringUtil.buildRandomString("ib"), parent);
        box.addChar(257);
        Assert.assertEquals("", box.getText());
        builder.delete(box);
    }
}
