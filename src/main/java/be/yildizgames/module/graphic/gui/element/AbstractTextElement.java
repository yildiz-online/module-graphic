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

package be.yildizgames.module.graphic.gui.element;

import be.yildizgames.common.util.Registerer;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.internal.BaseElement;

/**
 * Base element to print text.
 *
 * @author Grégory Van Den Borre
 */
public abstract class AbstractTextElement extends BaseElement {

    /**
     * The registerer will force the name to be unique, or throw an exception if it is not.
     */
    private static final Registerer<AbstractTextElement> REGISTERER = Registerer.newRegisterer();

    /**
     * Font used in the text.
     */
    private Font font;

    /**
     * Text to print.
     */
    private String currentText = "";

    private String croppedText = "";

    /**
     * Full constructor.
     *
     * @param coordinates Text position and size.
     * @param textFont    Font to use to print the text.
     */
    protected AbstractTextElement(final BaseCoordinate coordinates, final Font textFont) {
        super("text" + System.nanoTime(), coordinates);
        assert textFont != null;
        this.font = textFont;
        REGISTERER.register(this);
    }

    public static AbstractTextElement getByName(final String name) {
        return AbstractTextElement.REGISTERER.get(name);
    }

    /**
     * Compute the width in pixel of the printed text.
     *
     * @return Current text width in pixel.
     */
    public final int getTextWidth() {
        return this.font.computeTextWidth(this.currentText);
    }

    /**
     * Compute the last text width, if the text is multi line.
     *
     * @return Current text last line width in pixel.
     */
    public final float getLastLineWidth() {
        if (this.currentText.trim().isEmpty()) {
            return 0;
        }
        String[] lines = this.currentText.split("\n");
        String lastLine = lines[lines.length - 1];
        return this.font.computeTextWidth(lastLine);
    }

    /**
     * Remove the element from registered elements.
     */
    protected final void removeFromRegisterer() {
        REGISTERER.remove(this);
    }

    /**
     * @return Current text height in pixel.
     */
    public final int getTextHeight() {
        return this.font.size;
    }

    /**
     * @return The text.
     */
    public final String getText() {
        return this.currentText;
    }

    /**
     * Overload the setText method to avoid the toString call.
     *
     * @param newText New text to print.
     * @return <code>true</code> if the text was updated.
     */
    public final boolean setText(final String newText) {
        assert newText != null;
        if (this.currentText.equals(newText)) {
            return false;
        }
        this.currentText = newText;
        this.croppedText = this.currentText;
        if (this.getTextWidth() < this.getWidth()) {
            this.croppedText = this.font.crop(this.croppedText, this.getWidth());
        }
        this.setTextImpl(this.croppedText);
        this.setWidth(this.getTextWidth());
        return true;
    }

    /**
     * @return The font height.
     */
    public final int getFontHeight() {
        return this.font.size;
    }

    /**
     * @return The text font.
     */
    public final Font getFont() {
        return this.font;
    }

    /**
     * @param newFont New font.
     */
    public final void setFont(final Font newFont) {
        this.font = newFont;
        if (!"".equals(this.font.getName())) {
            this.setHeight(font.size);
            this.setFontImpl(this.font);
            this.setColor(font.color);
        }
    }

    /**
     * Internal method, do not use externally. Update the text in graphic engine implementation.
     *
     * @param newText New text to print.
     */
    protected abstract void setTextImpl(final String newText);

    /**
     * Internal method, do not use externally. Update the font in graphic engine implementation.
     *
     * @param newFont New font to use.
     */
    protected abstract void setFontImpl(final Font newFont);

    /**
     * Set the text color.
     *
     * @param color Color to set to this text.
     */
    public abstract void setColor(Color color);
}
