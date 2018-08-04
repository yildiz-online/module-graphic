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

package be.yildizgames.module.graphic.misc;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.color.Color;

/**
 * Text to position in 3D world.
 *
 * @author Grégory Van den Borre
 */
public interface MovableText extends Movable {

    /**
     * Set the text color.
     *
     * @param color Color to use.
     */
    void setTextColor(Color color);

    /**
     * Set the text alignment.
     *
     * @param h Horizontal alignment.
     * @param v Vertical alignment.
     */
    void setTextAlignement(Horizontal h, Vertical v);

    /**
     * Set offset position.
     *
     * @param offset Offset value.
     */
    void setTextOffset(Point3D offset);

    void show();

    void hide();

    /**
     * Horizontal alignment.
     *
     * @author Van den Borre Grégory
     */
     enum Horizontal {

        /**
         * Text is left aligned.
         */
        LEFT,

        /**
         * Text is center.
         */
        CENTER
    }

    /**
     * Vertical alignment.
     *
     * @author Van den Borre Grégory
     */
    enum Vertical {

        /**
         * Text is at the bottom.
         */
        BELOW,

        /**
         * Text is on top.
         */
        ABOVE,

        /**
         * Text is centered.
         */
        CENTER
    }
}
