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
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.graphic.dummy.DummyGraphicEngineProvider;
import be.yildizgames.module.graphic.gui.GuiEventManager;
import be.yildizgames.module.graphic.gui.internal.EventBubblingDispatcher;
import be.yildizgames.module.window.BaseWindowEngine;

import java.util.ServiceLoader;

/**
 * Behavior for a graphic engine. Specification: The engine must be able to load resources, show a sky box, show the GUI, move and rotate 3d meshes.
 *
 * @author Grégory Van den Borre
 */
public abstract class BaseGraphicEngine implements GraphicEngine, FpsProvider {

    private final GuiEventManager eventManager = new EventBubblingDispatcher();

    public static BaseGraphicEngine getEngine(BaseWindowEngine windowEngine) {
        ServiceLoader<GraphicEngineProvider> provider = ServiceLoader.load(GraphicEngineProvider.class);
        return provider.findFirst().orElseGet(DummyGraphicEngineProvider::new).getEngine(windowEngine);
    }

    public static BaseGraphicEngine getEngine() {
        ServiceLoader<GraphicEngineProvider> provider = ServiceLoader.load(GraphicEngineProvider.class);
        return provider.findFirst().orElseGet(DummyGraphicEngineProvider::new).getEngine(BaseWindowEngine.getEngine());
    }

    @Override
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
     * Add a folder to use to load resources.
     *
     * @param resource Data for the resources.
     */
    public abstract void addResourcePath(ResourcePath resource);

    @Override
    public final Font createFont(final String name, final String path, final int size) {
        return this.createFont(name, path, size, Color.WHITE);
    }

    @Override
    public final Font createFont(final String name, final String path, final Relative size, final Color color) {
        return this.createFont(name, path, (int)(size.of(this.getScreenSize().height)), color);
    }

    @Override
    public final Font createFont(final String path, final int size, final Color color) {
        return this.createFont(StringUtil.buildRandomString("font"), path, size, color);
    }

    @Override
    public final Font createFont(final String path, final Relative size, final Color color) {
        return this.createFont(StringUtil.buildRandomString("font"), path, (int)(size.of(this.getScreenSize().height)), color);
    }

    @Override
    public final Font createFont(final String name, final String path, final Relative size) {
        return this.createFont(name, path, (int)(size.of(this.getScreenSize().height)));
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

    public abstract BaseWindowEngine getWindowEngine();
}
