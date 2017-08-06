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

import be.yildiz.common.translation.Key;
import be.yildiz.common.translation.Translation;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;

import java.util.Optional;

/**
 * A GuiButton is an highlightable Widget, it can be associated with a caption text.
 *
 * @author Grégory Van Den Borre
 */
public final class GuiButton extends ContainerChild implements Button {

    /**
     * Container.
     */
    private final GuiContainer buttonContainer;

    /**
     * Associated text.
     */
    private final AbstractTextElement caption;

    /**
     * Materials.
     */
    private ButtonMaterial materials;

    /**
     * Material to use when the button is in pushed state.
     */
    private Material pushedMaterial;

    /**
     * Flag to check the pushed state.
     */
    private boolean pushed;

    /**
     * Caption horizontal alignment.
     */
    private PositionRelativeLeft captionLeftAlign;

    private int leftDiff;

    private PositionRelativeTop captionTopAlign;

    private int topDiff;

    /**
     * Full constructor, hidden from outside package, must be invoked by the gui builder.
     *
     * @param name        GuiButton name, must be unique.
     * @param captionText Caption associated to the button.
     * @param container   Background container.
     * @param materials   GuiButton materials.
     * @param parent      Container holding the button.
     */
    GuiButton(final String name, final AbstractTextElement captionText, final GuiContainer container, final ButtonMaterial materials, final GuiContainer parent) {
        super(name, container.getCoordinates(), Optional.of(parent));
        container.setMaterial(materials.material);
        this.buttonContainer = container;
        this.setHighlightable(true);
        this.setFocusable(true);
        this.caption = captionText;
        this.highlight(false);
        this.materials = materials;
        this.pushedMaterial = materials.highlight;
        this.caption.setHeight(this.caption.getFontHeight());
        this.setCaptionTextLeftAlignement(PositionRelativeLeft.CENTER);
        this.setCaptionTextTopAlignement(PositionRelativeTop.CENTER);
        this.hide();
        this.show();
    }

    @Override
    public void setData(final ButtonData data) {
        this.setMaterial(data.material);
        this.removeAllClickListeners();
        this.addMouseLeftClickListener(data.listener);
    }

    @Override
    public void setCaptionTextLeftAlignement(final PositionRelativeLeft alignment, final int diff) {
        this.caption.setLeft(this, alignment, diff - this.getLeft());
        this.captionLeftAlign = alignment;
        this.leftDiff = diff;
    }

    @Override
    public void setCaptionTextLeftAlignement(final PositionRelativeLeft alignment) {
        this.setCaptionTextLeftAlignement(alignment, 0);
    }

    @Override
    public void setCaptionTextTopAlignement(final PositionRelativeTop alignment, final int diff) {
        this.caption.setTop(this, alignment, diff - this.getTop());
        this.captionTopAlign = alignment;
        this.topDiff = diff;
    }

    @Override
    public void setCaptionTextTopAlignement(final PositionRelativeTop alignment) {
        setCaptionTextTopAlignement(alignment, 0);
    }

    @Override
    public GuiButton setCaptionText(final String text) {
        this.caption.setText(text);
        return this;
    }

    @Override
    public GuiButton setCaptionText(final Key key) {
        return this.setCaptionText(Translation.getInstance().translate(key));
    }

    /**
     * @param over If <code>true</code> the base image will show and the highlight image will hide, false will invert it.
     */
    @Override
    protected void highlightImpl(final boolean over) {
        if (over) {
            this.buttonContainer.setMaterial(this.materials.highlight);
        } else {
            this.buttonContainer.setMaterial(this.materials.material);
        }
    }

    @Override
    public void delete() {
        this.caption.delete();
        this.buttonContainer.delete();
    }

    @Override
    public void desactivate() {
        this.buttonContainer.setMaterial(this.materials.inactive);
        this.setHighlightable(false);
        this.setFocusable(false);
        this.setMouseClickActive(false);
    }

