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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.translation.Key;
import be.yildiz.common.translation.Translation;
import be.yildiz.module.color.Color;
import be.yildiz.module.coordinate.BaseCoordinate;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;

import java.util.Optional;

/**
 * A check box is a simple element with 2 states, checked and unchecked. An AbstractMouseLeftClickListener is added to switch states when the checkbox is clicked on.
 *
 * @author Grégory Van Den Borre
 */
public final class CheckBoxGui extends ContainerChild implements CheckBox {

    /**
     * Distance in pixel to put the caption from the box.
     */
    private static final int CAPTION_DISTANCE = 10;
    /**
     * Background image.
     */
    private final AbstractIconElement background;
    /**
     * Image to print when the state is checked.
     */
    private final AbstractIconElement check;
    /**
     * Caption associated to the check box.
     */
    private final AbstractTextElement caption;
    /**
     * Current check box state.
     */
    private boolean checked;
    private Material material;

    private Material highlightMaterial;

    /**
     * Full constructor, initialize parameters and add a mouse click listener to switch checked state.
     *
     * @param name            Check box name, must be unique.
     * @param coordinates     Check box size and position.
     * @param backgroundImage Background for the check box.
     * @param checkedImage    Background for a checked check box.
     * @param text            Check box caption text.
     * @param container       Container holding the check box.
     */
    CheckBoxGui(final String name, final BaseCoordinate coordinates, final AbstractIconElement backgroundImage, final Material hoverMaterial, final AbstractIconElement checkedImage,
                final AbstractTextElement text, final GuiContainer container) {
        super(name, coordinates, Optional.of(container));
        //FIXME low assert
        assert backgroundImage != null : "backgroundImage parameter is null";
        assert checkedImage != null : "checkedImage parameter is null";
        assert text != null : "text parameter is null";
        this.background = backgroundImage;
        this.material = backgroundImage.getMaterial();
        this.highlightMaterial = hoverMaterial;
        this.check = checkedImage;
        this.caption = text;
        this.caption.setHeight(this.caption.getFontHeight());
        this.caption.setTop(this, PositionRelativeTop.CENTER);
        this.caption.setLeft(this, PositionRelativeLeft.RIGHT, CAPTION_DISTANCE);
        this.addMouseLeftClickListener(() -> this.check(!this.isChecked()));
        this.updateCaptionPosition();
        this.setHighlightable(true);
        this.showImpl();
    }

    @Override
    public CheckBoxGui setCaptionText(final String text) {
        this.caption.setText(text);
        return this;
    }

    @Override
    public CheckBoxGui setCaptionText(final Key text) {
        return this.setCaptionText(Translation.getInstance().translate(text));
    }

    @Override
    public void check(final boolean checkedState) {
        this.checked = checkedState;
        if (this.checked) {
            this.check.show();
        } else {
            this.check.hide();
        }
    }

    @Override
    protected void delete() {
        this.background.delete();
        this.check.delete();
        this.caption.delete();
    }

    /**
     * Update the check box background material.
     *
     * @param material New material to use.
     * @return This object for chaining.
     */
    public CheckBoxGui setMaterial(final Material material) {
        this.material = material;
        this.background.setMaterial(material);
        return this;
    }

    /**
     * Update the check box checked material.
     *
     * @param material New material to use.
     * @return This object for chaining.
     */
    public CheckBoxGui setCheckedMaterial(final Material material) {
        this.check.setMaterial(material);
        return this;
    }

    /**
     * Update the caption font.
     *
     * @param font New font to use.
     * @return This object for chaining.
     */
    public CheckBoxGui setFont(final Font font) {
        this.caption.setFont(font);
        this.updateCaptionPosition();
        return this;
    }

    @Override
    protected void highlightImpl(final boolean over) {
        if (over) {
            this.background.setMaterial(this.highlightMaterial);
        } else {
            this.background.setMaterial(this.material);
        }
    }

    @Override
    protected void setSizeImpl(final int width, final int height) {
        assert width >= 0 : "w parameter cannot be under 0";
        assert height >= 0 : "h parameter cannot be under 0";
        this.background.setSize(width, height);
        this.check.setSize(width, height);
        this.updateCaptionPosition();
    }

    @Override
    protected void showImpl() {
        this.background.show();
        this.caption.show();
        this.check(this.checked);
    }

    @Override
    protected void hideImpl() {
        this.background.hide();
        this.caption.hide();
        this.check.hide();
    }

    @Override
    protected Element setPositionImpl(final int positionX, final int positionY) {
        assert positionX >= 0 : "x parameter cannot be under 0";
        assert positionY >= 0 : "y parameter cannot be under 0";
        this.background.setPosition(positionX, positionY);
        this.check.setPosition(positionX, positionY);
        this.updateCaptionPosition();
        return this;
    }

    /**
     * Update the caption position when the box position or size is updated.
     */
    private void updateCaptionPosition() {
        this.caption.setLeft(this, PositionRelativeLeft.RIGHT, CAPTION_DISTANCE);
        this.caption.setTop(this, PositionRelativeTop.CENTER);
    }

    /**
     * Change the caption text color.
     * @param color Color of the caption.
     * @return This object for chaining.
     */
    public CheckBoxGui setCaptionColor(final Color color) {
        this.caption.setColor(color);
        return this;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
