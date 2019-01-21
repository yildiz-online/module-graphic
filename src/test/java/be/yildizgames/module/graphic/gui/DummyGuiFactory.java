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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.element.AbstractIconElement;
import be.yildizgames.module.graphic.gui.element.AbstractTextElement;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.gui.internal.impl.StandardGuiFactory;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.material.MaterialEffect;
import be.yildizgames.module.graphic.material.MaterialEffect.EffectType;
import be.yildizgames.module.graphic.material.MaterialTechnique;
import be.yildizgames.module.window.ScreenSize;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author Grégory Van den Borre
 */
public class DummyGuiFactory extends StandardGuiFactory {

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
            //Empty method.

        }

        @Override
        protected void receiveShadowImpl(boolean receive) {
            //Empty method.

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
            return new MaterialEffect(null, Duration.ofMillis(time)) {
                @Override
                protected void executeImpl(Material material) {

                }
            };
        }
    };

    public DummyGuiFactory() {
        super(new ScreenSize(1024, 768));
        defaultFont.load();
    }

    @Override
    protected AbstractIconElement buildIconElement(String name, BaseCoordinate coordinates, Material material, Container container) {
        return new DummyIconElement(name, coordinates, material);
    }

    @Override
    protected AbstractTextElement buildTextElement(BaseCoordinate coordinates, Font font, Container container) {
        return new DummyTextElement(coordinates, font);
    }

    @Override
    public SimpleContainer buildContainerElement(String name, BaseCoordinate coordinates, Material background) {
        return new DummyGuiContainer(name, coordinates, background);
    }


    @Override
    public SimpleContainer buildContainerElement(String name, BaseCoordinate coordinates, Material background, Container parent, boolean widget) {
        return new DummyGuiContainer(name, coordinates, background, parent, widget);
    }
}
