/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.client.translation.Translation;
import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;
import be.yildizgames.module.graphic.gui.checkbox.CheckBox;
import be.yildizgames.module.graphic.gui.element.AbstractIconElement;
import be.yildizgames.module.graphic.gui.element.AbstractTextElement;
import be.yildizgames.module.graphic.gui.internal.BaseContainerChild;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.material.Material;

/**
 * A check box is a simple element with 2 states, checked and unchecked. An AbstractMouseLeftClickListener is added to switch states when the checkbox is clicked on.
 *
 * @author Grégory Van Den Borre
 */
final class SimpleCheckBox extends BaseContainerChild implements CheckBox {

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
    private Material checkMaterial;
    /**
     * Current check box state.
     */
    private boolean checked;

    private Material material;

    private Material highlightMaterial;

    private Material checkedHighlightMaterial;

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
    SimpleCheckBox(
            final String name,
            final BaseCoordinate coordinates,
            final AbstractIconElement backgroundImage,
            final Material hoverMaterial,
            final AbstractIconElement checkedImage,
            final Material checkedHoverMaterial,
            final AbstractTextElement text,
            final SimpleContainer container) {
        super(name, coordinates, container);
        assert hoverMaterial != null : "hoverMaterial parameter is null";
        assert backgroundImage != null : "backgroundImage parameter is null";
        assert checkedImage != null : "checkedImage parameter is null";
        assert text != null : "text parameter is null";
        this.background = backgroundImage;
        this.material = backgroundImage.getMaterial();
        this.checkMaterial = checkedImage.getMaterial();
        this.highlightMaterial = hoverMaterial;
        this.checkedHighlightMaterial = checkedHoverMaterial;
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
    public CheckBox setCaptionText(final String text) {
        this.caption.setText(text);
        return this;
    }

    @Override
    public CheckBox setCaptionText(final TranslationKey text) {
        return this.setCaptionText(Translation.getInstance().translate(text));
    }

    @Override
    public CheckBox check(final boolean checkedState) {
        this.checked = checkedState;
        if (this.checked) {
            this.background.hide();
            this.check.show();
        } else {
            this.background.show();
            this.check.hide();
        }
        return this;
    }

    @Override
    public void delete() {
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
    @Override
    public CheckBox setMaterial(final Material material) {
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
    @Override
    public CheckBox setCheckedMaterial(final Material material) {
        this.check.setMaterial(material);
        this.checkMaterial = material;
        return this;
    }

    /**
     * Update the caption font.
     *
     * @param font New font to use.
     * @return This object for chaining.
     */
    @Override
    public CheckBox setFont(final Font font) {
        this.caption.setFont(font);
        this.updateCaptionPosition();
        return this;
    }

    /**
     * Change the caption text color.
     *
     * @param color Color of the caption.
     * @return This object for chaining.
     */
    @Override
    public CheckBox setCaptionColor(final Color color) {
        this.caption.setColor(color);
        return this;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    protected void highlightImpl(final boolean over) {
        if (over) {
            this.background.setMaterial(this.highlightMaterial);
            this.check.setMaterial(this.checkedHighlightMaterial);
        } else {
            this.background.setMaterial(this.material);
            this.check.setMaterial(this.checkMaterial);
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
     *
     * @param color Color of the caption.
     * @return This object for chaining.
     */
    @Override
    public CheckBox setCaptionColor(final Color color) {
        this.caption.setColor(color);
        return this;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
