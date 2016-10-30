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

package be.yildiz.module.graphic.gui.textarea;

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.collections.Lists;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.*;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class TextAreaBuilder implements WidgetBuilder<TextAreaBuilder> {

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private final GuiBuilder builder;

    private int padding = 0;

    private final List<TextAreaAnimation> animations = Lists.newList();

    public TextAreaBuilder(GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    public TextAreaBuilder withName(final String name) {
        this.base.withName(name);
        return this;
    }

    public TextAreaBuilder withFont(final Font font) {
        this.base.withFont(font);
        return this;
    }

    public TextAreaBuilder withBackground(final Material background) {
        this.base.withBackground(background);
        return this;
    }

    @Override
    public TextAreaBuilder atPosition(final Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public TextAreaBuilder atPosition(final int x, final int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public TextAreaBuilder withSize(final int width, final int length) {
        this.base.withSize(width, length);
        return this;
    }

    @Override
    public TextAreaBuilder withSize(Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public TextAreaBuilder withCoordinates(Coordinates coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    public TextAreaBuilder withPadding(final int padding) {
        this.padding = padding;
        return this;
    }

    public TextArea build(final GuiContainer container) {
        return this.builder.buildTextArea(this.base.getName(), this.base.getCoordinates(), this.base.getFont(), this.base.getBackground(), this.padding, container);
    }

    public TextAreaBuilder animate(TextAreaAnimation animation) {
        this.builder.getAnimationManager().addAnimation(animation);
        this.animations.add(animation);
        return this;
    }
}
