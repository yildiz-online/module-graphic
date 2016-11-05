//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic;

import lombok.Getter;

import java.util.Arrays;

/**
 * A parameter for a shader, wrap a float[4].
 *
 * @author Grégory Van den Borre
 */
public final class ShaderParamFloat4 {

    /**
     * Parameter name.
     */
    @Getter
    private final String name;

    /**
     * Parameter values.
     */
    private final float[] values;

    /**
     * Full constructor.
     *
     * @param name Shader name.
     * @param v1 First value.
     * @param v2 Second value.
     * @param v3 Third value.
     * @param v4 Fourth value.
     */
    public ShaderParamFloat4(final String name, final float v1, final float v2, final float v3, final float v4) {
        super();
        this.name = name;
        this.values = new float[4];
        this.values[0] = v1;
        this.values[1] = v2;
        this.values[2] = v3;
        this.values[3] = v4;
    }

    /**
     * @return The param values.
     */
    public float[] getValues() {
        return Arrays.copyOf(this.values, this.values.length);
    }
}
