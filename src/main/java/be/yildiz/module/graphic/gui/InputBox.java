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

import be.yildiz.common.translation.Key;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;

/**
 * @author Grégory Van den Borre
 */
public interface InputBox extends ContainerElement {

    InputBox setDefaultMessage(String message);

    InputBox setDefaultMessage(Key message);

    /**
     * All characters written in the box will be hidden and replaced by the replacement character(default is '*').
     *
     * @return This object for chaining.
     */
    InputBox setPassword();

    /**
     * Set the box background material.
     *
     * @param material Material to use as background.
     * @return This object for chaining.
     */
    InputBox setMaterial(Material material);

    /**
     * Set the text position relative to the input box.
     *
     * @param left Left relative position.
     * @param top  Top relative position.
     * @return This object for chaining.
     */
    InputBox setTextAlignment(PositionRelativeLeft left, PositionRelativeTop top);

    /**
     * Set the font to use for the inner text and for the caption.
     *
     * @param font Font to use.
     */
    void setFont(Font font);

    /**
     * Change the password replacement char by another, default is '*'.
     *
     * @param replacement New char to use in password boxes.
     */
    void setPasswordReplacement(char replacement);

    /**
     * @return The current contained text.
     */
    String getText();

    /**
     * Set the text to print in the box.
     *
     * @param text Text to set.
     * @return This object for chaining.
     */
    InputBox setText(String text);

    InputBox setText(Key text);

    /**
     * Set the caption text.
     *
     * @param caption Text to set for the caption.
     */
    void setCaptionText(String caption);

    void setCaptionText(Key caption);

    /**
     * Delete all the current contained text.
     */
    void deleteText();

    /**
     * Align the text.
     *
     * @return This object for chaining.
     */
    InputBox setTextCenter();

    /**
     * Remove the last char of the current contained text.
     */
    void removeChar();
}
