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
import be.yildizgames.common.geometry.Point2D;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.common.util.Checker;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.gui.ContainerChild;
import be.yildizgames.module.graphic.gui.OnMouseOverListener;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseAnimationGui;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBar;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarTimer;
import be.yildizgames.module.graphic.gui.textline.TimeTextLine;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.KeyboardListener;
import be.yildizgames.module.window.input.MouseDoubleLeftClickListener;
import be.yildizgames.module.window.input.MouseDragListener;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;
import be.yildizgames.module.window.input.MouseReleaseListener;
import be.yildizgames.module.window.input.MouseRightClickListener;
import be.yildizgames.module.window.input.MouseWheelListener;

import java.time.Duration;

/**
 * Update a ProgressBar over the time.
 *
 * @author Grégory Van den Borre
 */
final class SimpleProgressBarTimer extends EndFrameListener implements ProgressBarTimer {

    /**
     * Progress bar to update.
     */
    private final ProgressBar bar;

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
     * Full constructor, will update the progress bar and print the remaining time.
     *
     * @param bar  Associated progress bar.
     * @param text Widget where result will be printed.
     * @param time Total time to complete the bar.
     */
    SimpleProgressBarTimer(final ProgressBar bar, final TimeTextLine text, final Duration time) {
        super();
        assert bar != null;
        assert text != null;
        assert time != null;
        this.bar = bar;
        this.totalTime = time.toMillis();
        this.text = text;
    }

    @Override
    public final void neverStop() {
        this.neverStop = true;
    }

