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

import be.yildiz.common.Color;
import be.yildiz.common.Coordinates;
import be.yildiz.common.exeption.UnhandledSwitchCaseException;
import be.yildiz.module.graphic.Font;

import java.util.Optional;

/**
 * A single line of text.
 *
 * @author Grégory Van den Borre
 */
public final class GuiTextLine extends ContainerChild implements TextLine {

    /**
     * Text element to use.
     */
    private final AbstractTextElement text;

    /**
     * Text alignment.
     */
    private TextPosition textPosition;

    /**
     * Build a new GuiTextLine, this widget allow to build a single line text
     * element(multi line is possible through the use of \n character but no
     * automatic end of line mechanism is provided, see TextAreaGui widget for
     * this). A GuiTextLine contains method always return false.
     *
     * @param name        Text line name, must be unique.
     * @param textElement TextElement to use to print the text.
     * @param container   Container holding the text line.
     */
    GuiTextLine(final String name, final AbstractTextElement textElement, final GuiContainer container) {
        super(name, new Coordinates(textElement.getTextWidth(), textElement.getTextHeight(), textElement.getLeft(), textElement.getTop()), Optional
                .of(container));
        this.text = textElement;
        this.textPosition = TextPosition.LEFT;
        this.showImpl();
        this.forceContainsFalse();
    }

    @Override
    public GuiTextLine setText(final String message) {
        if (this.text.setText(message)) {
            this.setWidth(this.text.getWidth());
            this.setTextPosition(this.textPosition);
        }
        return this;
    }


    @Override
    public GuiTextLine setText(final int message) {
        return this.setText(String.valueOf(message));
    }

    @Override
    public void setFont(final Font font) {
        this.text.setFont(font);
        this.setText(this.getContent());
    }

    @Override
    public void delete() {
        this.text.delete();
    }

    /**
     * Does nothing, not highlightable.
     *
     * @param over Unused.
     */
    @Override
    protected void highlightImpl(final boolean over) {
        //Does nothing.
    }

    @Override
    protected void setSizeImpl(final int width, final int height) {
    }

    @Override
    protected void showImpl() {
        this.text.show();
    }

    @Override
    protected void hideImpl() {
        this.text.hide();
    }

    @Override
    protected Element setPositionImpl(final int left, final int top) {
        this.text.setPosition(left, top);
        return this;
    }

    @Override
    public GuiTextLine setColor(final Color color) {
        this.text.setColor(color);
        return this;
    }

    @Override
    public GuiTextLine setTextPosition(final TextPosition textPosition) {
        boolean updated = this.textPosition != textPosition;
        this.textPosition = textPosition;
        if (textPosition == TextPosition.CENTER) {
            int textWidth = (int) this.text.getLastLineWidth();
            int parentWidth = this.getParent().get().getWidth();
            int pos = (parentWidth >> 1) - (textWidth >> 1);
            this.setLeft(pos);
        } else if (textPosition == TextPosition.RIGHT) {
            int textWitdh = (int) this.text.getLastLineWidth();
            int parentWidth = this.getParent().get().getWidth();
            this.setLeft(parentWidth - textWitdh);
        } else if (textPosition == TextPosition.LEFT) {
            if (updated) {
                this.setLeft(this.getLeft());
            }
        } else {
            throw new UnhandledSwitchCaseException(textPosition);
        }
        return this;
    }

    @Override
    public String getContent() {
        return this.text.getText();
    }
}