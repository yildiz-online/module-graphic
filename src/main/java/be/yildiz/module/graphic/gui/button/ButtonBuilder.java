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

package be.yildiz.module.graphic.gui.button;

import be.yildiz.common.Coordinates;
import be.yildiz.common.Position;
import be.yildiz.common.Size;
import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.graphic.gui.*;
import lombok.NonNull;

import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public class ButtonBuilder {

    private final GuiBuilder builder;

    private String name = StringUtil.buildRandomString("button");

    private ButtonMaterial material = new ButtonMaterial(Material.empty(), Material.empty(), Font.getDefault());

    private Coordinates coordinates = Coordinates.ZERO;

    private Element.PositionRelativeTop captionTopAlignment= Element.PositionRelativeTop.CENTER;

    private int captionTopDistance = 0;

    private Element.PositionRelativeLeft captionLeftAlignment = Element.PositionRelativeLeft.CENTER;

    private int captionLeftDistance = 0;

    public ButtonBuilder(final GuiBuilder builder) {
        super();
        this.builder = builder;
    }

    public ButtonBuilder withCoordinates(final Coordinates c) {
        this.coordinates = c;
        return this;
    }

    public ButtonBuilder withSize(final Size size) {
        this.coordinates = new Coordinates(size, this.coordinates.left, this.coordinates.top);
        return this;
    }

    /**
     * Provide a position to the button.
     * @param position Button position.
     * @return This object for chaining.
     * @throws NullPointerException if position is null.
     */
    public ButtonBuilder atPosition(final Position position) {
        this.coordinates = new Coordinates(this.coordinates.getSize(), position);
        return this;
    }

    /**
     * Provide a position to the button.
     * @param x Button left position.
     * @param y Button top position.
     * @return This object for chaining.
     * @throws NullPointerException if position is null.
     */
    public ButtonBuilder atPosition(final int x, final int y) {
        return this.atPosition(new Position(x, y));
    }

    public ButtonBuilder withMaterial(final Material m) {
        this.material = new ButtonMaterial(m, this.material.highlight, this.material.inactive, this.material.font);
        return this;
    }

    public ButtonBuilder withHighlightMaterial(final Material m) {
        this.material = new ButtonMaterial(this.material.material, m, this.material.inactive, this.material.font);
        return this;
    }

    public ButtonBuilder withInactiveMaterial(final Material m) {
        this.material = new ButtonMaterial(this.material.material, this.material.highlight, m, this.material.font);
        return this;
    }

    public ButtonBuilder withButtonMaterial(final ButtonMaterial m) {
        this.material = m;
        return this;
    }

    /**
     * Provide a font to the button.
     * @param font Button font.
     * @return This object for chaining.
     * @throws NullPointerException if font is null.
     */
    public ButtonBuilder withFont(final Font font) {
        this.material = new ButtonMaterial(this.material.material, this.material.highlight, this.material.inactive, Optional.of(font));
        return this;
    }

    /**
     * Provide a name to the button.
     * @param name Button unique name.
     * @return This object for chaining.
     * @throws NullPointerException if name is null.
     */
    public ButtonBuilder withName(@NonNull final String name) {
        this.name = name;
        return this;
    }

    public ButtonBuilder fromOther(final Button b) {
        this.material = new ButtonMaterial(b.getMaterial(), b.getHighlightMaterial(), b.getInactiveMaterial(), Optional.of(b.getCaptionFont()));
        return this;
    }

    public ButtonBuilder withCaptionTextAlignment(Element.PositionRelativeTop top, final int distance) {
        this.captionTopAlignment = top;
        this.captionTopDistance = distance;
        return this;
    }

    public ButtonBuilder withCaptionTextAlignment(Element.PositionRelativeLeft left, final int distance) {
        this.captionLeftAlignment = left;
        this.captionLeftDistance = distance;
        return this;
    }

    public Button build(final GuiContainer container) {
        Button b = this.builder.buildButton(this.name, this.coordinates, this.material, container);
        b.setCaptionTextLeftAlignement(this.captionLeftAlignment, this.captionLeftDistance);
        b.setCaptionTextTopAlignement(this.captionTopAlignment, this.captionTopDistance);
        return b;
    }

}
