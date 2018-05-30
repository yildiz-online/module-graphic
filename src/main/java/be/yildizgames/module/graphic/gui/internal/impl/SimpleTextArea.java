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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.client.translation.Translation;
import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.BaseContainerChild;
import be.yildizgames.module.graphic.gui.element.AbstractIconElement;
import be.yildizgames.module.graphic.gui.element.AbstractTextElement;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.gui.textarea.TextArea;
import be.yildizgames.module.graphic.material.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi line text widget.
 *
 * @author Grégory Van den Borre
 */
final class SimpleTextArea extends BaseContainerChild implements TextArea {

    /**
     * End of line.
     */
    private static final String EOL = "\n";

    /**
     * Background for the text, if any.
     */
    private final AbstractIconElement background;

    /**
     * Text to print.
     */
    private final AbstractTextElement text;

    /**
     * Content of the text, one entry for a line.
     */
    private final List<String> lines = new ArrayList<>();

    /**
     * Text padding value.
     */
    private final int padding;

    /**
     * Full constructor.
     *
     * @param name            Unique name.
     * @param coordinates     Text area size and position.
     * @param backgroundImage Background.
     * @param textElement     Text Area text element.
     * @param padding         Text padding.
     * @param container       Container holding the TextAreaGui widget.
     */
    SimpleTextArea(final String name, final BaseCoordinate coordinates, final AbstractIconElement backgroundImage, final AbstractTextElement textElement, final int padding, final SimpleContainer container) {
        super(name, coordinates, container);
        assert backgroundImage != null : "i parameter is null.";
        assert textElement != null : "t parameter is null.";
        this.background = backgroundImage;
        this.text = textElement;
        this.showImpl();
        this.padding = padding;
        // force padding.
        this.setPosition(this.getLeft(), this.getTop());
    }

    @Override
    public void addLine(final TranslationKey line) {
        this.addLine(Translation.getInstance().translate(line));
    }

    @Override
    public void addLine(final TranslationKey.MultiKey line) {
        this.addLine(Translation.getInstance().translate(line));
    }

    @Override
    public void addLine(final String line) {
        // FIXME add the case when a word is > line size.
        if (line.contains(EOL)) {
            String[] linesRetrieved = line.split(EOL);
            for (String lineSplit : linesRetrieved) {
                this.addLine(lineSplit);
            }
        } else {
            if (line.length() > 1) {
                int startLine;
                final float length = this.text.getFont().computeTextWidth(line);
                if (length <= this.text.getLastLineWidth()) {
                    this.lines.add(line);
                } else {
                    final float space = this.text.getFont().getCharSize(' ');
                    final String[] words = line.split(" ");
                    final float lineMaxSize = this.getWidth();
                    int currentLineSize = this.padding * 2;
                    StringBuilder formattedLine = new StringBuilder();
                    for (final String word : words) {
                        if (!word.isEmpty()) {
                            final float wordWidth = this.text.getFont().computeTextWidth(word);
                            if (currentLineSize + wordWidth < lineMaxSize + space) {
                                formattedLine.append(word);
                                formattedLine.append(' ');
                            } else {
                                currentLineSize = this.padding * 2;
                                this.lines.add(formattedLine.toString());
                                formattedLine.setLength(0);
                                formattedLine.append(word);
                                formattedLine.append(' ');
                            }
                            currentLineSize += wordWidth + space;
                        }
                    }
                    // add the last line
                    this.lines.add(formattedLine.toString().replace(" ,", ","));

                }

                int size = Math.round((float) this.getHeight() / (float) this.text.getFontHeight());
                if (this.lines.size() > size) {
                    startLine = this.lines.size() - size;
                } else {
                    startLine = 0;
                }
                final StringBuilder formattedText = new StringBuilder();
                for (int i = startLine; i < this.lines.size(); i++) {
                    formattedText.append(this.lines.get(i));
                    formattedText.append('\n');
                }
                this.text.setText(formattedText.toString());
            }
        }
    }

    @Override
    public String getContent() {
        return this.text.getText();
    }

    @Override
    public void deleteText() {
        this.text.setText("");
        this.lines.clear();
    }

    /**
     * Delete the widget elements and remove it from the Registerer.
     */
    @Override
    public void delete() {
        this.lines.clear();
        this.text.delete();
        this.background.delete();
    }

    /**
     * Does nothing.
     *
     * @param over Unused.
     */
    @Override
    protected void highlightImpl(final boolean over) {
        //does nothing.
    }

    @Override
    protected void showImpl() {
        this.background.show();
        this.text.show();
    }

    @Override
    protected void hideImpl() {
        this.background.hide();
        this.text.hide();
    }

    @Override
    public void setFont(final Font font) {
        this.text.setFont(font);
    }

    @Override
    public void setMaterial(final Material newMaterial) {
        this.background.setMaterial(newMaterial);
    }

    @Override
    protected void setSizeImpl(final int width, final int height) {
        this.background.setSize(width, height);
        this.text.setSize(width, height);
    }

    @Override
    protected Element setPositionImpl(final int posX, final int posY) {
        this.background.setPosition(posX, posY);
        this.text.setPosition(posX + this.padding, posY + this.padding);
        return this;
    }

    @Override
    public SimpleTextArea setColor(final Color color) {
        this.text.setColor(color);
        return this;
    }

    @Override
    public void replaceText(final String text) {
        this.text.setText("");
        this.lines.clear();
        this.addLine(text);
    }

    @Override
    public void replaceText(final TranslationKey text) {
        this.replaceText(Translation.getInstance().translate(text));
    }

    @Override
    public void replaceText(final TranslationKey.MultiKey text) {
        this.replaceText(Translation.getInstance().translate(text));
    }


}
