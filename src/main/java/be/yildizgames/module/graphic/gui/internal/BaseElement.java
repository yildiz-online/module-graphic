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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.common.geometry.Point2D;
import be.yildizgames.common.util.BaseRegisterable;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;

/**
 * Base for GUI elements.
 *
 * @author Grégory Van Den Borre
 */
public abstract class BaseElement extends BaseRegisterable implements Element {

    /**
     * Value for maximum opacity.
     */
    public static final int MAX_OPACITY = 100;
    protected int virtualHeight;
    /**
     * <code>true</code> if the element is currently visible.
     */
    private boolean visible = false;
    /**
     * Element width in pixel.
     */
    private int width;
    /**
     * Element height in pixel.
     */
    private int height;
    /**
     * Element x position from the Alignment left border, in pixel.
     */
    private int left;
    /**
     * Element y position from the Alignment top border, in pixel.
     */
    private int top;
    /**
     * Element opacity value, must be between 0 and 100.
     */
    private int opacity = BaseElement.MAX_OPACITY;

    /**
     * If a relative position has been set, it is stored to recompute correctly when size is modified.
     */
    private PositionRelativeLeft leftRelative = PositionRelativeLeft.LEFT;

    /**
     * If a relative position has been set, it is stored to recompute correctly when size is modified.
     */
    private PositionRelativeTop topRelative = PositionRelativeTop.TOP;

    /**
     * Object the position relative must be computed from.
     */
    private Element otherRelative;

    /**
     * Left position from the other relative object.
     */
    private int otherRelativeLeftOffset;

    /**
     * Top position from the other relative object.
     */
    private int otherRelativeTopOffset;

    /**
     * Full constructor.
     *
     * @param name        Element name, must be unique.
     * @param coordinates Element size and position.
     */
    protected BaseElement(final String name, final BaseCoordinate coordinates) {
        super(name);
        assert coordinates != null : "coordinates parameter is null.";
        this.left = coordinates.left;
        this.top = coordinates.top;
        this.width = coordinates.width;
        this.height = coordinates.height;
        this.virtualHeight = this.height;
        this.otherRelative = this;
    }

    /**
     * Set the element left position relative to an other element with a position difference.
     *
     * @param other    Element to use as reference position.
     * @param relative PositionRelative to use.
     * @param diff     Difference to set, in pixel.
     * @return This.
     */
    @Override
    public final BaseElement setLeft(final Element other, final PositionRelativeLeft relative, final int diff) {
        this.otherRelative = other;
        this.leftRelative = relative;
        this.otherRelativeLeftOffset = diff;

        switch (relative) {
            case CENTER:
                this.setLeft(other.getLeft() + (other.getWidth() >> 1) - (this.width >> 1) + diff);
                break;
            case LEFT:
                this.setLeft(other.getLeft() - this.width + diff);
                break;
            case RIGHT:
                this.setLeft(other.getLeft() + other.getWidth() + diff);
                break;
            case INSIDE_RIGHT:
                this.setLeft(other.getLeft() + other.getWidth() - this.width + diff);
                break;
            case INSIDE_LEFT:
                this.setLeft(other.getLeft() + diff);
                break;
            default:
                throw new IllegalArgumentException(relative + " is not a valid option.");
        }
        return this;
    }

    /**
     * Set the element left position relative to an other element with a position difference relative to the width of the parent.
     *
     * @param other        Element to use as reference position.
     * @param left         PositionRelative to use.
     * @param relativeDiff Difference to set, relative to the width of the parent.
     * @return This.
     */
    @Override
    public final BaseElement setLeft(final Element other, final PositionRelativeLeft left, final Relative relativeDiff) {
        return this.setLeft(other, left, (int) (other.getWidth() * relativeDiff.value));
    }

    /**
     * Show or hide the element.
     *
     * @param visible <code>true</code> will set the element visible, false will hide it.
     */
    @Override
    public final Element setVisible(final boolean visible) {
        if (visible) {
            this.show();
        } else {
            this.hide();
        }
        return this;
    }

