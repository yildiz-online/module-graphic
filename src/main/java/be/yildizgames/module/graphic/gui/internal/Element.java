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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.common.util.Registerable;
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.coordinates.Relative;
import be.yildizgames.module.coordinates.Size;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;

/**
 * @author Grégory Van den Borre
 */
public interface Element extends Registerable {

    /**
     * Set the element invisible on screen.
     *
     * @return This.
     */
    Element hide();

    Element setPosition(Element other);

    /**
     * Set the element visible on screen.
     *
     * @return This.
     */
    Element show();

    Element setTop(Element other, PositionRelativeTop relative, int diff);

    Element setTop(Element other, PositionRelativeTop top, Relative relativeDiff);

    Element setTop(Element other, PositionRelativeTop relative);

    Element setLeft(Element other, PositionRelativeLeft relative);

    Element setLeft(Element other, PositionRelativeLeft relative, int diff);

    Element setLeft(Element other, PositionRelativeLeft left, Relative relativeDiff);

    /**
     * @return The element left position.
     */
    int getLeft();

    /**
     * Set the element left position.
     *
     * @param left New widget left position in pixels.
     * @return This.
     */
    Element setLeft(int left);

    /**
     * @return The element top position.
     */
    int getTop();

    /**
     * Set the element top position.
     *
     * @param top New widget top position in pixels.
     * @return This.
     */
    Element setTop(int top);

    void addToPosition(int xValue, int yValue);

    void addToLeft(int value);

    /**
     * Move the element to the top or bottom.
     *
     * @param value Move the element to y pixel to the bottom, negative value will move to the top.
     */
    void addToTop(int value);

    /**
     * Set the element position.
     *
     * @param left Element X position.
     * @param top  Element Y position.
     * @return This object for chaining.
     */
    Element setPosition(int left, int top);

    /**
     * @return The element width.
     */
    int getWidth();

    /**
     * Set the element width.
     *
     * @param width New width.
     */
    void setWidth(int width);

    /**
     * @return The element height.
     */
    int getHeight();

    /**
     * Set the element height.
     *
     * @param height New height.
     */
    void setHeight(int height);

    int getBottom();

    void setSize(Size size);

    Coordinates getCoordinates();

    void setCoordinates(Coordinates coordinates);

    boolean isVisible();

    /**
     * Show or hide the element.
     *
     * @param visible <code>true</code> to show the element, <code>false</code> to hide it.
     * @return This object for chaining.
     */
    Element setVisible(boolean visible);

    void setSize(int newWidth, int newHeight);

    int getRight();

    void setVirtualHeight(int height);

    void resetVirtualHeight();

    /**
     * Alignment value to set the widgets positions.
     *
     * @author Van den Borre Grégory
     */
    enum Alignment {

        /**
         * Widget will be left aligned, its left position will be computed from its left part. It is the default behavior.
         */
        LEFT,

        /**
         * Widget will be right aligned, its left position will be computed from its right part.
         */
        RIGHT,

        /**
         * Widget will be top aligned, its top position will be computed from its top part. It is the default behavior.
         */
        TOP,

        /**
         * Widget will be bottom aligned, its top position will be computed from its bottom part.
         */
        BOTTOM,

        /**
         * Widget will be centered its left position will be computed from its half width and the top will be assumed to be its half height.
         */
        CENTER

    }




}
