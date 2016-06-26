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
import be.yildiz.common.Size;
import be.yildiz.common.resource.FileResource.FileType;
import be.yildiz.module.graphic.Shader.FragmentProfileList;
import be.yildiz.module.graphic.Shader.VertexProfileList;
import be.yildiz.module.graphic.gui.GuiBuilder;

/**
 * dummy implementation for graphic engine.
 *
 * @author Grégory Van den Borre
 */
public final class DummyGraphicEngine implements GraphicEngine {

    /**
     * Simple constructor.
     */
    public DummyGraphicEngine() {
        super();
    }

    @Override
    public void close() {
    }

    @Override
    public void update() {
    }

    @Override
    public void printScreen() {
    }

    @Override
    public void addResourcePath(final String name, final String path, final FileType type) {
    }

    @Override
    public GuiBuilder getGuiBuilder() {
        return null;
    }

    @Override
    public Material createMaterial(final String name) {
        return null;
    }

    @Override
    public Skybox createSkybox(final String name, final String path) {
        return null;
    }

    @Override
    public SelectionRectangle createSelectionRectangle(final Material texture, final Material texture2) {
        return null;
    }

    @Override
    public Font createFont(final String name, final String path, final int size) {
        return null;
    }

    @Override
    public float getFPS() {
        return 0;
    }

    @Override
    public GraphicWorld createGraphicWorld(final String worldName, final ShadowType shadowType) {
        return null;
    }

    @Override
    public Shader createFragmentShader(final String name, final String file, final String entry, final FragmentProfileList profile) {
        return null;
    }

    @Override
    public Shader createVertexShader(final String name, final String file, final String entry, final VertexProfileList profile) {
        return null;
    }

    @Override
    public Font createFont(String name, String path, int size, Color color) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClientWorld createWorld() {
        return new DummyClientWorld();
    }

    @Override
    public Size getScreenSize() {
        return new Size(0);
    }
}
