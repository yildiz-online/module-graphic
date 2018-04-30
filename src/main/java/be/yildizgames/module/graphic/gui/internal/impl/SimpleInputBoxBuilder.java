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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.inputbox.InputBox;
import be.yildizgames.module.graphic.gui.inputbox.InputBoxBuilder;
import be.yildizgames.module.graphic.gui.inputbox.InputBoxTemplate;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
class SimpleInputBoxBuilder implements InputBoxBuilder {

    private final SimpleGuiFactory builder;

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private Font captionFont;

    private Material backgroundHighlight;

    private Material cursorMaterial;

    SimpleInputBoxBuilder(SimpleGuiFactory simpleGuiFactory) {
        this.builder = simpleGuiFactory;
    }

    @Override
    public SimpleInputBoxBuilder withName(String name) {
        this.base.withName(name);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder withCoordinates(Coordinates coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder atPosition(Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder atPosition(int x, int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder withSize(Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder withSize(int width, int length) {
        this.base.withSize(width, length);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder withRelativeWidth(Relative r) {
        this.base.withSize((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().height);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder withRelativeHeight(Relative r) {
        this.base.withSize(this.base.getCoordinates().width, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public SimpleInputBoxBuilder atRelativeLeft(Relative r) {
        this.base.atPosition((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().top);
        return this;
    }

    @Override
    public SimpleInputBoxBuilder atRelativeTop(Relative r) {
        this.base.atPosition(this.base.getCoordinates().left, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public InputBoxBuilder withCaptionFont(Font caption) {
        this.captionFont = caption;
        return this;
    }

    @Override
    public InputBoxBuilder withBackgroundHighlight(Material m) {
        this.backgroundHighlight = m;
        return this;
    }

    @Override
    public InputBoxBuilder withCursor(Material m) {
        this.cursorMaterial = m;
        return this;
    }

    @Override
    public InputBox build(Container container) {
        return this.builder.buildInputBox(
                this.base.getName(),
                this.base.getCoordinates(),
                this.base.getFont(),
                this.captionFont,
                this.base.getBackground(),
                this.backgroundHighlight,
                this.cursorMaterial,
                container);
    }

    @Override
    public InputBoxBuilder withFont(Font font) {
        this.base.withFont(font);
        return this;
    }

    @Override
    public InputBoxBuilder withBackground(Material material) {
        this.base.withBackground(material);
        return this;
    }

    @Override
    public InputBoxBuilder fromTemplate(InputBoxTemplate template) {
        return this.withSize(template.getSize())
                .withBackground(template.getMaterial())
                .withCursor(template.getCursor())
                .withFont(template.getFont())
                .withCaptionFont(template.getCaptionFont())
                .withFont(template.getFont());
    }
}
