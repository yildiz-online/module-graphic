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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.element.AbstractTextElement;
import be.yildizgames.module.graphic.gui.internal.Element;

/**
 * Empty text element.
 *
 * @author Grégory Van den Borre
 */
public final class DummyTextElement extends AbstractTextElement {

    /**
     * Full constructor.
     *
     * @param coordinates Object coordinates.
     * @param textFont    Font to use.
     */
    public DummyTextElement(final Coordinates coordinates, final Font textFont) {
        super(coordinates, textFont);
    }

    @Override
    protected void setTextImpl(final String newText) {
    }

    @Override
    protected void setFontImpl(final Font newFont) {
    }

    @Override
    public void setColor(final Color color) {
    }

    @Override
    public void delete() {
        removeFromRegisterer();
    }

    @Override
    protected void showImpl() {
    }

    @Override
    protected void hideImpl() {
    }

    @Override
    protected void setSizeImpl(final int newWidth, final int newHeight) {
    }

    @Override
    protected Element setPositionImpl(final int newLeft, final int newTop) {
        return this;
    }
}
