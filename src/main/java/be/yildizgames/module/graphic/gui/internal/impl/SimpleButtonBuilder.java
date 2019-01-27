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

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;
import be.yildizgames.module.graphic.gui.button.Button;
import be.yildizgames.module.graphic.gui.button.ButtonAnimation;
import be.yildizgames.module.graphic.gui.button.ButtonBuilder;
import be.yildizgames.module.graphic.gui.button.ButtonMaterial;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.MouseLeftClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
class SimpleButtonBuilder implements ButtonBuilder {

    private final StandardGuiFactory builder;

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private Material material = Material.empty();

    private Material highlight = Material.empty();

    private Material inactive = Material.empty();

    private Font inactiveFont;

    private Font captionFont;

    private PositionRelativeTop captionTopAlignment = PositionRelativeTop.CENTER;

    private int captionTopDistance = 0;

    private PositionRelativeLeft captionLeftAlignment = PositionRelativeLeft.CENTER;

    private int captionLeftDistance = 0;

    private final List<ButtonAnimation> animations = new ArrayList<>();

    private TranslationKey captionTextKey;

    private String captionText;

    private MouseLeftClickListener clickListener;

    SimpleButtonBuilder(final StandardGuiFactory builder) {
        super();
        this.builder = builder;
    }

    @Override
    public SimpleButtonBuilder withName(final String name) {
        this.base.withName(name);
        return this;
    }

    @Override
    public SimpleButtonBuilder fromOther(Button button) {
        this.base.withCoordinates(button.getCoordinates());
        this.material = button.getMaterial();
        this.highlight = button.getHighlightMaterial();
        this.inactive = button.getInactiveMaterial();
        this.captionFont = button.getCaptionFont();
        this.inactiveFont = button.getInactiveFont();
        this.captionLeftAlignment = button.getCaptionHorizontalAlignment();
        this.captionLeftDistance = button.getCaptionHorizontalPadding();
        this.captionTopAlignment = button.getCaptionVerticalAlignment();
        this.captionTopDistance = button.getCaptionVerticalPadding();
        return this;
    }

    @Override
    public SimpleButtonBuilder withButtonMaterial(final ButtonMaterial m) {
        this.material = m.material;
        this.highlight = m.highlight;
        this.inactive = m.inactive;
        this.captionFont = m.font;
        this.inactiveFont = m.inactiveFont;
        return this;
    }

    @Override
    public SimpleButtonBuilder withCaptionTextAlignment(PositionRelativeTop top, final int distance) {
        this.captionTopAlignment = top;
        this.captionTopDistance = distance;
        return this;
    }

    @Override
    public SimpleButtonBuilder withCaptionTextAlignment(PositionRelativeLeft left, final int distance) {
        this.captionLeftAlignment = left;
        this.captionLeftDistance = distance;
        return this;
    }

    @Override
    public SimpleButtonBuilder withCoordinates(final BaseCoordinate coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    @Override
    public SimpleButtonBuilder atPosition(final Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public SimpleButtonBuilder atPosition(final int x, final int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public SimpleButtonBuilder withSize(final Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public SimpleButtonBuilder withSize(final int width, final int length) {
        this.base.withSize(width, length);
        return this;
    }

    @Override
    public SimpleButtonBuilder withRelativeWidth(Relative r) {
        this.base.withSize((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().height);
        return this;
    }

    @Override
    public SimpleButtonBuilder withRelativeHeight(Relative r) {
        this.base.withSize(this.base.getCoordinates().width, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public SimpleButtonBuilder atRelativeLeft(Relative r) {
        this.base.atPosition((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().top);
        return this;
    }

    @Override
    public SimpleButtonBuilder atRelativeTop(Relative r) {
        this.base.atPosition(this.base.getCoordinates().left, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public Button build(final Container container) {
        SimpleContainer c = this.builder.getSimpleContainer(container.getName());
        ButtonMaterial bm = new ButtonMaterial(this.material, this.highlight, this.inactive, this.captionFont, this.inactiveFont);
        Button result = this.builder.buildButton(this.base.getName(), this.base.getCoordinates(), bm, c);
        result.setCaptionTextLeftAlignment(this.captionLeftAlignment, this.captionLeftDistance);
        result.setCaptionTextTopAlignment(this.captionTopAlignment, this.captionTopDistance);
        Optional.ofNullable(captionTextKey).ifPresent(result::setCaptionText);
        Optional.ofNullable(captionText).ifPresent(result::setCaptionText);
        Optional.ofNullable(clickListener).ifPresent(result::addMouseLeftClickListener);
        this.animations.forEach(a -> {
            a.setElement(result);
            result.registerAnimation(a);
        });
        return result;
    }

    @Override
    public SimpleButtonBuilder animate(ButtonAnimation animation) {
        this.builder.getAnimationManager().addAnimation(animation);
        this.animations.add(animation);
        return this;
    }

    @Override
    public ButtonBuilder withCaptionText(TranslationKey key) {
        this.captionTextKey = key;
        return this;
    }

    @Override
    public ButtonBuilder withCaptionText(String text) {
        this.captionText = text;
        return this;
    }

    @Override
    public ButtonBuilder onClick(MouseLeftClickListener l) {
        this.clickListener = l;
        return this;
    }

}
