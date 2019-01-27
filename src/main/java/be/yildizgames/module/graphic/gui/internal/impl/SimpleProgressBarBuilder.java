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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.time.TimeFormatter;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.image.Image;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBar;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarBuilder;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarTimer;
import be.yildizgames.module.graphic.material.Material;

import java.time.Duration;

/**
 * @author Grégory Van den Borre
 */
class SimpleProgressBarBuilder implements ProgressBarBuilder {

    private final BaseWidgetBuilder baseWidgetBuilder = new BaseWidgetBuilder();

    private final StandardGuiFactory factory;

    SimpleProgressBarBuilder(StandardGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public final ProgressBarBuilder atPosition(int left, int top) {
        this.baseWidgetBuilder.atPosition(left, top);
        return this;
    }

    @Override
    public ProgressBarBuilder withName(String name) {
        this.baseWidgetBuilder.withName(name);
        return this;
    }

    @Override
    public ProgressBarBuilder withCoordinates(BaseCoordinate coordinates) {
        this.baseWidgetBuilder.withCoordinates(coordinates);
        return this;
    }

    @Override
    public final ProgressBarBuilder atPosition(Position position) {
        this.baseWidgetBuilder.atPosition(position);
        return this;
    }

    @Override
    public final ProgressBarBuilder withSize(int width, int heigh) {
        this.baseWidgetBuilder.withSize(width, heigh);
        return this;
    }

    @Override
    public ProgressBarBuilder withRelativeWidth(Relative r) {
        this.baseWidgetBuilder.withSize((int) (this.factory.getScreenSize().width * r.value), this.baseWidgetBuilder.getCoordinates().height);
        return null;
    }

    @Override
    public ProgressBarBuilder withRelativeHeight(Relative r) {
        this.baseWidgetBuilder.withSize(this.baseWidgetBuilder.getCoordinates().width, (int) (this.factory.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public ProgressBarBuilder atRelativeLeft(Relative r) {
        this.baseWidgetBuilder.atPosition((int) (this.factory.getScreenSize().width * r.value), this.baseWidgetBuilder.getCoordinates().top);
        return this;
    }

    @Override
    public ProgressBarBuilder atRelativeTop(Relative r) {
        this.baseWidgetBuilder.atPosition(this.baseWidgetBuilder.getCoordinates().left, (int) (this.factory.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public final ProgressBarBuilder withSize(Size size) {
        this.baseWidgetBuilder.withSize(size);
        return this;
    }

    @Override
    public ProgressBar build(Container parent, Material empty, Material filled) {
        SimpleContainer c = this.factory.getSimpleContainer(parent.getName());
        return this.factory.buildProgressBar(this.baseWidgetBuilder.getName(), this.baseWidgetBuilder.getCoordinates(), empty, filled, c);
    }

    @Override
    public ProgressBarTimer buildTimer(Container parent, Material empty, Material filled, Duration duration, TimeFormatter formatter, Font font) {
        SimpleContainer c = this.factory.getSimpleContainer(parent.getName());
        return this.factory.buildProgressBar(this.baseWidgetBuilder.getCoordinates(), empty, filled, font, duration, formatter, c);
    }

    @Override
    public ProgressBar buildRectangle(Container parent, Material border, Material content) {
        SimpleContainer c = this.factory.getSimpleContainer(parent.getName());
        Image borderImage = this.factory.buildImage(StringUtil.buildRandomString("image"), this.baseWidgetBuilder.getCoordinates(), border, c);
        Image contentImage = this.factory.buildImage(StringUtil.buildRandomString("image"), this.baseWidgetBuilder.getCoordinates(), content, c);
        return this.factory.buildProgressBar(this.baseWidgetBuilder.getName(), borderImage, contentImage, c);
    }
}
