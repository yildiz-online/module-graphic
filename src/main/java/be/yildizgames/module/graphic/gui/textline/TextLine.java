/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.graphic.gui.textline;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.common.util.Registerable;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.ContainerChild;

/**
 * @author Grégory Van den Borre
 */
public interface TextLine extends ContainerChild, Registerable {

    /**
     * Set the text to print.
     *
     * @param text Text to print, the toString method of the object will be used.
     * @return This object for chaining.
     */
    TextLine setText(String text);

    TextLine setText(TranslationKey key);

    TextLine setText(TranslationKey.MultiKey key);

    /**
     * Set the text to print.
     *
     * @param i Text to print, the int will be converted to a string message.
     * @return This object for chaining.
     */
    TextLine setText(int i);

    /**
     * Set a new color to this text.
     *
     * @param color Color to set.
     * @return This object for chaining.
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
     * @return This object for chaining.
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
