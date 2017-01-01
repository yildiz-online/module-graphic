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

import be.yildiz.common.Color;
import be.yildiz.common.Size;
import be.yildiz.common.resource.FileResource.FileType;
import be.yildiz.module.graphic.Shader.FragmentProfileList;
import be.yildiz.module.graphic.Shader.VertexProfileList;
import be.yildiz.module.graphic.gui.GuiBuilder;
import be.yildiz.module.window.WindowEngine;

/**
 * Behavior for a graphic engine. Specification: The engine must be able to load resources, show a sky box, show the GUI, move and rotate 3d meshes.
 *
 * @author Grégory Van den Borre
 */
public interface GraphicEngine extends FpsProvider {

    /**
     * Free resources used by the engine.
     */
    void close();

    /**
     * Render one frame.
     */
    void update();

    /**
     * Print the current rendering frame and save it in a file.
     */
    void printScreen();

    /**
     * Add a folder to use to load resources.
     *
     * @param name Name of the resource group.
     * @param path Path of the folder to scan.
     * @param type Resource type.
     */
    void addResourcePath(String name, String path, FileType type);

    /**
     * Provide the module responsible to build GUI elements.
     *
     * @return The GuiBuilder.
     */
    GuiBuilder getGuiBuilder();

    /**
     * Build a new Material.
     *
     * @param name Material name, must be unique.
     * @return The newly built material.
     */
    Material createMaterial(String name);

    /**
     * Create a sky box.
     *
     * @param name Sky box unique name.
     * @param path Path to images to use.
     * @return The created object.
     */
    Skybox createSkybox(String name, String path);

    /**
     * Create a new Selection Rectangle, only one can be used at a time, if more
     * than one is created, an exception is thrown.
     *
     * @param border Material to set.
     * @param inner  Inner material.
     * @return The created object.
     */
    SelectionRectangle createSelectionRectangle(Material border, Material inner);

    /**
     * Create a new font to use to print text.
     *
     * @param name  Name of the font, must be unique, used to retrieve it from the
     *              registerer.
     * @param path  Path to the font file to load.
     * @param size  Size of the font.
     * @param color Font color.
     * @return The newly built font.
     */
    Font createFont(String name, String path, int size, Color color);

    /**
     * Create a new font to use to print text.
     *
     * @param name Name of the font, must be unique, used to retrieve it from the registerer.
     * @param path Path to the font file to load.
     * @param size Size of the font.
     * @return The newly built font.
     */
    default Font createFont(final String name, final String path, final int size) {
        return this.createFont(name, path, size, Color.WHITE);
    }

    /**
     * Create a new graphic world.
     *
     * @param worldName  Unique name to give to the world.
     * @param shadowType Type of the shadow to use.
     * @return The created GraphicWorld.
     */
    GraphicWorld createGraphicWorld(String worldName, ShadowType shadowType);

    /**
     * Create a shader fragment type.
     *
     * @param name    Shader name.
     * @param file    Shader file.
     * @param entry   Entry point name.
     * @param profile Profile to set.
     * @return The created shader.
     */
    Shader createFragmentShader(String name, String file, String entry, FragmentProfileList profile);

    /**
     * Create a shader vertex type.
     *
     * @param name    Shader name.
     * @param file    Shader file.
     * @param entry   Entry point name.
     * @param profile Profile to set.
     * @return The created shader.
     */
    Shader createVertexShader(String name, String file, String entry, VertexProfileList profile);

    ClientWorld createWorld();

    Size getScreenSize();

    WindowEngine getWindowEngine();

    /**
     * Possible type of shadows.
     *
     * @author Van Den Borre Grégory
     */
    enum ShadowType {
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
}
