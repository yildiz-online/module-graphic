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

package be.yildiz.module.graphic.gui.textline;

import be.yildiz.common.util.ManualElapsedTimeComputer;

/**
 * @author Grégory Van den Borre
 */
public class TextAppearing extends TextAnimation {

    private String textToDisplay;
    private final ManualElapsedTimeComputer timer;

    private int currentPosition = 0;
    private boolean completed = true;

    public TextAppearing(String name, long delay) {
        super(name);
        this.timer = new ManualElapsedTimeComputer(delay);
    }

    @Override
    public void update(long time) {
        if (!completed && this.timer.isTimeElapsed(time)) {
            currentPosition++;
            this.text.setText(this.textToDisplay.substring(0, currentPosition));
            this.completed = currentPosition == textToDisplay.length() - 1;
        }
    }

    @Override
    public void start() {
        this.textToDisplay = this.text.getContent();
        this.text.setText("");
        this.completed = false;
    }
}
