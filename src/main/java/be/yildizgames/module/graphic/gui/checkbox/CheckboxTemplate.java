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

package be.yildizgames.module.graphic.gui.checkbox;

import be.yildizgames.module.coordinates.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public final class CheckboxTemplate {

    /**
     * Material to use when the checkbox is empty.
     */
    private final Material backgroundMaterial;

    /**
     * Material to use when the mouse is over the checkbox.
     */
    private final Material hoverMaterial;

    /**
     * Material to use when the checkbox is checked.
     */
    private final Material checkedMaterial;

    /**
     * Checkbox width and height.
     */
    private final Size size;

    /**
     * Font to use for the caption.
     */
    private final Font font;

    private final Material checkedHover;

    public CheckboxTemplate(Material backgroundMaterial, Material hoverMaterial, Material checkedMaterial, Material checkedHover, Size size, Font font) {
        super();
        this.backgroundMaterial = backgroundMaterial;
        this.hoverMaterial = hoverMaterial;
        this.checkedMaterial = checkedMaterial;
        this.checkedHover = checkedHover;
        this.size = size;
        this.font = font;
    }

    public Material getBackgroundMaterial() {
        return backgroundMaterial;
    }

    public Material getHoverMaterial() {
        return hoverMaterial;
    }

    public Material getCheckedMaterial() {
        return checkedMaterial;
    }

    public Size getSize() {
        return size;
    }

    public Font getFont() {
        return font;
    }

    public Material getCheckedHover() {
        return checkedHover;
    }
}
