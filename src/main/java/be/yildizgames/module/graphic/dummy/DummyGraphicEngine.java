/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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
import be.yildizgames.module.graphic.BaseGraphicEngine;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.GraphicWorld;
import be.yildizgames.module.graphic.SceneManager;
import be.yildizgames.module.graphic.ShadowType;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.material.MaterialManager;
import be.yildizgames.module.graphic.misc.SelectionRectangle;
import be.yildizgames.module.graphic.misc.SkyBox;
import be.yildizgames.module.window.BaseWindowEngine;
import be.yildizgames.module.window.ScreenSize;

/**
 * dummy implementation for graphic engine.
 *
 * @author Grégory Van den Borre
 */
public final class DummyGraphicEngine extends BaseGraphicEngine {

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
    public void updateImpl() {
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
    public GuiFactory getGuiFactory() {
        return new DummyGuiFactory();
    }

    @Override
    public MaterialManager getMaterialManager() {
        return null;
    }

    @Override
    public SkyBox createSkybox(final String name, final String path) {
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
        return new SceneManager() {};
    }

    @Override
    public Font createFont(String name, String path, int size, Color color) {
        return new DummyFont(name, size, color);
    }

    @Override
    public GraphicWorld createWorld() {
        return new DummyGraphicWorld();
    }

    @Override
    public ScreenSize getScreenSize() {
        return new ScreenSize(0,0);
    }

    @Override
    public BaseWindowEngine getWindowEngine() {
        return BaseWindowEngine.getEngine();
    }
}
