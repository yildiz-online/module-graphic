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

package be.yildizgames.module.graphic;

import be.yildizgames.common.file.ResourcePath;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.dummy.DummyGraphicEngineProvider;
import be.yildizgames.module.graphic.gui.GuiEventManager;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.gui.internal.EventBubblingDispatcher;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.material.MaterialManager;
import be.yildizgames.module.graphic.misc.SelectionRectangle;
import be.yildizgames.module.graphic.misc.Skybox;
import be.yildizgames.module.window.ScreenSize;
import be.yildizgames.module.window.WindowEngine;

import java.util.ServiceLoader;

/**
 * Behavior for a graphic engine. Specification: The engine must be able to load resources, show a sky box, show the GUI, move and rotate 3d meshes.
 *
 * @author Grégory Van den Borre
 */
public abstract class GraphicEngine implements FpsProvider {

    private final GuiEventManager eventManager = new EventBubblingDispatcher();

    public static GraphicEngine getEngine(WindowEngine windowEngine) {
        ServiceLoader<GraphicEngineProvider> provider = ServiceLoader.load(GraphicEngineProvider.class);
        return provider.findFirst().orElseGet(DummyGraphicEngineProvider::new).getEngine(windowEngine);
    }

    public static GraphicEngine getEngine() {
        ServiceLoader<GraphicEngineProvider> provider = ServiceLoader.load(GraphicEngineProvider.class);
        return provider.findFirst().orElseGet(DummyGraphicEngineProvider::new).getEngine(WindowEngine.getEngine());
    }

    public final GuiEventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Free resources used by the engine.
     */
    public abstract void close();

    /**
     * Render one frame.
     */
    public abstract void update();

    /**
     * Print the current rendering frame and save it in a file.
     */
    public abstract void printScreen();

    /**
     * Add a folder to use to load resources.
     *
     * @param resource Data for the resources.
     */
    public abstract void addResourcePath(ResourcePath resource);

    /**
     * Provide the module responsible to build GUI elements.
     *
     * @return The GuiBuilder.
     */
    public abstract GuiFactory getGuiBuilder();

    public abstract MaterialManager getMaterialManager();

    /**
     * Create a sky box.
     *
     * @param name Sky box unique name.
     * @param path Path to images to use.
     * @return The created object.
     */
    public abstract Skybox createSkybox(String name, String path);

    /**
     * Create a new Selection Rectangle, only one can be used at a time, if more
     * than one is created, an exception is thrown.
     *
     * @param border Material to set.
     * @param inner  Inner material.
     * @return The created object.
     */
    public abstract SelectionRectangle createSelectionRectangle(Material border, Material inner);

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
    public abstract Font createFont(String name, String path, int size, Color color);

    /**
     * Create a new font to use to print text.
     *
     * @param name Name of the font, must be unique, used to retrieve it from the registerer.
     * @param path Path to the font file to load.
     * @param size Size of the font.
     * @return The newly built font.
     */
    public final Font createFont(final String name, final String path, final int size) {
        return this.createFont(name, path, size, Color.WHITE);
    }

    /**
     * Create a new graphic world.
     *
     * @param worldName  Unique name to give to the world.
     * @param shadowType Type of the shadow to use.
     * @return The created GraphicWorld.
     */
    public abstract SceneManager createGraphicWorld(String worldName, ShadowType shadowType);

    public abstract GraphicWorld createWorld();

    public abstract ScreenSize getScreenSize();

    public abstract WindowEngine getWindowEngine();

    /**
     * Possible type of shadows.
     *
     * @author Van Den Borre Grégory
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
}