    /**
     * Set the element left position relative to an other widget.
     *
     * @param other    Element to use as reference position.
     * @param relative PositionRelative to use.
     * @return This.
     */
    @Override
    public final BaseElement setLeft(final Element other, final PositionRelativeLeft relative) {
        this.setLeft(other, relative, 0);
        return this;
    }

    /**
     * Update position value without updating the widget.
     *
     * @param left Value to add to left value.
     * @param top  Value to add to top value.
     */
    public final void updateAddPositionValue(final int left, final int top) {
        this.left += left;
        this.top += top;
    }

    /**
     * Set the element top position relative to an other element with a position difference.
     *
     * @param other    Element to use as reference position.
     * @param relative PositionRelative to use.
     * @param diff     Difference to set, in pixel.
     * @return This.
     */
    @Override
    public final BaseElement setTop(final Element other, final PositionRelativeTop relative, final int diff) {
        this.otherRelative = other;
        this.topRelative = relative;
        this.otherRelativeTopOffset = diff;
        switch (relative) {
            case CENTER:
                this.setTop(other.getTop() + (other.getHeight() >> 1) - (this.height >> 1) + diff);
                break;
            case TOP:
                this.setTop(other.getTop() - this.height + diff);
                break;
            case TOP_INSIDE:
                this.setTop(other.getTop() + diff);
                break;
            case BOTTOM:
                this.setTop(other.getBottom() + diff);
                break;
            case BOTTOM_INSIDE:
                this.setTop(other.getBottom() - this.height + diff);
                break;
            default:
                throw new IllegalArgumentException(relative + " is not a valid option.");
        }
        return this;
    }

    /**
     * Set the element top position relative to an other element with a position difference relative to the height of the parent.
     *
     * @param other        Element to use as reference position.
     * @param top          PositionRelative to use.
     * @param relativeDiff Difference to set, relative to the height of the parent.
     * @return This.
     */
    @Override
    public final BaseElement setTop(final Element other, final PositionRelativeTop top, final Relative relativeDiff) {
        return this.setTop(other, top, (int) (other.getHeight() * relativeDiff.value));
    }

    /**
     * Set the element top position relative to an other widget.
     *
     * @param other    Element to use as reference position.
     * @param relative PositionRelative to use.
     * @return This.
     */
    @Override
    public final BaseElement setTop(final Element other, final PositionRelativeTop relative) {
        return this.setTop(other, relative, 0);
    }

    /**
     * Set at the same position as the other element.
     *
     * @param other Other element to get the position.
     * @return This.
     */
    @Override
    public final BaseElement setPosition(final Element other) {
        this.setPosition(other.getLeft(), other.getTop());
        return this;
    }

    @Override
    public final Element show() {
        if (!this.visible) {
            this.visible = true;
            this.showImpl();
        }
        return this;
    }

    @Override
    public final Element hide() {
        if (this.visible) {
            this.visible = false;
            this.hideImpl();
        }
        return this;
    }

    /**
     * Move the widget.
     *
     * @param xValue Add this value to the container current X position.
     * @param yValue Add this value to the container current Y position.
     */
    @Override
    public final void addToPosition(final int xValue, final int yValue) {
        this.setPosition(this.left + xValue, this.top + yValue);
    }

    /**
     * Move the element left or right by adding a value to its current x value.
     *
     * @param value Value to add to the current x position.
     */
    @Override
    public final void addToLeft(final int value) {
        this.setPosition(this.left + value, this.top);
    }

    /**
     * Move the element up or down by adding a value to its current y value.
     *
     * @param value Value to add to the current y position.
     */
    @Override
    public final void addToTop(final int value) {
        this.setPosition(this.left, this.top + value);
    }

    /**
     * Multiply the size of the element.
     *
     * @param factor Multiplication factor.
     */
    public final void multiplySize(final float factor) {
        this.setSize((int) (this.width * factor), (int) (this.height * factor));
    }

