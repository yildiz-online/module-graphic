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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.module.graphic.gui.container.Container;

public interface ContainerChild extends Widget{
    /**
     * Remove this widget from its parent.
     */
    void detachFromParent();

    /**
     * Set the left position relative to the parent position.
     *
     * @param p Position to set.
     * @return This element for chaining methods.
     */
    ContainerChild setLeftFromParent(PositionRelativeLeft p);

    /**
     * Set the left position relative to the parent position with a difference.
     *
     * @param relative Position to set.
     * @param diff     Difference to set.
     * @return This element for chaining methods.
     */
    ContainerChild setLeftFromParent(PositionRelativeLeft relative, int diff);

    /**
     * Set the top position relative to the parent position.
     *
     * @param relative Position to set.
     * @return This element for chaining methods.
     */
    ContainerChild setTopFromParent(PositionRelativeTop relative);

    /**
     * Set the top position relative to the parent position with a difference.
     *
     * @param relative Position to set.
     * @param diff     Difference to set.
     * @return This element for chaining methods.
     */
    ContainerChild setTopFromParent(PositionRelativeTop relative, int diff);

    Container getParent();

    void updateAddPositionValue(int left, int top);

    void updateSizeAfterZoom(float zoomFactor);
}
