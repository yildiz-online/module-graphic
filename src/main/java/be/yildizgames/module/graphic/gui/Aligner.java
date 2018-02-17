/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

/**
 * @author Grégory Van den Borre
 */
public class Aligner {

    private final int baseLeft;
    private final int baseTop;
    private final int left;
    private final int top;

    private Aligner(final int baseLeft, final int baseTop, final int left, final int top) {
        super();
        this.baseLeft = baseLeft;
        this.baseTop = baseTop;
        this.left = left;
        this.top = top;
    }

    public static Aligner leftAligner(final int baseLeft, final int baseTop, final int left) {
        return new Aligner(baseLeft, baseTop, left, 0);
    }

    public static Aligner topAligner(final int baseLeft, final int baseTop, final int top) {
        return new Aligner(baseLeft, baseTop, 0, top);
    }

    public void align(final Widget... widgets) {
        int leftValue = this.baseLeft;
        int topValue = this.baseTop;
        for (Widget w : widgets) {
            w.setLeft(leftValue);
            w.setTop(topValue);
            leftValue += this.left;
            topValue += this.top;
        }
    }

}
