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
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.coordinates.Position;
import be.yildizgames.module.coordinates.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public class BaseWidgetBuilder {

    private String name = StringUtil.buildRandomString("widget");

    private Coordinates coordinates = FullCoordinates.full(FullCoordinates.ZERO.getWidth(), FullCoordinates.ZERO.getHeight(),
            FullCoordinates.ZERO.getLeft(), FullCoordinates.ZERO.getTop());

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
        this.coordinates = FullCoordinates.full(this.coordinates.getWidth(), this.coordinates.getHeight(), position);
    }

    public final void atPosition(final int x, final int y) {
        this.atPosition(FullCoordinates.position(x, y));
    }

    public final void withSize(Size size) {
        assert size != null;
        this.coordinates = FullCoordinates.full(size, this.coordinates.getLeft(), this.coordinates.getTop());
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
        this.withSize(FullCoordinates.size(width, length));
    }

    public String getName() {
        return name;
    }

    public final Coordinates getCoordinates() {
        return coordinates;
    }

    public Font getFont() {
        return this.font;
    }

    public Material getBackground() {
        return background;
    }
    
}
