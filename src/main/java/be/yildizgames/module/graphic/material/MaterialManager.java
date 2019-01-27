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

package be.yildizgames.module.graphic.material;

import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.material.MaterialPass.Transparency;
import be.yildizgames.module.graphic.material.TextureUnit.LayerBlendOperationEx;
import be.yildizgames.module.graphic.material.TextureUnit.LayerBlendSource;
import be.yildizgames.module.graphic.shader.FragmentShader;
import be.yildizgames.module.graphic.shader.Shader;
import be.yildizgames.module.graphic.shader.Shader.FragmentProfileList;
import be.yildizgames.module.graphic.shader.Shader.VertexProfileList;
import be.yildizgames.module.graphic.shader.VertexShader;

/**
 * Manage the construction of materials.
 *
 * @author Grégory Van den Borre
 */
public abstract class MaterialManager {

    private static final VertexProfileList DEFAULT_VERTEX_PROFILE = new VertexProfileList(Shader.VertexDx.VS_1_1, Shader.VertexGL.ARBVP1);

    private static final FragmentProfileList DEFAULT_FRAGMENT_PROFILE = new FragmentProfileList(Shader.FragmentDx.PS_1_1, Shader.FragmentGL.ARBFP1);

    /**
     * Full constructor.
     */
     protected MaterialManager() {
        super();
        Material empty = this.createMaterial(Material.EMPTY_NAME);
        empty.getTechnique(0).getPass(0).enableColor(false);
        empty.load();

        this.createMaterial(Color.GREEN, Material.GREEN_NAME);
        this.createMaterial(Color.RED, Material.RED_NAME);
        this.createMaterial(Color.BLUE, Material.BLUE_NAME);
        this.createMaterial(Color.GRAY, Material.GRAY_NAME);
        this.createMaterial(Color.YELLOW, Material.YELLOW_NAME);
        this.createMaterial(Color.BLACK, Material.BLACK_NAME);
    }

    /**
     * Create a new Material.
     *
     * @param name Material name, must be unique.
     * @return The newly built material.
     */
    public final Material createMaterial(final String name) {
        return this.createMaterialImpl(name);
    }

    /**
     * Create a new Material with a given color to set as ambient and with a
     * random unique name.
     *
     * @param color Color to set as ambient in the fist pass of the first
     *              technique.
     * @return The newly built material.
     */
    public final Material createMaterial(final Color color) {
        return this.createMaterial(color, StringUtil.buildRandomString(color));
    }

    /**
     * Create a new Material with a given color to set as ambient.
     *
     * @param color Color to set as ambient in the fist pass of the first
     *              technique.
     * @param name  Material name, must be unique.
     * @return The newly built material.
     */
    public final Material createMaterial(final Color color, final String name) {
        final Material mat = this.createMaterial(name);
        mat.getTechnique(0).getPass(0).getUnit(0).setColorOperationEx(LayerBlendOperationEx.SOURCE1, LayerBlendSource.MANUAL, LayerBlendSource.CURRENT, color);
        mat.load();
        return mat;
    }

    /**
     * Create and load a simple Material with a random name.
     *
     * @param path File used as image for the texture.
     * @return The created and loaded texture.
     */
    public final Material loadSimpleTexture(final String path) {
        return this.loadSimpleTexture(StringUtil.buildRandomString(path), path);
    }

    /**
     * Create a blended material with 2 files.
     *
     * @param name  Material name, must be unique.
     * @param path  First file.
     * @param path2 Second file.
     * @return The created material.
     */
    public final Material createBlendMaterial(final String name, final String path, final String path2) {
        Material m = this.createMaterial(name);
        TextureUnit u = m.getTechnique(0).getPass(0).getUnit(0);
        u.setTexture(path);
        u = m.getTechnique(0).getPass(0).createUnit();
        u.setTexture(path2);
        return m;
    }

    /**
     * Create and load a simple Material with a given name.
     *
     * @param name Material unique name.
     * @param path File used as image for the texture.
     * @return The created and loaded texture.
     */
    public final Material loadSimpleTexture(final String name, final String path) {
        final Material texture = this.createMaterialImpl(name);
        texture.getTechnique(0).getPass(0).getUnit(0).setTexture(path);
        texture.load();
        return texture;
    }

