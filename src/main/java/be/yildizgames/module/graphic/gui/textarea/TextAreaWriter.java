/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
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

import java.io.Writer;

/**
 * Helper class to extends a java.io.Writer stream and link it to a TextAreaGui.
 * All call to Writer.write will result in a TextAreaGui.addLine
 *
 * @author Grégory Van den Borre
 */
public final class TextAreaWriter extends Writer {

    /**
     * Associated TextAreaGui.
     */
    private final TextArea textArea;

    /**
     * Full constructor.
     *
     * @param text TextAreaGui to associate with this stream.
     */
    public TextAreaWriter(final TextArea text) {
        super();
        this.textArea = text;
    }

    @Override
    public void write(final char[] line, final int off, final int len) {
        this.textArea.addLine(String.valueOf(line));
    }

    @Override
    public void flush() {
        //Does nothing.
    }

    @Override
    public void close() {
        //Does nothing.
    }
}
