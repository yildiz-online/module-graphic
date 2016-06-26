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

import be.yildiz.common.Color;
import be.yildiz.common.collections.Lists;
import lombok.Getter;

import java.util.List;

/**
 * A material technique can be composed of several pass.
 *
 * @author Grégory Van den Borre
 */
public abstract class MaterialPass {

    /**
     * Associated texture unit.
     */
    private final List<TextureUnit> unitList;
    /**
     * Used transparent capability.
     */
    @Getter
    private Transparency transparency;
    /**
     * Used blending mode.
     */
    @Getter
    private BlendMode blendMode;

    /**
     * Full constructor.
     */
    protected MaterialPass() {
        super();
        this.unitList = Lists.newList();
        this.transparency = MaterialPass.Transparency.NONE;
        this.blendMode = MaterialPass.BlendMode.NONE;
    }

    /**
     * Copy constructor.
     *
     * @param unitList     List of associated texture units.
     * @param transparency Transparency value.
     * @param blend        Blend mode value.
     */
    protected MaterialPass(final List<TextureUnit> unitList, final Transparency transparency, final BlendMode blend) {
        super();
        this.transparency = transparency;
        this.blendMode = blend;
        this.unitList = unitList;
    }

    /**
     * Create a texture unit for this pass.
     *
     * @return The created texture unit.
     */
    public final TextureUnit createUnit() {
        TextureUnit unit = this.createUnitImpl(this.unitList.size());
        this.unitList.add(unit);
        return unit;
    }

    /**
     * Set a diffuse color.
     *
     * @param color Diffuse color value.
     * @return This object.
     */
    public final MaterialPass setDiffuse(final Color color) {
        this.setDiffuseImpl(color);
        return this;
    }

    /**
     * Show or hide colors for this pass units.
     *
     * @param show <code>true</code> to show, <code>false</code> to hide.
     * @return This object.
     */
    public final MaterialPass enableColor(final boolean show) {
        this.enableColorImpl(show);
        return this;
    }

    /**
     * The lights will no longer affect the material pass.
     *
     * @return This object.
     */
    public final MaterialPass disableLight() {
        this.disableLightImpl();
        return this;
    }

    /**
     * Disable the light for this material in implementation.
     */
    protected abstract void disableLightImpl();

    /**
     * The the scene blending.
     *
     * @param blend1 Source blending factor.
     * @param blend2 Destination blending factor.
     * @return This object.
     */
    public abstract MaterialPass setSceneBlend(SceneBlend blend1, SceneBlend blend2);

    /**
     * Set the ambient color value.
     *
     * @param color Ambient color to use.
     * @return This object.
     */
    public final MaterialPass setAmbient(final Color color) {
        this.setAmbientImpl(color);
        return this;
    }

    /**
     * Create the unit in implementation.
     *
     * @param index Associated index.
     * @return The new Texture unit.
     */
    protected abstract TextureUnit createUnitImpl(int index);

    /**
     * Enable or disable color in implementation.
     *
     * @param show <code>true</code> will enable the color, <code>false</code>
     *             will disable it.
     */
    protected abstract void enableColorImpl(final boolean show);

    /**
     * Set the diffuse color in implementation.
     *
     * @param color Diffuse color value.
     */
    protected abstract void setDiffuseImpl(final Color color);

    /**
     * Set the ambient color in implementation.
     *
     * @param color Ambient color value.
     */
    protected abstract void setAmbientImpl(Color color);

    /**
     * @param index Texture unit index.
     * @return The texture unit.
     */
    public final TextureUnit getUnit(final int index) {
        assert index >= 0 && index < this.unitList.size();
        return this.unitList.get(index);
    }

    /**
     * Modify the transparent capability of this pass.
     *
     * @param capability New capability value.
     * @return This object.
     */
    public final MaterialPass setTransparency(final Transparency capability) {
        this.transparency = capability;
        this.setTransparentCapabilityImpl(capability);
        return this;
    }

    /**
     * Apply a blend mode to the pass.
     *
     * @param mode Mode to use.
     * @return This object.
     */
    public final MaterialPass setBlendMode(final BlendMode mode) {
        this.blendMode = mode;
        this.setBlendModeImpl(mode);
        return this;
    }

    /**
     * Set the transparent capability in implementation.
     *
     * @param capability Value to set.
     */
    protected abstract void setTransparentCapabilityImpl(final Transparency capability);

    /**
     * Set the blend mode in implementation.
     *
     * @param mode Mode to apply.
     */
    protected abstract void setBlendModeImpl(BlendMode mode);

    /**
     * Set the emissive color.
     *
     * @param color Color to set as emissive.
     * @return This object.
     */
    public abstract MaterialPass setEmissive(Color color);

