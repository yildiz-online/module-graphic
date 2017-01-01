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

import be.yildiz.common.collections.Lists;
import be.yildiz.common.resource.Resource;
import be.yildiz.common.util.Registerer;
import be.yildiz.module.graphic.MaterialPass.BlendMode;
import be.yildiz.module.graphic.MaterialPass.SceneBlend;
import be.yildiz.module.graphic.MaterialPass.Transparency;

import java.util.List;

/**
 * A Material is an element used to display 2D image on a 2D or 3D object. It has a unique name, and a List of MaterialTechnique.
 *
 * @author Grégory Van den Borre
 */
public abstract class Material extends Resource {

    /**
     * Simple gray material name, to get the matching material use Material.gray().
     */
    public static final String GRAY_NAME = "_internal_gray_";
    /**
     * Empty Texture name, to get the matching material use Material.empty().
     */
    public static final String EMPTY_NAME = "empty";
    /**
     * Simple green material name, to get the matching material use Material.green().
     */
    public static final String GREEN_NAME = "_internal_green_";
    /**
     * Simple red material name, to get the matching material use Material.red().
     */
    public static final String RED_NAME = "_internal_red_";
    /**
     * Simple blue material name, to get the matching material use Material.blue().
     */
    public static final String BLUE_NAME = "_internal_blue_";
    /**
     * Simple yellow material name, to get the matching material use Material.yellow().
     */
    public static final String YELLOW_NAME = "_internal_yellow_";
    /**
     * Simple black material name, to get the matching material use Material.black().
     */
    public static final String BLACK_NAME = "_internal_black_";
    /**
     * Contains all created Material, to check name is unique and to retrieve a Material from its name.
     */
    private static final Registerer<Material> REGISTERER = Registerer.newRegisterer();
    /**
     * List of all MaterialTechnique contained in the texture.
     */
    private final List<MaterialTechnique> techniqueList;

    /**
     * Protected constructor, only called from children.
     *
     * @param name Material name, must be unique
     */
    protected Material(final String name) {
        this(name, Lists.newList());
    }

    /**
     * Copy constructor.
     *
     * @param name Name for the new material.
     * @param list List of copied techniques.
     */
    protected Material(final String name, final List<MaterialTechnique> list) {
        super(name);
        this.techniqueList = list;
        Material.REGISTERER.register(this);
    }

    /**
     * Retrieve a Material from its unique name.
     *
     * @param name Unique material name.
     * @return Found material.
     */
    public static Material get(final String name) {
        return Material.REGISTERER.get(name);
    }

    /**
     * @return An empty material.
     */
    public static Material empty() {
        return Material.REGISTERER.get(Material.EMPTY_NAME);
    }

    /**
     * @return A simple green material.
     */
    public static Material green() {
        return Material.REGISTERER.get(Material.GREEN_NAME);
    }

    /**
     * @return A simple blue material.
     */
    public static Material blue() {
        return Material.REGISTERER.get(Material.BLUE_NAME);
    }

    /**
     * @return A simple red material.
     */
    public static Material red() {
        return Material.REGISTERER.get(Material.RED_NAME);
    }

    /**
     * @return A simple gray material.
     */
    public static Material gray() {
        return Material.REGISTERER.get(Material.GRAY_NAME);
    }

    /**
     * @return A simple yellow material.
     */
    public static Material yellow() {
        return Material.REGISTERER.get(Material.YELLOW_NAME);
    }

    /**
     * @return A simple black material.
     */
    public static Material black() {
        return Material.REGISTERER.get(Material.BLACK_NAME);
    }

    /**
     * Create a new MaterialTechnique and insert it in the list.
     *
     * @return The newly created MaterialTechnique.
     */
    public final MaterialTechnique createTechnique() {
        MaterialTechnique technique = this.createTechniqueImpl(this.techniqueList.size());
        this.techniqueList.add(technique);
        return technique;
    }

    /**
     * Add a technique with a glow texture.
     *
     * @param maskFile Texture mask to glow.
     */
    public final void addGlowTechnique(final String maskFile) {
        final MaterialTechnique glow = this.getTechnique(0);
        final MaterialPass glowPass = glow.getPass(0);
        final TextureUnit glowUnit = glowPass.getUnit(0);
        glowUnit.setTexture(maskFile);
        glow.setGlow();
    }

    /**
     * Set the blend mode for the first technique, first pass.
     *
     * @param mode BlendMode to set.
     */
    public final void setBlendMode(final BlendMode mode) {
        for (MaterialTechnique t : this.techniqueList) {
            for (MaterialPass p : t.getPassList()) {
                p.setBlendMode(mode);
            }
        }
    }

    /**
     * Set the scene blend for the first pass of the first technique.
     *
     * @param sb1 Blending first parameter.
     * @param sb2 Blending second parameter.
     */
    public final void setSceneBlend(final SceneBlend sb1, final SceneBlend sb2) {
        this.getTechnique(0).getPass(0).setSceneBlend(sb1, sb2);
    }

    /**
     * The material will no longer be affected by light and will always be visible(useful for GUI elements...).
     *
     * @return This object.
     */
    public final Material disableLight() {
        for (MaterialTechnique t : this.techniqueList) {
            for (MaterialPass p : t.getPassList()) {
                p.disableLight();
            }
        }
        return this;
    }

    /**
     * Create a copy of this material.
     *
     * @param name The name must be unique so the copy must have a new name.
     * @return A perfect copy of this material except for the name.
     */
    public final Material copy(final String name) {
        return this.copyImpl(name);
    }

    /**
     * Add an effect to this material.
     *
     * @param type Effect type to apply.
     * @param time Time for the effect to be completed, in millisec.
     * @return The applied material effect.
     */
    public abstract MaterialEffect addEffect(MaterialEffect.EffectType type, long time);

    /**
     * Implementation specific to copy the material.
     *
     * @param name Copy unique name.
     * @return The copied material.
     */
    protected abstract Material copyImpl(String name);

    /**
     * Add a technique in implementation.
     *
     * @param techniqueIndex Index of the added technique.
     * @return The created technique.
     */
    protected abstract MaterialTechnique createTechniqueImpl(int techniqueIndex);

    /**
     * Get a technique used with this material, throw InvalidParameterException If no technique is found for the given parameter.
     *
     * @param index Index of the technique used, first is 0.
     * @return The associated technique.
     */
    public final MaterialTechnique getTechnique(final int index) {
        final MaterialTechnique technique = this.techniqueList.get(index);
        if (technique == null) {
            throw new IllegalArgumentException("No technique found for this index.");
        }
        return technique;
    }

    /**
     * Set the material receive shadows or not.
     *
     * @param receive <code>true</code> to receive.
     */
    public final void receiveShadow(final boolean receive) {
        this.receiveShadowImpl(receive);
    }

    /**
     * Implementation specific to receive shadows on material.
     *
     * @param receive <code>true</code> to receive.
     */
    protected abstract void receiveShadowImpl(boolean receive);

    /**
     * Set transparency for every pass of every techniques.
     *
     * @param t Transparency type.
     * @return This object.
     */
    public final Material setTransparency(final Transparency t) {
        for (MaterialTechnique tec : this.techniqueList) {
            for (MaterialPass pass : tec.getPassList()) {
                pass.setTransparency(t);
            }
        }
        return this;
    }
}
