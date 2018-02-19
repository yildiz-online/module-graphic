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

package be.yildizgames.module.graphic.gui.textline;

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.gui.internal.WidgetBuilder;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleGuiFactory;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleTextLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class TextLineBuilder implements WidgetBuilder<TextLineBuilder> {

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private final SimpleGuiFactory builder;

    private final List<TextAnimation> animations = new ArrayList<>();


    public TextLineBuilder(SimpleGuiFactory builder) {
        super();
        this.builder = builder;
    }

    @Override
    public TextLineBuilder withName(final String name) {
        this.base.withName(name);
        return this;
    }

    public TextLineBuilder withFont(final Font font) {
        this.base.withFont(font);
        return this;
    }

    @Override
    public TextLineBuilder atPosition(final Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public TextLineBuilder atPosition(final int x, final int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public TextLineBuilder withSize(final int width, final int length) {
        this.base.withSize(width, length);
        return this;
    }

    @Override
    public TextLineBuilder withSize(Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public TextLineBuilder withRelativeWidth(Relative r) {
        this.base.withSize((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().height);
        return this;
    }

    @Override
    public TextLineBuilder withRelativeHeight(Relative r) {
        this.base.withSize(this.base.getCoordinates().width, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public TextLineBuilder atRelativeLeft(Relative r) {
        this.base.atPosition((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().top);
        return this;
    }

    @Override
    public TextLineBuilder atRelativeTop(Relative r) {
        this.base.atPosition(this.base.getCoordinates().left, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public TextLineBuilder withCoordinates(Coordinates coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    public TextLine build(final SimpleContainer container) {
        SimpleContainer c = this.builder.getSimpleContainer(container.getName());
        SimpleTextLine result = this.builder.buildTextLine(this.base.getName(), this.base.getCoordinates(), this.base.getFont(), c);
        this.animations.forEach(a -> {
            a.setElement(result);
            result.registerAnimation(a);
        });
        return result;
    }

    public TextLineBuilder animate(TextAnimation animation) {
        this.builder.getAnimationManager().addAnimation(animation);
        this.animations.add(animation);
        return this;
    }
}