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

package be.yildizgames.module.graphic.shader;

import be.yildizgames.module.color.Color;

/**
 *
 * @author Grégory Van den Borre
 */
public class ShaderParam {

    public static ShaderParamColor color(String name, Color color) {
        return new ShaderParamColor(name, color);
    }

    public static ShaderParamFloat float1(String name, float value) {
        return new ShaderParamFloat(name, value);
    }

    public static ShaderParamFloat2 float2(String name, float value1, float value2) {
        return new ShaderParamFloat2(name, value1, value2);
    }

    public static ShaderParamFloat3 float3(String name, float value1, float value2, float value3) {
        return new ShaderParamFloat3(name, value1, value2, value3);
    }

    public static ShaderParamFloat4 float4(String name, float value1, float value2, float value3, float value4) {
        return new ShaderParamFloat4(name, value1, value2, value3, value4);
    }
}
