/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public class BaseWidgetBuilder {

    private String name = StringUtil.buildRandomString("widget");

    private BaseCoordinate coordinates = new Coordinates(BaseCoordinate.ZERO.width, BaseCoordinate.ZERO.height,
            BaseCoordinate.ZERO.left, BaseCoordinate.ZERO.top);

    private Font font;

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
        this.coordinates = new Coordinates(this.coordinates.width, this.coordinates.height, position);
    }

    public final void atPosition(final int x, final int y) {
        this.atPosition(new Position(x, y));
    }

    public final void withSize(Size size) {
        assert size != null;
        this.coordinates = new Coordinates(size, this.coordinates.left, this.coordinates.top);
    }

    public final void withCoordinates(BaseCoordinate coordinates) {
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

    public BaseCoordinate getCoordinates() {
        return coordinates;
    }

    public Font getFont() {
        return this.font;
    }

    public Material getBackground() {
        return background;
    }
    
}