    /**
     * Update the progress bar and print the remaining time if the text widget has been passed in constructor.
     *
     * @param frameTime Time elapsed since the last call.
     * @return <code>true</code> while the total time has not been reached.
     */
    @Override
    public final boolean frameEnded(final long frameTime) {
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
    @Override
    public final SimpleProgressBarTimer setElapsedTime(final long time) {
        Checker.exceptionNotPositive(time);
        this.elapsedTime = time;
        return this;
    }

    @Override
    public final Element hide() {
        this.text.hide();
        this.bar.hide();
        return this;
    }

    @Override
    public final Element setPosition(Element other) {
        this.text.setPosition(other);
        this.bar.setPosition(other);
        return this;
    }

    @Override
    public Element show() {
        this.text.show();
        this.bar.show();
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop relative, int diff) {
        this.text.setTop(other, relative, diff);
        this.bar.setTop(other, relative, diff);
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop top, Relative relativeDiff) {
        this.text.setTop(other, top, relativeDiff);
        this.bar.setTop(other, top, relativeDiff);
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop relative) {
        this.text.setTop(other, relative);
        this.bar.setTop(other, relative);
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft relative) {
        this.text.setLeft(other, relative);
        this.bar.setLeft(other, relative);
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft relative, int diff) {
        this.text.setLeft(other, relative, diff);
        this.bar.setLeft(other, relative, diff);
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft left, Relative relativeDiff) {
        this.text.setLeft(other, left, relativeDiff);
        this.bar.setLeft(other, left, relativeDiff);
        return this;
    }

    @Override
    public int getLeft() {
        return this.bar.getLeft();
    }

    @Override
    public Element setLeft(int left) {
        this.text.setLeft(left);
        this.bar.setLeft(left);
        return this;
    }

    @Override
    public int getTop() {
        return this.bar.getTop();
    }

    @Override
    public Element setTop(int top) {
        this.text.setTop(top);
        this.bar.setTop(top);
        return this;
    }

    @Override
    public void addToPosition(int xValue, int yValue) {
        this.text.addToPosition(xValue,yValue);
        this.bar.addToPosition(xValue,yValue);
    }

    @Override
    public void addToLeft(int value) {
        this.text.addToLeft(value);
        this.bar.addToLeft(value);
    }

    @Override
    public void addToTop(int value) {
        this.text.addToTop(value);
        this.bar.addToTop(value);
    }

    @Override
    public Element setPosition(int left, int top) {
        this.text.setPosition(left, top);
        this.bar.setPosition(left, top);
        return this;
    }

    @Override
    public int getWidth() {
        return this.bar.getWidth();
    }

    @Override
    public void setWidth(int width) {
        this.bar.setWidth(width);
    }

    @Override
    public int getHeight() {
        return this.bar.getHeight();
    }

    @Override
    public void setHeight(int height) {
        this.bar.setHeight(height);
    }

    @Override
    public int getBottom() {
        return this.bar.getBottom();
    }

    @Override
    public void setSize(Size size) {
        this.bar.setSize(size);
    }

    @Override
    public BaseCoordinate getCoordinates() {
        return this.bar.getCoordinates();
    }

    @Override
    public void setCoordinates(BaseCoordinate coordinates) {
        this.text.setCoordinates(coordinates);
        this.bar.setCoordinates(coordinates);
    }

    @Override
    public boolean isVisible() {
        return this.bar.isVisible();
    }

    @Override
    public Element setVisible(final boolean visible) {
        this.bar.setVisible(visible);
        this.text.setVisible(visible);
        return this;
    }

    @Override
    public Element setPosition(Point2D position) {
        this.text.setPosition(position);
        this.bar.setPosition(position);
        return this;
    }

    @Override
    public void setSize(int newWidth, int newHeight) {
        this.bar.setSize(newWidth, newHeight);
    }

    @Override
    public int getRight() {
        return this.bar.getRight();
    }

    @Override
    public void setVirtualHeight(int height) {
        this.bar.setVirtualHeight(height);
    }

    @Override
    public void resetVirtualHeight() {
        this.bar.resetVirtualHeight();
    }

    @Override
    public ProgressBarTimer setValues(final long total, final long timeLeft) {
        Checker.exceptionNotPositive(total);
        Checker.exceptionNotPositive(timeLeft);
        this.totalTime = total;
        this.elapsedTime = total - timeLeft;
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void setProgress(float newProgressValue) {
        //does nothing
    }

    @Override
    public void reset() {
        //does nothing
    }

    @Override
    public void setContentMaterial(Material mat) {
        this.bar.setContentMaterial(mat);
    }

    @Override
    public void detachFromParent() {
        this.text.detachFromParent();
        this.bar.detachFromParent();
    }

    @Override
    public ContainerChild setLeftFromParent(PositionRelativeLeft p) {
        this.text.setLeftFromParent(p);
        this.bar.setLeftFromParent(p);
        return this;
    }

    @Override
    public ContainerChild setLeftFromParent(PositionRelativeLeft relative, int diff) {
        this.text.setLeftFromParent(relative, diff);
        this.bar.setLeftFromParent(relative, diff);
        return this;
    }

    @Override
    public ContainerChild setTopFromParent(PositionRelativeTop relative) {
        this.text.setTopFromParent(relative);
        this.bar.setTopFromParent(relative);
        return this;
    }

    @Override
    public ContainerChild setTopFromParent(PositionRelativeTop relative, int diff) {
        this.text.setTopFromParent(relative, diff);
        this.bar.setTopFromParent(relative, diff);
        return this;
    }

    @Override
    public Container getParent() {
        return this.bar.getParent();
    }

    @Override
    public void updateAddPositionValue(int left, int top) {
        this.text.updateAddPositionValue(left, top);
        this.bar.updateAddPositionValue(left, top);
    }

    @Override
    public void updateSizeAfterZoom(float zoomFactor) {
        //Does nothing.
    }

    @Override
    public void addMouseLeftClickListener(MouseLeftClickListener listener) {
        this.bar.addMouseLeftClickListener(listener);
    }

    @Override
    public void addMouseRightClickListener(MouseRightClickListener listener) {
        this.bar.addMouseRightClickListener(listener);
    }

    @Override
    public void addMouseDoubleLeftClickListener(MouseDoubleLeftClickListener listener) {
        this.bar.addMouseDoubleLeftClickListener(listener);
    }

    @Override
    public void addMouseReleaseListener(MouseReleaseListener listener) {
        this.bar.addMouseReleaseListener(listener);
    }

    @Override
    public void removeAllClickListeners() {
        this.bar.removeAllClickListeners();
    }

    @Override
    public void addKeyboardListener(KeyboardListener listener) {
        this.bar.addKeyboardListener(listener);
    }

    @Override
    public void addMouseMoveListener(MouseMoveListener listener) {
        this.bar.addMouseMoveListener(listener);
    }

    @Override
    public void addMouseWheelListener(MouseWheelListener listener) {
        this.bar.addMouseWheelListener(listener);
    }

    @Override
    public void addMouseDragListener(MouseDragListener listener) {
        this.bar.addMouseDragListener(listener);
    }

    @Override
    public void addOnMouseOverListener(OnMouseOverListener listener) {
        this.bar.addOnMouseOverListener(listener);
    }

    @Override
    public boolean contains(MousePosition position) {
        return this.bar.contains(position);
    }

    @Override
    public boolean contains(int x, int y) {
        return this.bar.contains(x, y);
    }

    @Override
    public void addEmptyZone(Rectangle zone) {
        this.bar.addEmptyZone(zone);
    }

    @Override
    public void align(Alignment alignment) {
        this.text.align(alignment);
        this.bar.align(alignment);
    }

    @Override
    public int getAbsoluteLeft() {
        return this.bar.getAbsoluteLeft();
    }

    @Override
    public int getAbsoluteTop() {
        return this.bar.getAbsoluteTop();
    }

    @Override
    public void registerAnimation(BaseAnimationGui anim) {
        this.bar.registerAnimation(anim);
    }

    @Override
    public void playAnimation(String animation) {
        this.bar.playAnimation(animation);
    }

    @Override
    public void stopAnimation(String name) {
        this.bar.stopAnimation(name);
    }

    @Override
    public void focus(boolean b) {
        this.bar.focus(b);
    }

    @Override
    public boolean isFocusable() {
        return this.bar.isFocusable();
    }

    @Override
    public void highlight(boolean b) {
        this.bar.highlight(b);
    }

    @Override
    public String getName() {
        return this.bar.getName();
    }
}
