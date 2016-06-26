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

import be.yildiz.common.client.gui.listener.MouseLeftClickListener;
import be.yildiz.module.graphic.Material;

import java.util.Optional;

/**
 * An image to display on the screen.
 *
 * @author Grégory Van den Borre
 */
public interface Image extends Element {

    /**
     * Move the image to the top or bottom.
     *
     * @param y Move the image to y pixel to the bottom, negative value will move to the top.
     */
    void addToTop(int y);

    /**
     * @return The Image Material.
     */
    Material getMaterial();

    /**
     * Set the image material.
     *
     * @param m Material to set.
     */
    void setMaterial(Material m);

    /**
     * Add a listener to notify when the mouse left button is pressed.
     *
     * @param l Listener to add.
     */
    void addMouseLeftClickListener(MouseLeftClickListener l);

    /**
     * Add a listener to notify when the mouse pass over the Image.
     *
     * @param l Listener to add.
     */
    void addOnMouseOverListener(OnMouseOverListener l);

    /**
     * Force the contains method to always return false.
     */
    void forceContainsFalse();

    void align(Alignment alignment);

    void addToLeft(int left);

    /**
     * @return The Image height.
     */
    int getHeight();

    /**
     * Multiply the Image size by a given factor.
     *
     * @param f Multiply factor.
     */
    void multiplySize(float f);

    /**
     * Delete the Image from the system.
     */
    void delete();

    /**
     * Set the Image size.
     *
     * @param w Image width.
     * @param h Image height.
     */
    void setSize(int w, int h);

    default int getBorderSize() {
        return 0;
    }

    Optional<GuiContainer> getParent();

    void detachFromParent();
}
