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

package be.yildizgames.module.graphic.gui.button;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.ContainerChild;
import be.yildizgames.module.graphic.gui.OnMouseOverListener;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;
import be.yildizgames.module.graphic.gui.Widget;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;

/**
 * @author Grégory Van den Borre
 */
public interface Button extends Widget, ContainerChild {

    Button setCaptionText(String text);

    Button setCaptionText(TranslationKey text);

    Font getCaptionFont();

    /**
     * Change the font for the button text.
     *
     * @param font New font to use.
     */
    void setCaptionFont(Font font);


    /**
     * Change the button materials.
     *
     * @param material Background, highlight and inactive material data.
     */
    void setMaterial(ButtonMaterial material);

    Material getMaterial();

    void setMaterial(Material m);

    Material getHighlightMaterial();

    @Override
    void addOnMouseOverListener(OnMouseOverListener l);

    @Override
    void addMouseMoveListener(MouseMoveListener l);

    @Override
    void addMouseLeftClickListener(MouseLeftClickListener l);

    void mouseLeftClick(MousePosition position);

    @Override
    boolean contains(MousePosition position);

    void highlight(boolean contains);

    /**
     * Replace all existing data for this button by new one. Old materials and click listeners will be removed and replaced by this ones.
     *
     * @param data New data for this button.
     */
    void setData(ButtonData data);

    /**
     * The button will no longer be clickable nor highlitable.
     */
    void desactivate();

    /**
     * Reactive the button if it has been disabled.
     */
    void reactivate();

    void setFocusable(boolean b);

    /**
     * Set the caption horizontal alignment.
     *
     * @param alignment Caption alignment.
     * @param diff      Offset in pixels.
     */
    void setCaptionTextLeftAlignment(PositionRelativeLeft alignment, int diff);

    /**
     * Set the caption horizontal alignment.
     *
     * @param alignment Caption alignment.
     */
    void setCaptionTextLeftAlignment(PositionRelativeLeft alignment);

    /**
     * Set the caption vertical alignment.
     *
     * @param alignment Caption alignment.
     */
    void setCaptionTextTopAlignment(PositionRelativeTop alignment);

    /**
     * Set the caption vertical alignment.
     *
     * @param alignment Caption alignment.
     * @param diff      Offset in pixels.
     */
    void setCaptionTextTopAlignment(PositionRelativeTop alignment, int diff);

    Material getInactiveMaterial();

    Font getInactiveFont();

    /**
     * Set a material for the button when in inactive state. If no material is set, base material will be used.
     *
     * @param material Material to use in inactive state.
     */
    void setInactiveMaterial(Material material);

    PositionRelativeLeft getCaptionHorizontalAlignment();

    int getCaptionHorizontalPadding();

    PositionRelativeTop getCaptionVerticalAlignment();

    int getCaptionVerticalPadding();

    void setPushedMaterial(Material pushed);
}
