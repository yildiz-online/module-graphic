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

package be.yildizgames.module.graphic.shader;

/**
 * A parameter for a shader with 3 float values.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class ShaderParamFloat3 {

    /**
     * Parameter name.
     */
    public final String name;

    /**
     * Parameter value 1.
     */
    public final float value1;

    /**
     * Parameter value 2.
     */
    public final float value2;

    /**
     * Parameter value 3.
     */
    public final float value3;

    /**
     * A parameter with 3 float values.
     * @param name Parameter name.
     * @param v1 Float value.
     * @param v2 Float value.
     * @param v3 Float value.
     */
    ShaderParamFloat3(final String name, final float v1, final float v2, final float v3) {
        super();
        this.name = name;
        this.value1 = v1;
        this.value2 = v2;
        this.value3 = v3;
    }
}