    /**
     * Create and load a simple Material with a transparent image and a random
     * name.
     *
     * @param path        File used as image for the texture.
     * @param transparent Transparency type.
     * @return The created and loaded texture.
     */
    public final Material loadSimpleTexture(final String path, final Transparency transparent) {
        return this.loadSimpleTexture(StringUtil.buildRandomString(path), path, transparent);

    }

    /**
     * Create and load a simple Material with a transparent image and a given
     * name.
     *
     * @param name        Material unique name.
     * @param path        File used as image for the texture.
     * @param transparent Transparency type.
     * @return The created and loaded texture.
     */
    public final Material loadSimpleTexture(final String name, final String path, final Transparency transparent) {
        final Material texture = this.createMaterialImpl(name);
        texture.getTechnique(0).getPass(0).setTransparency(transparent);
        texture.getTechnique(0).getPass(0).getUnit(0).setTexture(path);
        texture.load();
        return texture;
    }

    /**
     * Build a simple GUI material, with alpha transparency and light disabled.
     *
     * @param path Texture file path.
     * @return The created and loaded texture.
     */
    public final Material createGuiMaterial(final String path) {
        return this.loadSimpleTexture(path, Transparency.ALPHA).disableLight();
    }

    public final Material createGuiMaterial(final String name, final String path) {
        return this.loadSimpleTexture(name, path, Transparency.ALPHA).disableLight();
    }

    /**
     * Create a new fragment (pixel) shader with 'main' as entry point and 'PS_1_1', 'ARBFP1' as profiles.
     *
     * @param name Shader name.
     * @param file File containing the shader code.
     * @return The built shader.
     */
    public final FragmentShader createFragmentShader(final String name, final String file) {
        Shader shader = this.createFragmentShaderImpl(name, file, "main", DEFAULT_FRAGMENT_PROFILE);
        return new FragmentShader(shader);
    }

    /**
     * Create a new vertex shader with 'main' as entry point and 'VS_1_1', 'ARBVP1' as profiles.
     *
     * @param name Shader name.
     * @param file File containing the shader code.
     * @return The built shader.
     */
    public VertexShader createVertexShader(final String name, final String file) {
        Shader shader = this.createVertexShaderImpl(name, file, "main", DEFAULT_VERTEX_PROFILE);
        return new VertexShader(shader);
    }

    /**
     * Create a new fragment (pixel) shader.
     *
     * @param name    Shader name.
     * @param file    File containing the shader code.
     * @param entry   Main function name.
     * @param profile Profiles to set.
     * @return The built shader.
     */
    public FragmentShader createFragmentShader(final String name, final String file, final String entry, final FragmentProfileList profile) {
        Shader shader = this.createFragmentShaderImpl(name, file, entry, profile);
        return new FragmentShader(shader);
    }

    /**
     * Create a new vertex shader.
     *
     * @param name    Shader name.
     * @param file    File containing the shader code.
     * @param entry   Main function name.
     * @param profile Profiles to set.
     * @return The built shader.
     */
    public VertexShader createVertexShader(final String name, final String file, final String entry, final VertexProfileList profile) {
        Shader shader = this.createVertexShaderImpl(name, file, entry, profile);
        return new VertexShader(shader);
    }

    /**
     * Create a shader fragment type.
     *
     * @param name    Shader name.
     * @param file    Shader file.
     * @param entry   Entry point name.
     * @param profile Profile to set.
     * @return The created shader.
     */
    protected abstract Shader createFragmentShaderImpl(String name, String file, String entry, FragmentProfileList profile);

    /**
     * Create a shader vertex type.
     *
     * @param name    Shader name.
     * @param file    Shader file.
     * @param entry   Entry point name.
     * @param profile Profile to set.
     * @return The created shader.
     */
    protected abstract Shader createVertexShaderImpl(String name, String file, String entry, VertexProfileList profile);

    protected abstract Material createMaterialImpl(String name);
}
