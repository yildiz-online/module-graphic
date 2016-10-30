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
import be.yildiz.common.collections.Lists;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.*;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class ImageBuilder implements WidgetBuilder<ImageBuilder>{

    private final GuiBuilder builder;

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private final List<ImageAnimation> animations = Lists.newList();


    public ImageBuilder(final GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    public ImageBuilder withName(final String name) {
        this.base.withName(name);
        return this;
    }

    @Override
    public ImageBuilder withCoordinates(Coordinates coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    @Override
    public ImageBuilder atPosition(Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public ImageBuilder atPosition(int x, int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public ImageBuilder withSize(Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public ImageBuilder withSize(int width, int length) {
        this.base.withSize(width, length);
        return this;
    }

    public ImageBuilder withBackground(final Material background) {
        this.base.withBackground(background);
        return this;
    }

    public Image build(final GuiContainer container) {
        Image result = this.builder.buildImage(this.base.getName(), this.base.getCoordinates(), this.base.getBackground(), container);
        this.animations.forEach(a -> {
            a.setElement(result);
            result.registerAnimation(a);
        });
        return result;
    }

    public ImageBuilder animate(ImageAnimation animation) {
        this.builder.getAnimationManager().addAnimation(animation);
        this.animations.add(animation);
        return this;
    }
}
