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

package be.yildizgames.module.graphic.gui.inputbox;

import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.material.Material;

/**
 * Template to build the input box.
 *
 * @author Van den Borre Grégory
 */
public final class InputBoxTemplate {

    /**
     * Input box size.
     */
    private final Size size;

    /**
     * Input box base material.
     */
    private final Material material;

    /**
     * Input box material when selected.
     */
    private final Material focus;

    /**
     * Input box cursor material.
     */
    private final Material cursor;

    /**
     * Input box font.
     */
    private final Font font;
    private final Font captionFont;

    public InputBoxTemplate(Size size, Material material, Material focus, Material cursor, Font font) {
        this.size = size;
        this.material = material;
        this.focus = focus;
        this.cursor = cursor;
        this.font = font;
        this.captionFont = font;
    }

    public InputBoxTemplate(Size size, Material material, Material focus, Material cursor, Font font, Font captionFont) {
        this.size = size;
        this.material = material;
        this.focus = focus;
        this.cursor = cursor;
        this.font = font;
        this.captionFont = captionFont;
    }

    public Size getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    public Material getFocus() {
        return focus;
    }

    public Material getCursor() {
        return cursor;
    }

    public Font getFont() {
        return font;
    }

    public Font getCaptionFont() {
        return captionFont;
    }
}
