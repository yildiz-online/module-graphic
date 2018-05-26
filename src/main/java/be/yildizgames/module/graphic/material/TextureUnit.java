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

package be.yildizgames.module.graphic.material;

import be.yildizgames.module.color.Color;

/**
 * A texture unit is the base part of a material.
 *
 * @author Grégory Van Den Borre
 */
public abstract class TextureUnit {

    /**
     * Protected constructor, only called from children.
     */
    protected TextureUnit() {
        super();
    }

    /**
     * Set a new texture.
     *
     * @param path Image path.
     * @return This object.
     */
    public final TextureUnit setTexture(final String path) {
        this.setTextureImpl(path);
        return this;
    }

    /**
     * @param basePath       Image path, expected format is path/name.extension, and the
     *                       files will be path/nameX.extension, X being the image
     *                       number(0,1,...). i.e. for files img0.tga, img1.tga..., path is
     *                       img.tga.
     * @param numberOfFrames Number of frames to play.
     * @param duration       Animation duration, in seconds.
     * @return This object.
     */
    public abstract TextureUnit setTextureAnimated(String basePath, int numberOfFrames, int duration);

    /**
     * Set the unit color operation.
     *
     * @param op Color operation.
     */
    public abstract void setColorOperation(ColorOperation op);

    /**
     * Set the unit color operation.
     *
     * @param op   Color operation.
     * @param src1 Operation source 1.
     * @param src2 Operation source 2.
     */
    public abstract void setColorOperationEx(LayerBlendOperationEx op, LayerBlendSource src1, LayerBlendSource src2);

    /**
     * Set the unit color operation.
     *
     * @param op      Color operation.
     * @param source1 Operation source 1.
     * @param source2 Operation source 2.
     * @param color   Color to set.
     */
    public abstract void setColorOperationEx(LayerBlendOperationEx op, LayerBlendSource source1, LayerBlendSource source2, Color color);

    /**
     * Set the unit animation scroll speed.
     *
     * @param x Animation X speed.
     * @param y Animation Y speed.
     */
    public abstract void scroll(float x, float y);

    /**
     * Scale uniformly the texture.
     *
     * @param scale Scale factor.
     */
    public final void setScale(final float scale) {
        this.setScale(scale, scale);
    }

    /**
     * Scale the texture.
     *
     * @param xScale Scale factor for width.
     * @param yScale Scale factor for height.
     */
    public final void setScale(final float xScale, final float yScale) {
        this.setScaleImpl(xScale, yScale);
    }

    /**
     * Call implementation to set the scale.
     *
     * @param xScale Width scale.
     * @param yScale Height scale.
     */
    protected abstract void setScaleImpl(float xScale, float yScale);

    /**
     * Call implementation to set the texture.
     *
     * @param path Image path.
     */
    protected abstract void setTextureImpl(String path);

    /**
     * Set alpha operation.
     *
     * @param operation Alpha operation.
     * @param source1   Operation source 1.
     * @param source2   Operation source 2.
     */
    public abstract void setAlphaOperation(LayerBlendOperationEx operation, LayerBlendSource source1, LayerBlendSource source2);

    public abstract void setCoordinateSet(int set);

    public final void setColor(Color color) {
        this.setColorOperationEx(TextureUnit.LayerBlendOperationEx.SOURCE1, TextureUnit.LayerBlendSource.MANUAL, TextureUnit.LayerBlendSource.CURRENT, color);
    }

    /**
     * Possible color operations.
     *
     * @author Van den Borre Grégory
     */
    public enum ColorOperation {

        /**
         * Replace all color with texture with no adjustment.
         */
        REPLACE,

        /**
         * Add color components together.
         */
        ADD,

        /**
         * Multiply color components together.
         */
        MODULATE,

        /**
         * Blend based on texture alpha.
         */
        ALPHA_BLEND
    }

    /**
     * Possible layer blend operations.
     *
     * @author Van den Borre Grégory
     */
    public enum LayerBlendOperationEx {

        /**
         * Use source1 without modification.
         */
        SOURCE1,

        /**
         * Use source2 without modification.
         */
        SOURCE2,

        /**
         * Multiply source1 and source2 together.
         */
        MODULATE,

        /**
         * As MODULATE but brighten afterwards (x2).
         */
        MODULATE_X2,

        /**
         * As MODULATE but brighten more afterwards (x4).
         */
        MODULATE_X4,

        /**
         * Add source1 and source2 together.
         */
        ADD,

        /**
         * As ADD, but subtract 0.5 from the result.
         */
        ADD_SIGNED,

        /**
         * As ADD, but subtract product from the sum.
         */
        ADD_SMOOTH,

        /**
         * Subtract source2 from source1.
         */
        SUBTRACT,

        /**
         * Use interpolated alpha value from vertices to scale source1, then add
         * source2 scaled by (1-alpha).
         */
        BLEND_DIFFUSE_ALPHA,

        /**
         * As BLEND_DIFFUSE_ALPHA, but use alpha from texture.
         */
        BLEND_TEXTURE_ALPHA,

        /**
         * As BLEND_DIFFUSE_ALPHA, but use current alpha from previous stages.
         */
        BLEND_CURRENT_ALPHA,

        /**
         * As BLEND_DIFFUSE_ALPHA but use a constant manual blend value
         * (0.0-1.0).
         */
        BLEND_MANUAL,

        /**
         * Dot product of color1 and color2.
         */
        DOTPRODUCT,

        /**
         * use interpolated color values from vertices to scale source1, then
         * add source2 scaled by (1-color).
         */
        BLEND_DIFFUSE_COLOUR
    }

    /**
     * Possible layer blend sources.
     *
     * @author Van den Borre Grégory
     */
    public enum LayerBlendSource {

        /**
         * The color as built up from previous stages.
         */
        CURRENT,

        /**
         * The color derived from the texture assigned to this layer.
         */
        TEXTURE,

        /**
         * The interpolated diffuse color from the vertices.
         */
        DIFFUSE,

        /**
         * The interpolated specular color from the vertices.
         */
        SPECULAR,

        /**
         * The color supplied manually as a separate argument.
         */
        MANUAL

    }

    /**
     * Filter to apply on texture.
     *
     * @author Van den Borre Grégory
     */
    public enum TextureFilter {

        /**
         * Anisotropic filter.
         */
        ANISOTROPIC(0),

        /**
         * Bilinear filter.
         */
        BILINEAR(1),

        /**
         * No filter.
         */
        NONE(2),

        /**
         * Trilinear filter.
         */
        TRILINEAR(3);

        /**
         * Associated value to avoid to rely on enumeration order.
         */
        private final int value;

        /**
         * Full constructor.
         *
         * @param value Associated value.
         */
        TextureFilter(final int value) {
            this.value = value;
        }

        /**
         * @return The associated value.
         */
        public int getValue() {
            return this.value;
        }
    }
}
