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

package be.yildiz.module.graphic.gui.checkbox;

import be.yildiz.common.Coordinates;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.CheckBox;
import be.yildiz.module.graphic.gui.GuiBuilder;
import be.yildiz.module.graphic.gui.GuiContainer;

/**
 * @author Grégory Van den Borre
 */
public class CheckBoxBuilder {

    private final GuiBuilder builder;

    private String name = StringUtil.buildRandomString("checkbox");
    private Coordinates coordinates = Coordinates.ZERO;
    private Material background = Material.empty();
    private Material hover = Material.empty();
    private Material checked = Material.empty();
    private Font font = Font.getDefault();

    public CheckBoxBuilder(GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    public CheckBoxBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public CheckBoxBuilder withCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public CheckBoxBuilder withBackground(final Material background) {
        this.background = background;
        return this;
    }

    public CheckBoxBuilder withHover(final Material hover) {
        this.hover = hover;
        return this;
    }

    public CheckBoxBuilder withChecked(final Material checked) {
        this.checked = checked;
        return this;
    }

    public CheckBoxBuilder withFont(final Font font) {
        this.font = font;
        return this;
    }

    public CheckBox build(GuiContainer container) {
        return this.builder.buildCheckBox(this.name, this.coordinates, this.background, this.hover, this.checked, this.font, container);
    }
}
