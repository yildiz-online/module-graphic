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

import be.yildiz.common.Color;
import be.yildiz.module.graphic.Font;

/**
 * @author Grégory Van den Borre
 */
public interface TextLine extends ContainerElement {

    /**
     * Set the text to print.
     *
     * @param text Text to print, the toString method of the object will be used.
     */
    TextLine setText(String text);

    /**
     * Set the text to print.
     *
     * @param i Text to print, the int will be converted to a string message.
     */
    TextLine setText(int i);

    void delete();

    /**
     * Set a new color to this text.
     *
     * @param color Color to set.
     */
    TextLine setColor(Color color);

    /**
     * Set the text font.
     *
     * @param font New font to use.
     */
    void setFont(Font font);

    /**
     * Set the text alignment.
     *
     * @param textPosition Text alignment.
     */
    TextLine setTextPosition(TextPosition textPosition);

    /**
     * @return The text content.
     */
    String getContent();

    /**
     * Possible text position.
     *
     * @author Van den Borre Grégory
     */
    enum TextPosition {

        /**
         * Text will be aligned left.
         */
        LEFT,

        /**
         * Text will be centered.
         */
        CENTER,

        /**
         * Text will be aligned right.
         */
        RIGHT
    }
}