    /**
     * Delete the widget.
     */
    public abstract void delete();

    /**
     * Implementation to set the widget visible.
     */
    protected abstract void showImpl();

    /**
     * Implementation to set the widget invisible.
     */
    protected abstract void hideImpl();

    /**
     * Set the left and top element position.
     *
     * @param leftPosition Left position.
     * @param topPosition  Top position.
     */
    @Override
    public final Element setPosition(final int leftPosition, final int topPosition) {
        this.left = leftPosition;
        this.top = topPosition;
        return this.setPositionImpl(this.left, this.top);
    }

    /**
     * Set the element position.
     *
     * @param position New position.
     */
    @Override
    public final Element setPosition(final Point2D position) {
        return this.setPosition(position.getX(), position.getY());
    }

    @Override
    public final Element setTop(final int topValue) {
        this.setPosition(this.left, topValue);
        return this;
    }

    @Override
    public final Element setLeft(final int leftValue) {
        this.setPosition(leftValue, this.top);
        return this;
    }

    /**
     * @return Widget bottom border position.
     */
    @Override
    public final int getBottom() {
        return this.top + this.height;
    }

    /**
     * Update the widget size.
     *
     * @param size New width and height, in pixels.
     */
    @Override
    public final void setSize(final Size size) {
        this.setSize(size.width, size.height);
        this.virtualHeight = this.getHeight();
    }

    /**
     * Set the widget height.
     *
     * @param value New widget height value in pixels.
     */
    @Override
    public final void setHeight(final int value) {
        this.setSize(this.width, value);
    }

    /**
     * Set the widget width.
     *
     * @param value New widget width value in pixels.
     */
    @Override
    public final void setWidth(final int value) {
        this.setSize(value, this.height);
    }

    /**
     * @return An object containing this widget coordinates.
     */
    @Override
    public final BaseCoordinate getCoordinates() {
        return new Coordinates(this.width, this.height, this.left, this.top);
    }

    /**
     * Set the position and size coordinates.
     *
     * @param coordinates Coordinates to set.
     */
    @Override
    public final void setCoordinates(final BaseCoordinate coordinates) {
        this.setSize(coordinates.width, coordinates.height);
        this.setPosition(coordinates.left, coordinates.top);
    }

    /**
     * Update the widget size.
     *
     * @param newWidth  New width, in pixels.
     * @param newHeight New height, in pixels.
     */
    @Override
    public final void setSize(final int newWidth, final int newHeight) {
        if (this.width != newWidth || this.height != newHeight) {
            this.width = newWidth;
            this.height = newHeight;
            this.virtualHeight = this.height;
            if (!this.equals(this.otherRelative)) {
                this.setLeft(this.otherRelative, this.leftRelative, this.otherRelativeLeftOffset);
                this.setTop(this.otherRelative, this.topRelative, this.otherRelativeTopOffset);
            }
            this.setSizeImpl(newWidth, newHeight);
        }
    }

    @Override
    public final boolean isVisible() {
        return visible;
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public final int getLeft() {
        return left;
    }

    @Override
    public final int getTop() {
        return top;
    }

    public final int getOpacity() {
        return opacity;
    }

    /**
     * @return Widget right border position.
     */
    @Override
    public final int getRight() {
        return this.left + this.width;
    }

    @Override
    public final void setVirtualHeight(final int height) {
        this.virtualHeight = height;
    }

    @Override
    public final void resetVirtualHeight() {
        this.virtualHeight = this.getHeight();
    }

    /**
     * Implementation to set the widget size.
     *
     * @param newWidth  New width.
     * @param newHeight New height.
     */
    protected abstract void setSizeImpl(int newWidth, int newHeight);

    /**
     * Implementation to update the widget position.
     *
     * @param newLeft New widget x position.
     * @param newTop  New widget y position.
     * @return This object for chaining.
     */
    protected abstract Element setPositionImpl(int newLeft, int newTop);
}
