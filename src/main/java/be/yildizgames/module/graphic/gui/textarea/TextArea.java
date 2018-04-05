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

package be.yildizgames.module.graphic.gui.textarea;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public interface TextArea extends Element {

    /**
     * Add a new line of text.
     *
     * @param line Text line to add to the current text.
     */
    void addLine(String line);

    void addLine(TranslationKey line);

    void addLine(TranslationKey.MultiKey line);

    void detachFromParent();

    /**
     * @return The printed text content.
     */
    String getContent();

    /**
     * Delete all the text contained in the text area.
     */
    void deleteText();

    /**
     * Set the text font.
     *
     * @param font Font to use.
     */
    void setFont(Font font);

    /**
     * Set the background material.
     *
     * @param newMaterial Material to set.
     */
    void setMaterial(Material newMaterial);

    /**
     * Set the text color.
     *
     * @param color New text color.
     * @return This object for chaining.
     */
    TextArea setColor(Color color);

    /**
     * @param text Replace former text by a new one.
     */
    void replaceText(String text);

    void replaceText(TranslationKey text);

    void replaceText(TranslationKey.MultiKey text);
}
