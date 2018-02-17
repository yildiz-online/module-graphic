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

package be.yildizgames.module.graphic.gui.image;

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.gui.GuiBuilder;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.WidgetBuilder;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.material.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class ImageBuilder implements WidgetBuilder<ImageBuilder> {

    private final GuiBuilder builder;

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private final List<ImageAnimation> animations = new ArrayList<>();


    public ImageBuilder(final GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    @Override
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

    @Override
    public ImageBuilder withRelativeWidth(Relative r) {
        this.base.withSize((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().height);
        return this;
    }

    @Override
    public ImageBuilder withRelativeHeight(Relative r) {
        this.base.withSize(this.base.getCoordinates().width, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public ImageBuilder atRelativeLeft(Relative r) {
        this.base.atPosition((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().top);
        return this;
    }

    @Override
    public ImageBuilder atRelativeTop(Relative r) {
        this.base.atPosition(this.base.getCoordinates().left, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    public ImageBuilder withBackground(final Material background) {
        this.base.withBackground(background);
        return this;
    }

    public Image build(final Container container) {
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
