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

import be.yildizgames.common.logging.LogFactory;
import org.slf4j.Logger;

/**
 * Simple wrapper class for Z values.
 *
 * @author Grégory Van den Borre
 */
public final class Zorder implements Comparable<Zorder> {

    private static final Logger LOGGER = LogFactory.getInstance().getLogger(Zorder.class);

    /**
     * Z order to display GUI elements, nearly the highest value.
     */
    public static final Zorder GUI = new Zorder(640);
    /**
     * Z order to bottom elements.
     */
    public static final Zorder ZERO = new Zorder(0);
    /**
     * Minimum possible z value.
     */
    private static final int MIN = 0;
    /**
     * Maximum possible z value(duz to a limitation in Ogre engine).
     */
    private static final int MAX = 650;
    /**
     * Wrapped Z value.
     */
    private final int value;

    /**
     * Constructor, wrap the int value as Z order.
     *
     * @param z Z value.
     */
    public Zorder(final int z) {
        super();
        if (z < MIN || z > MAX) {
            LOGGER.error("Z should be between {} and {} value={} Assigned value max value ({}) instead", MIN, MAX, z, MAX);
            this.value = MAX;
        } else {
            this.value = z;
        }
    }

    /**
     * Create a new Zorder with a Z value added to the one of this object.
     *
     * @param toAdd Z value to add.
     * @return A new Zorder with Z value being the addition of this Z and the parameter value.
     */
    public Zorder add(final int toAdd) {
        return new Zorder(this.value + toAdd);
    }

    @Override
    public int compareTo(final Zorder o) {
        if (o.value == this.value) {
            return 0;
        } else if (o.value > this.value) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Z order:" + this.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Zorder zorder = (Zorder) o;

        return value == zorder.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
