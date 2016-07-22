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

import be.yildiz.common.BaseCoordinate;
import be.yildiz.common.client.gui.listener.MouseLeftClickListener;
import be.yildiz.common.client.gui.listener.MouseMoveListener;
import be.yildiz.common.vector.Point2D;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;

/**
 * @author Grégory Van den Borre
 */
public interface Button extends ContainerElement {

    Button setCaptionText(String text);

    BaseCoordinate getCoordinates();

    Font getCaptionFont();

    /**
     * Change the font for the button text.
     *
     * @param font New font to use.
     */
    void setCaptionFont(Font font);

    void detachFromParent();

    /**
     * Change the button materials.
     *
     * @param material Background, highlight and inactive material data.
     */
    void setMaterial(ButtonMaterial material);

    Material getMaterial();

    void setMaterial(Material m);

    Material getHighlightMaterial();

    void addOnMouseOverListener(OnMouseOverListener l);

    void addMouseMoveListener(MouseMoveListener l);

    void addMouseLeftClickListener(MouseLeftClickListener l);

    void delete();

    void mouseLeftClick(int x, int y);

    boolean contains(Point2D position);

    void highlight(boolean contains);

    Element setMouseOver(boolean b, Point2D zero);

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

    Element setFocusable(boolean b);

    /**
     * Set the caption horizontal alignment.
     *
     * @param alignment Caption alignment.
     * @param diff      Offset in pixels.
     */
    void setCaptionTextLeftAlignement(PositionRelativeLeft alignment, int diff);

    /**
     * Set the caption horizontal alignment.
     *
     * @param alignment Caption alignment.
     */
    void setCaptionTextLeftAlignement(PositionRelativeLeft alignment);

    /**
     * Set the caption vertical alignment.
     *
     * @param alignement Caption alignment.
     */
    void setCaptionTextTopAlignement(PositionRelativeTop alignement);

    /**
     * Set the caption vertical alignment.
     *
     * @param alignment Caption alignment.
     * @param diff      Offset in pixels.
     */
    void setCaptionTextTopAlignement(PositionRelativeTop alignment, int diff);

    Material getInactiveMaterial();

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
}