    @Override
    public void reactivate() {
        this.buttonContainer.setMaterial(this.materials.material);
        this.setHighlightable(true);
        this.setFocusable(true);
        this.setMouseClickActive(true);
    }

    /**
     * Set the button in not pushed state.
     */
    public void setUnpushed() {
        this.buttonContainer.setMaterial(this.materials.material);
        this.pushed = false;
    }

    /**
     * Set the button in pushed state.
     */
    public void setPushed() {
        this.buttonContainer.setMaterial(this.pushedMaterial);
        this.pushed = true;
    }

    /**
     * Show caption and icon, not highlight.
     */
    @Override
    protected void showImpl() {
        this.buttonContainer.show();
    }

    /**
     * Hide everything.
     */
    @Override
    protected void hideImpl() {
        this.buttonContainer.hide();
    }

    @Override
    protected void setSizeImpl(final int width, final int height) {
        this.buttonContainer.setSize(width, height);
        // force recompute position
        this.setCaptionText(this.caption.getText());
    }

    @Override
    protected Element setPositionImpl(final int left, final int top) {
        this.buttonContainer.setPosition(left, top);
        // force recompute position
        this.setCaptionTextLeftAlignement(this.captionLeftAlign, 0);
        return this;
    }

    @Override
    public Material getInactiveMaterial() {
        return this.materials.inactive;
    }

    @Override
    public Font getInactiveFont() {
        return this.materials.inactiveFont;
    }

    @Override
    public void setInactiveMaterial(final Material material) {
        ButtonMaterial old = this.materials;
        this.materials = new ButtonMaterial(old.material, old.highlight, material, old.font, old.font);

    }

    @Override
    public PositionRelativeLeft getCaptionHorizontalAlignment() {
        return this.captionLeftAlign;
    }

    @Override
    public int getCaptionHorizontalPadding() {
        return this.leftDiff;
    }

    @Override
    public PositionRelativeTop getCaptionVerticalAlignment() {
        return this.captionTopAlign;
    }

    @Override
    public int getCaptionVerticalPadding() {
        return this.topDiff;
    }

    /**
     * Change the button background.
     *
     * @param material new Material to use.
     */
    @Override
    public void setMaterial(final Material material) {
        ButtonMaterial old = this.materials;
        if (this.materials.inactive.equals(this.materials.material)) {
            this.materials = new ButtonMaterial(material, old.highlight, old.font);
        } else {
            this.materials = new ButtonMaterial(material, old.highlight, old.inactive, old.font, old.inactiveFont);
        }
        this.buttonContainer.setMaterial(material);
    }

    /**
     * @return The text printed as caption.
     */
    public String getCaptionText() {
        return this.caption.getText();
    }

    /**
     * @return The background material.
     */
    @Override
    public Material getMaterial() {
        return this.materials.material;
    }

    @Override
    public void setMaterial(final ButtonMaterial material) {
        this.materials = material;
        this.buttonContainer.setMaterial(material.material);
    }

    /**
     * @return The highlighted material.
     */
    @Override
    public Material getHighlightMaterial() {
        return this.materials.highlight;
    }

    /**
     * Change the highlighted button background.
     *
     * @param material New Material to use.
     */
    public void setHighlightMaterial(final Material material) {
        ButtonMaterial old = this.materials;
        this.materials = new ButtonMaterial(old.material, material, old.inactive, old.font, old.inactiveFont);

    }

    /**
     * @return The caption font.
     */
    @Override
    public Font getCaptionFont() {
        return this.caption.getFont();
    }

    @Override
    public void setCaptionFont(final Font font) {
        this.caption.setFont(font);
        // recompute caption position
        this.setPosition(this.getLeft(), this.getTop());
    }

    // FIXME do not store material java side, set it native side and just use a
    // swap() method to pass from normal to hightlight(in native use pointer
    // instead of string)


    public boolean isPushed() {
        return pushed;
    }

    public void setPushedMaterial(Material pushedMaterial) {
        this.pushedMaterial = pushedMaterial;
    }
}
