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

package be.yildizgames.module.graphic.gui.image;

import be.yildizgames.module.graphic.gui.ContainerChild;
import be.yildizgames.module.graphic.gui.Widget;
import be.yildizgames.module.graphic.material.Material;

/**
 * An image to display on the screen.
 *
 * @author Grégory Van den Borre
 */
public interface Image extends Widget, ContainerChild {

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
     * Force the contains method to always return false.
     */
    void forceContainsFalse();

    /**
     * Multiply the Image size by a given factor.
     *
     * @param f Multiply factor.
     */
    void multiplySize(float f);


    default int getBorderSize() {
        return 0;
    }

    void delete();
}
