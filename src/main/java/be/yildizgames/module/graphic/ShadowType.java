/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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
package be.yildizgames.module.graphic;

/**
 * Possible type of shadows.
 *
 * @author Grégory Van den Borre
 */
public enum ShadowType {
    /**
     * No shadows.
     */
    NONE(0),

    /**
     * Texture additive shadows.
     */
    TEXTURE_ADDITIVE(1),

    /**
     * Texture modulative shadows.
     */
    TEXTURE_MODULATIVE(2),

    /**
     * Stencil additive shadows.
     */
    STENCIL_ADDITIVE(3),

    /**
     * Stencil modulative shadows.
     */
    STENCIL_MODULATIVE(4);

    /**
     * Associated value to avoid to depend on the natural order.
     */
    public final int value;

    /**
     * Constructor set the value.
     *
     * @param value Associated value.
     */
    ShadowType(final int value) {
        this.value = value;
    }
}
