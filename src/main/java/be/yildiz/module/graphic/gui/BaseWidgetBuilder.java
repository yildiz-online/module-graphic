/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.graphic.gui;

import be.yildiz.common.BaseCoordinate;
import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;

/**
 * @author Grégory Van den Borre
 */
public class BaseWidgetBuilder {

    private String name = StringUtil.buildRandomString("widget");

    private Coordinates coordinates = new Coordinates(BaseCoordinate.ZERO.width, BaseCoordinate.ZERO.height,
            BaseCoordinate.ZERO.left, BaseCoordinate.ZERO.top);

    private Font font = Font.getDefault();

    private Material background = Material.empty();


    public BaseWidgetBuilder() {
        super();
    }

    public final void withName(final String name) {
        assert name != null;
        this.name = name;
    }

    public final void atPosition(final Position position) {
        assert position != null;
        this.coordinates = new Coordinates(this.coordinates.getSize(), position);
    }

    public final void atPosition(final int x, final int y) {
        this.atPosition(new Position(x, y));
    }

    public final void withSize(Size size) {
        assert size != null;
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

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Font getFont() {
        return font;
    }

    public Material getBackground() {
        return background;
    }
}
