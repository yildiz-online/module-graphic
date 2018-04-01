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

package be.yildizgames.module.graphic.dummy;

import be.yildizgames.common.file.ResourcePath;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.GraphicEngine;
import be.yildizgames.module.graphic.GraphicWorld;
import be.yildizgames.module.graphic.SceneManager;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.material.MaterialManager;
import be.yildizgames.module.graphic.misc.SelectionRectangle;
import be.yildizgames.module.graphic.misc.Skybox;
import be.yildizgames.module.window.WindowEngine;

/**
 * dummy implementation for graphic engine.
 *
 * @author Grégory Van den Borre
 */
public final class DummyGraphicEngine extends GraphicEngine {

    /**
     * Simple constructor.
     */
    DummyGraphicEngine() {
        super();
    }

    @Override
    public void close() {
        // does nothing.
    }

    @Override
    public void update() {
        // does nothing.
    }

    @Override
    public void printScreen() {
        // does nothing.
    }

    @Override
    public void addResourcePath(final ResourcePath resourcePath) {
        // does nothing.
    }

    @Override
    public GuiFactory getGuiBuilder() {
        return null;
    }

    @Override
    public MaterialManager getMaterialManager() {
        return null;
    }

    @Override
    public Skybox createSkybox(final String name, final String path) {
        return new DummySkybox(name, path);
    }

    @Override
    public SelectionRectangle createSelectionRectangle(final Material texture, final Material texture2) {
        return null;
    }

    @Override
    public float getFPS() {
        return 0;
    }

    @Override
    public SceneManager createGraphicWorld(final String worldName, final ShadowType shadowType) {
        return null;
    }

    @Override
    public Font createFont(String name, String path, int size, Color color) {
        return new DummyFont(name, size, color);
    }

    @Override
    public GraphicWorld createWorld() {
        return new DummyClientWorld();
    }

    @Override
    public Size getScreenSize() {
        return new Size(0);
    }

    @Override
    public WindowEngine getWindowEngine() {
        return WindowEngine.getEngine();
    }
}
