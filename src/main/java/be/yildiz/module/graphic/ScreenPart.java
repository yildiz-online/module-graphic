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

package be.yildiz.module.graphic;

import be.yildiz.common.Size;
import be.yildiz.common.util.Checker;
import be.yildiz.common.vector.Point2D;
import be.yildiz.common.vector.Point3D;
import be.yildiz.module.window.Cursor;

/**
 * Different virtual part of the screen.
 *
 * @author Grégory Van den Borre
 */
//FIXME too many computations, make optimisations
public enum ScreenPart {

    /**
     * From left part of screen to left part + border size.
     */
    LEFT(Point3D.INVERT_X),

    /**
     * From right part of screen to right part - border size.
     */
    RIGHT(Point3D.X),

    /**
     * From top part to top part + border size.
     */
    UP(Point3D.INVERT_Z),

    /**
     * From bottom part to bottom part - border size.
     */
    BOTTOM(Point3D.Z),

    /**
     * From top and left part.
     */
    LEFT_UP(Point3D.xz(-1, -1)),

    /**
     * From top and right part.
     */
    RIGHT_UP(Point3D.xz(1, -1)),

    /**
     * From bottom and left part.
     */
    LEFT_BOTTOM(Point3D.xz(-1, 1)),

    /**
     * From bottom and right part.
     */
    RIGHT_BOTTOM(Point3D.xz(1, 1)),

    /**
     * If in none of the other screen parts.
     */
    CENTER(Point3D.ZERO);

    /**
     * Border limit.
     */
    private static final int BORDER_SIZE = 5;

    /**
     * Direction associated to the screen part.
     */
    private final Point3D direction;

    private Cursor cursor;

    /**
     * Full constructor.
     *
     * @param associatedDirection Direction matching the screen part.
     */
    ScreenPart(final Point3D associatedDirection) {
        this.direction = associatedDirection;
    }

    /**
     * Retrieve the screen part containing the given position.
     *
     * @param res      Rectangle with the screen size.
     * @param position Position to check.
     * @return The screen part where the given position is.
     */
    public static ScreenPart getFromPosition(Size res, final Point2D position) {
        final float x = position.getX();
        final float y = position.getY();
        final boolean left = Checker.inRange(x, 0, ScreenPart.BORDER_SIZE);
        final boolean right = Checker.inRange(x, res.width - ScreenPart.BORDER_SIZE, res.width);
        final boolean up = Checker.inRange(y, 0, ScreenPart.BORDER_SIZE);
        final boolean bottom = Checker.inRange(y, res.height - ScreenPart.BORDER_SIZE, res.height);
        ScreenPart result;
        if (left) {
            if (up) {
                result = ScreenPart.LEFT_UP;
            } else if (bottom) {
                result = ScreenPart.LEFT_BOTTOM;
            } else {
                result = ScreenPart.LEFT;
            }
        } else if (right) {
            if (up) {
                result = ScreenPart.RIGHT_UP;
            } else if (bottom) {
                result = ScreenPart.RIGHT_BOTTOM;
            } else {
                result = ScreenPart.RIGHT;
            }
        } else if (up) {
            result = ScreenPart.UP;
        } else if (bottom) {
            result = ScreenPart.BOTTOM;
        } else {
            result = ScreenPart.CENTER;
        }

        return result;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    /**
     * @return the associated direction.
     */
    public Point3D getDirection() {
        return this.direction;
    }
}
