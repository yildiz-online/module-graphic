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

import be.yildizgames.common.frame.EndFrameListener;
import be.yildizgames.common.util.Checker;
import be.yildizgames.module.graphic.gui.textline.TimeTextLine;

import java.time.Duration;

/**
 * Update a ProgressBar over the time.
 *
 * @author Grégory Van den Borre
 */
public final class ProgressBarTimer extends EndFrameListener {

    /**
     * Progress bar to update.
     */
    private final BaseProgressBar bar;

    /**
     * Text line to print the current progress.
     */
    private final TimeTextLine text;

    /**
     * Total time to use from start to complete.
     */
    private long totalTime;
    /**
     * Currently elapsed time.
     */
    private long elapsedTime;
    private boolean neverStop = false;


    /**
     * Full constructor, will only update the progress bar and wont print anything.
     *
     * @param bar  Associated bar.
     * @param time Total time to complete the bar.
     */
    public ProgressBarTimer(final BaseProgressBar bar, final Duration time) {
        this(bar, new EmptyTimeTextLine(), time);
    }

    /**
     * Full constructor, will update the progress bar and print the remaining time.
     *
     * @param bar  Associated progress bar.
     * @param text Widget where result will be printed.
     * @param time Total time to complete the bar.
     */
    public ProgressBarTimer(final BaseProgressBar bar, final TimeTextLine text, final Duration time) {
        super();
        assert bar != null;
        assert text != null;
        assert time != null;
        this.bar = bar;
        this.totalTime = time.toMillis();
        this.text = text;
    }

    public void neverStop() {
        this.neverStop = true;
    }

    /**
     * Update the progress bar and print the remaining time if the text widget has been passed in constructor.
     *
     * @param frameTime Time elapsed since the last call.
     * @return <code>true</code> while the total time has not been reached.
     */
    @Override
    public boolean frameEnded(final long frameTime) {
        this.elapsedTime += frameTime;
        if (this.bar.isVisible()) {
            this.bar.setProgress((float) this.elapsedTime / (float) this.totalTime * 100.0f);
            this.text.display(this.totalTime - this.elapsedTime + 1000);
        }
        return this.neverStop || this.elapsedTime < this.totalTime;
    }

    /**
     * Set the current elapsed time.
     *
     * @param time Time elapsed.
     * @return This object.
     */
    public ProgressBarTimer setElapsedTime(final long time) {
        Checker.exceptionNotPositive(time);
        this.elapsedTime = time;
        return this;
    }

    public void setVisible(final boolean visible) {
        this.bar.setVisible(visible);
        this.text.setVisible(visible);
    }

    public void setValues(final long total, final long timeLeft) {
        Checker.exceptionNotPositive(total);
        Checker.exceptionNotPositive(timeLeft);
        this.totalTime = total;
        this.elapsedTime = total - timeLeft;
    }

}
