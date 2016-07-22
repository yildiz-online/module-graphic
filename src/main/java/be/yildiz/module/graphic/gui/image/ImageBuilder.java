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

package be.yildiz.module.graphic.gui.image;

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.GuiBuilder;
import be.yildiz.module.graphic.gui.GuiContainer;
import be.yildiz.module.graphic.gui.Image;

/**
 * @author Grégory Van den Borre
 */
public class ImageBuilder {

    private final GuiBuilder builder;

    private String name = StringUtil.buildRandomString("image");

    private Coordinates coordinates = Coordinates.ZERO;

    private Material background = Material.empty();


    public ImageBuilder(final GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    public ImageBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public ImageBuilder atPosition(final Position position) {
        this.coordinates = new Coordinates(this.coordinates.getSize(), position);
        return this;
    }

    public ImageBuilder atPosition(final int x, final int y) {
        return this.atPosition(new Position(x, y));
    }

    public ImageBuilder withSize(final Size size) {
        this.coordinates = new Coordinates(size, this.coordinates.left, this.coordinates.top);
        return  this;
    }

    public ImageBuilder withBackground(final Material background) {
        this.background = background;
        return this;
    }

    public Image build(final GuiContainer container) {
        return this.builder.buildImage(this.name, this.coordinates, this.background, container);
    }

    public ImageBuilder withCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }
}
