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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.BaseCoordinate;
import be.yildiz.common.Color;
import be.yildiz.common.Size;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.MaterialEffect;
import be.yildiz.module.graphic.MaterialEffect.EffectType;
import be.yildiz.module.graphic.MaterialTechnique;

import java.util.Arrays;

/**
 * @author Grégory Van den Borre
 */
public class DummyGuiBuilder extends GuiBuilder {

    public static final Font defaultFont = new Font("default", 0, Color.BLACK) {

        @Override
        protected void loadImpl() {
            float[] widthArray = new float[256];
            Arrays.fill(widthArray, 1.0f);
            this.setCharWidth(widthArray);
        }
    };

    public static final Material empty = new Material(Material.EMPTY_NAME) {

        @Override
        protected void loadImpl() {
            // TODO Auto-generated method stub

        }

        @Override
        protected void receiveShadowImpl(boolean receive) {
            // TODO Auto-generated method stub

        }

        @Override
        protected MaterialTechnique createTechniqueImpl(int techniqueIndex) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected Material copyImpl(String name) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public MaterialEffect addEffect(EffectType type, long time) {
            // TODO Auto-generated method stub
            return null;
        }
    };

    public DummyGuiBuilder() {
        super(new Size(1024, 768));
        defaultFont.load();
    }

    @Override
    protected AbstractIconElement buildIconElement(String name, BaseCoordinate coordinates, Material material, GuiContainer container) {
        return new DummyIconElement(name, coordinates, material);
    }

    @Override
    protected AbstractTextElement buildTextElement(BaseCoordinate coordinates, Font font, GuiContainer container) {
        return new DummyTextElement(coordinates, font);
    }

    @Override
    public GuiContainer buildContainerElement(String name, BaseCoordinate coordinates, Material background) {
        return new DummyGuiContainer(name, coordinates, background);
    }

    @Override
    public GuiContainer buildContainerElement(String name, BaseCoordinate coordinates, Material background, GuiContainer parent, boolean widget) {
        return new DummyGuiContainer(name, coordinates, background, parent, widget);
    }
}
