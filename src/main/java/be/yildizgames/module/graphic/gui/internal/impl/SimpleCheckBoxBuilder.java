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

import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.checkbox.CheckBox;
import be.yildizgames.module.graphic.gui.checkbox.CheckBoxAnimation;
import be.yildizgames.module.graphic.gui.checkbox.CheckBoxBuilder;
import be.yildizgames.module.graphic.gui.checkbox.CheckboxTemplate;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseWidgetBuilder;
import be.yildizgames.module.graphic.material.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grégory Van den Borre
 */
class SimpleCheckBoxBuilder implements CheckBoxBuilder {

    private final StandardGuiFactory builder;

    private final BaseWidgetBuilder base = new BaseWidgetBuilder();

    private Material hover = Material.empty();

    private Material checked = Material.empty();

    private Material checkedHover = Material.empty();

    private final List<CheckBoxAnimation> animations = new ArrayList<>();

    SimpleCheckBoxBuilder(StandardGuiFactory builder) {
        super();
        this.builder = builder;
    }

    @Override
    public SimpleCheckBoxBuilder fromTemplate(CheckboxTemplate template) {
        this.withSize(template.getSize());
        this.withBackground(template.getBackgroundMaterial());
        this.withChecked(template.getCheckedMaterial());
        this.withHover(template.getHoverMaterial());
        this.withCheckedHover(template.getCheckedHover());
        this.withFont(template.getFont());
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withName(final String name) {
        this.base.withName(name);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder atPosition(final Position position) {
        this.base.atPosition(position);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder atPosition(final int x, final int y) {
        this.base.atPosition(x, y);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withSize(final int width, final int length) {
        this.base.withSize(width, length);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withRelativeWidth(Relative r) {
        this.base.withSize((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().height);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withRelativeHeight(Relative r) {
        this.base.withSize(this.base.getCoordinates().width, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder atRelativeLeft(Relative r) {
        this.base.atPosition((int) (this.builder.getScreenSize().width * r.value), this.base.getCoordinates().top);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder atRelativeTop(Relative r) {
        this.base.atPosition(this.base.getCoordinates().left, (int) (this.builder.getScreenSize().height * r.value));
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withBackground(final Material background) {
        this.base.withBackground(background);
        if(this.hover.equals(Material.empty())) {
            this.hover = background;
        }
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withHover(final Material hover) {
        this.hover = hover;
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withCheckedHover(final Material hover) {
        this.checkedHover = hover;
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withChecked(final Material checked) {
        this.checked = checked;
        if(this.checkedHover.equals(Material.empty())) {
            this.checkedHover = checked;
        }
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withFont(final Font font) {
        this.base.withFont(font);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withCoordinates(final BaseCoordinate coordinates) {
        this.base.withCoordinates(coordinates);
        return this;
    }

    @Override
    public SimpleCheckBoxBuilder withSize(final Size size) {
        this.base.withSize(size);
        return this;
    }

    @Override
    public CheckBox build(Container container) {
        SimpleContainer c = this.builder.getSimpleContainer(container.getName());
        CheckBox result = this.builder.buildCheckBox(
                this.base.getName(),
                this.base.getCoordinates(),
                this.base.getBackground(),
                this.hover,
                this.checked,
                this.checkedHover,
                this.base.getFont(), c);
        this.animations.forEach(a -> {
            a.setElement(result);
            result.registerAnimation(a);
        });
        return result;
    }

    @Override
    public SimpleCheckBoxBuilder animate(CheckBoxAnimation animation) {
        this.builder.getAnimationManager().addAnimation(animation);
        this.animations.add(animation);
        return this;
    }
}