    /**
     * Enable or disable the depth write.
     *
     * @param enable <code>true</code> to enable, <code>false</code> to disable.
     * @return This object.
     */
    public abstract MaterialPass setDepthWrite(boolean enable);

    /**
     * Set a shader vertex program to this pass.
     *
     * @param name Program name.
     * @return This object.
     */
    public abstract MaterialPass setVertexProgram(String name);

    /**
     * Set a shader fragment program to this pass.
     *
     * @param name Program name.
     * @return This object.
     */
    public abstract MaterialPass setFragmentProgram(String name);

    /**
     * Set a shader fragment program to this pass.
     *
     * @param shader Shader to set.
     */
    public final void setFragmentProgram(final FragmentShader shader) {
        this.setFragmentProgram(shader.getName());
    }

    /**
     * Set a shader fragment program to this pass.
     *
     * @param shader Shader to set.
     */
    public final void setVertexProgram(final VertexShader shader) {
        this.setVertexProgram(shader.getName());
    }

    /**
     * Set the parameters for a fragment program.
     *
     * @param param Parameter param.
     */
    public abstract void setFragmentProgramParameter(ShaderParamFloat4 param);

    /**
     * Set the parameters for a fragment program.
     *
     * @param param Parameter param.
     */
    public abstract void setFragmentProgramParameter(ShaderParamFloat param);

    /**
     * Set the parameters for a fragment program.
     *
     * @param param Parameter param.
     */
    public abstract void setFragmentProgramParameter(ShaderParamColor param);

    /**
     * Set the auto parameters for a fragment program.
     *
     * @param name Parameter name.
     * @param auto Parameter automatically updated from graphic engine.
     */
    public abstract void setFragmentProgramParameterAuto(String name, ShaderConstantType auto);

    /**
     * Set the auto parameters for a fragment program.
     *
     * @param name  Parameter name.
     * @param auto  Parameter automatically updated from graphic engine.
     * @param param Additional parameter.
     */
    public abstract void setFragmentProgramParameterAuto(String name, ShaderConstantType auto, int param);

    /**
     * Set the auto parameters for a vertex program.
     *
     * @param name  Parameter name.
     * @param auto  Parameter automatically updated from graphic engine.
     * @param param Additional parameter.
     */
    public abstract void setVertexProgramParameterAuto(String name, ShaderConstantType auto, int param);

    /**
     * Set the auto parameters for a vertex program.
     *
     * @param name Parameter name.
     * @param auto Parameter automatically updated from graphic engine.
     */
    public abstract void setVertexProgramParameterAuto(String name, ShaderConstantType auto);

    /**
     * The transparency can be computed in different ways, they are exposed in
     * this enum.
     *
     * @author Van den Borre Grégory
     */
    public enum Transparency {

        /**
         * Use this if the transparent image contains an alpha channel.
         */
        ALPHA,

        /**
         * Use this if the image contains no alpha channel, transparency will be
         * based on the image color.
         */
        COLOR,

        /**
         * No transparent capability.
         */
        NONE
    }

    /**
     * Blend the pass with the rest of the scene.
     *
     * @author Van den Borre Grégory
     */
    public enum BlendMode {

        /**
         * Additive blending mode.
         */
        ADD,

        /**
         * Subtract blending mode.
         */
        SUBTRACT,

        /**
         * Reverse subtract blending mode.
         */
        REVERSE_SUBTRACT,

        /**
         * Minimum blending mode.
         */
        MIN,

        /**
         * Maximum blending mode.
         */
        MAX,

        /**
         * No blending mode.
         */
        NONE

    }

    /**
     * Different options for scene blending transparency.
     *
     * @author Van den Borre Grégory
     */
    public enum SceneBlend {

        /**
         * Constant value of 0.0.
         */
        ZERO,

        /**
         * Constant value of 1.0.
         */
        ONE,

        /**
         * The existing pixel color.
         */
        DEST_COLOR,

        /**
         * The texture pixel (texel) color.
         */
        SRC_COLOR,

        /**
         * 1 - (dest_colour).
         */
        ONE_MINUS_DEST_COLOR,

        /**
         * 1 - (src_colour).
         */
        ONE_MINUS_SRC_COLOR,

        /**
         * The existing pixel alpha value.
         */
        DEST_ALPHA,

        /**
         * The texel alpha value.
         */
        SRC_ALPHA,

        /**
         * 1 - (dest_alpha).
         */
        ONE_MINUS_DEST_ALPHA,

        /**
         * 1 - (src_alpha).
         */
        ONE_MINUS_SRC_ALPHA,

        /**
         * Nothing.
         */
        NONE
    }
}
