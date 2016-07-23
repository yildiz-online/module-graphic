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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import lombok.Getter;

/**
 * @author Grégory Van den Borre
 */
public class BaseWidgetBuilder {

    @Getter
    private String name = StringUtil.buildRandomString("widget");

    @Getter
    private Coordinates coordinates = Coordinates.ZERO;

    @Getter
    private Font font = Font.getDefault();

    @Getter
    private Material background = Material.empty();


    public BaseWidgetBuilder() {
        super();
    }

    public final void withName(final String name) {
        this.name = name;
    }

    public final void atPosition(final Position position) {
        this.coordinates = new Coordinates(this.coordinates.getSize(), position);
    }

    public final void atPosition(final int x, final int y) {
        this.atPosition(new Position(x, y));
    }

    public final void withSize(Size size) {
        this.coordinates = new Coordinates(size, this.coordinates.left, this.coordinates.top);
    }

    public final void withCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public final void withFont(final Font font) {
        this.font = font;
    }

    public void withBackground(final Material background) {
        this.background = background;
    }

    public void withSize(int width, int length) {
        this.withSize(new Size(width, length));
    }
}
